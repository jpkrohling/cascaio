/* 
 * Copyright (C) 2014 Juraci Paixão Kröhling <juraci at kroehling.de>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.cascaio.backend.v1.control.batch.stock;

import com.cascaio.backend.v1.control.batch.BasicBatchCheckpoint;
import org.slf4j.Logger;

import javax.batch.api.chunk.ItemReader;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

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

    public InputStream retrieveFileFromFtp() throws MalformedURLException, IOException {
        String ftpUrl = "ftp://%s:%s@%s/%s;type=i";
        ftpUrl = String.format(ftpUrl, getUsername(), getPassword(), getServer(), getRemotePath());
        URL url = new URL(ftpUrl);
        URLConnection conn = url.openConnection();
        return conn.getInputStream();
    }

    public boolean skipFirstLine() {
        return true;
    }

    public String getServer() {
        return "ftp.nasdaqtrader.com";
    }

    public String getPassword() {
        return "anonymous";
    }

    public String getUsername() {
        return "anonymous";
    }

    public String getRemotePath() {
        return "SymbolDirectory/nasdaqlisted.txt";
    }
}
