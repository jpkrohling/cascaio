package com.cascaio.backend.v1.control.batch.stockquote;

import com.cascaio.backend.v1.control.batch.BasicBatchCheckpoint;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.slf4j.Logger;

import javax.batch.api.BatchProperty;
import javax.batch.api.chunk.ItemReader;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Serializable;

/**
 * @author <a href="mailto:juraci.javadoc@kroehling.de">Juraci Paixão Kröhling</a>
 */
@Named
public class StockQuoteReader implements ItemReader {

    private static final String BASE_URL = "http://ichart.finance.yahoo.com/table.csv?a=01&b=01&c=2014&s=%s&g=d&ignore=.csv";

    @Inject
    @BatchProperty(name = "stockSymbol")
    String stockSymbol;

    @Inject
    HttpClient httpClient;

    @Inject
    Logger logger;

    private BufferedReader reader;
    private BasicBatchCheckpoint checkpoint;

    @Override
    public void open(Serializable serializable) throws Exception {
        if (null == serializable) {
            logger.trace("Starting a new checkpoint");
            checkpoint = new BasicBatchCheckpoint();
        } else {
            checkpoint = (BasicBatchCheckpoint) serializable;
            logger.trace("Checkpoint available: {}", checkpoint);
        }

        String url = String.format(BASE_URL, stockSymbol);
        logger.trace("URL to call: {}", url);

        HttpGet requestGet = new HttpGet(url);
        HttpResponse responseGet = httpClient.execute(requestGet);
        HttpEntity responseGetEntity = responseGet.getEntity();

        if (responseGet.getStatusLine().getStatusCode() != 200) {
            logger.error("Couldn't find the proper CSV file for the stock symbol {}", stockSymbol);
            throw new RuntimeException("Couldn't find the proper CSV file for the stock symbol " + stockSymbol);
        }

        logger.trace("Preparing reader");
        reader = new BufferedReader(new InputStreamReader(responseGetEntity.getContent()));
        reader.readLine(); // skip the first line
    }

    @Override
    public void close() throws Exception {
        if (null != reader) {
            reader.close();
        }
    }

    @Override
    public Object readItem() throws Exception {
        checkpoint.incrementAndGet();
        return reader.readLine();
    }

    @Override
    public Serializable checkpointInfo() throws Exception {
        logger.trace("Checkpoint info called");
        return checkpoint;
    }
}
