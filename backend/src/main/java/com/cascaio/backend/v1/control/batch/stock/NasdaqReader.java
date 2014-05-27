package com.cascaio.backend.v1.control.batch.stock;

import com.cascaio.backend.v1.control.batch.BasicBatchCheckpoint;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPClientConfig;
import org.slf4j.Logger;

import javax.batch.api.chunk.ItemReader;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.*;

/**
 * @author <a href="mailto:juraci.javadoc@kroehling.de">Juraci Paixão Kröhling</a>
 */
@Named
public class NasdaqReader implements ItemReader {

    @Inject
    Logger logger;

    private BufferedReader in;
    private BasicBatchCheckpoint checkpoint;

    @Override
    public void open(Serializable serializable) throws Exception {
        logger.trace("Opening file from FTP and reading to memory");

        if (null == serializable) {
            logger.trace("Starting a new checkpoint");
            checkpoint = new BasicBatchCheckpoint();
        } else {
            checkpoint = (BasicBatchCheckpoint) serializable;
            logger.trace("Checkpoint available: {}", checkpoint);
        }

        InputStream is = retrieveFileFromFtp();
        InputStreamReader isr = new InputStreamReader(is);
        in = new BufferedReader(isr);
        if (skipFirstLine()) {
            in.readLine();
        }
    }

    @Override
    public void close() throws Exception {
        if (in != null) {
            in.close();
        }
    }

    @Override
    public Object readItem() throws Exception {
        return in.readLine();
    }

    @Override
    public Serializable checkpointInfo() throws Exception {
        logger.trace("Checkpoint info called");
        return checkpoint;
    }

    public InputStream retrieveFileFromFtp() throws Exception {
        // open ftp connection
        FTPClient ftp = new FTPClient();
        FTPClientConfig config = new FTPClientConfig();
        ftp.configure(config);

        try {
            File file = File.createTempFile("nasdaq-listed-", ".txt");
            FileOutputStream fos = new FileOutputStream(file);
            logger.trace("Temporary file created: {}", file.getAbsolutePath());

            logger.trace("Connecting to server {}", getServer());
            ftp.connect(getServer());

            logger.trace("Logging in as {}", getUsername());
            if (!ftp.login(getUsername(), getPassword())) {
                logger.error("Couldn't login as {}, aborting.", getUsername());
                throw new Exception("Login not accepted.");
            }

            logger.trace("Setting file type to ASCII");
            ftp.setFileType(FTP.ASCII_FILE_TYPE);

            logger.trace("Entering local passive mode");
            ftp.enterLocalPassiveMode();

            logger.trace("Retrieving file {}", getRemotePath());
            if (!ftp.retrieveFile(getRemotePath(), fos)) {
                logger.error("Couldn't retrieveAndCreate file");
                throw new Exception("Unable to retrieveAndCreate file");
            }

            logger.trace("Logging out");
            ftp.logout();

            logger.trace("Closing local stream");
            fos.close();

            logger.trace("Returning local file input stream");
            return new FileInputStream(file);
        } finally {
            if(ftp.isConnected()) {
                try {
                    logger.trace("Disconnecting from {}", getServer());
                    ftp.disconnect();
                } catch(IOException ioe) {
                    logger.error("Exception while disconnecting: ", ioe);
                }
            }
        }
    }

    public boolean skipFirstLine() {
        return true;
    }

    public String getServer() {
        return "ftp.nasdaqtrader.com";
    }

    public String getPassword() {
        return "";
    }

    public String getUsername() {
        return "anonymous";
    }

    public String getRemotePath() {
        return "SymbolDirectory/nasdaqlisted.txt";
    }
}
