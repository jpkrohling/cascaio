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
package com.cascaio.backend.v1.control.batch.exchangerate;

import com.cascaio.backend.v1.control.batch.BasicBatchCheckpoint;
import com.cascaio.backend.v1.entity.reference.ExchangeRate;
import com.cascaio.backend.v1.entity.reference.adapter.BigDecimalAdapter;
import com.cascaio.backend.v1.entity.reference.adapter.CurrencyAdapter;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.joda.money.CurrencyUnit;
import org.joda.time.LocalDate;
import org.slf4j.Logger;

import javax.batch.api.BatchProperty;
import javax.batch.api.chunk.ItemReader;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

/**
 * @author <a href="mailto:juraci.javadoc@kroehling.de">Juraci Paixão Kröhling</a>
 */
@Named
public class ExchangeRateReader implements ItemReader {
    private static final String BASE_URL = "http://download.finance.yahoo.com/d/quotes.csv?a=01&b=01&c=2014&f=sl1&e=.csv&s=";

    @Inject
    Logger logger;

    @Inject
    @BatchProperty(name = "from")
    String from;

    @Inject
    BigDecimalAdapter bigDecimalAdapter;

    @Inject
    CurrencyAdapter currencyAdapter;

    private BufferedReader reader;
    private BasicBatchCheckpoint checkpoint;

    private CloseableHttpResponse responseGet;
    private CloseableHttpClient httpClient;

    @Override
    public void open(Serializable serializable) throws Exception {
        httpClient = HttpClients.createDefault();

        if (null == serializable) {
            logger.trace("Starting a new checkpoint");
            checkpoint = new BasicBatchCheckpoint();
        } else {
            checkpoint = (BasicBatchCheckpoint) serializable;
            logger.trace("Checkpoint available: {}", checkpoint);
        }

        CurrencyUnit fromCurrency = CurrencyUnit.of(from);

        logger.trace("Building http client");

        logger.trace("Listing currently registered currencies");
        List<CurrencyUnit> registeredCurrencies = CurrencyUnit.registeredCurrencies();
        StringBuilder conversionsToAsk = new StringBuilder();
        int current = 0;
        for (CurrencyUnit to : registeredCurrencies) {
            if (fromCurrency.equals(to)) {
                continue;
            }
            logger.debug("Would ask the provider from convert {} into {}", fromCurrency, to);

            if (current > 0) {
                conversionsToAsk.append("+");
            }

            conversionsToAsk.append(fromCurrency).append(to).append("=X");
            current++;
        }
        String url = BASE_URL + conversionsToAsk.toString();
        logger.trace("URL to call: {}", url);

        HttpGet requestGet = new HttpGet(url);
        responseGet = httpClient.execute(requestGet);
        HttpEntity responseGetEntity = responseGet.getEntity();

        logger.trace("Preparing reader");
        reader = new BufferedReader(new InputStreamReader(responseGetEntity.getContent()));
    }

    @Override
    public void close() throws Exception {
        logger.trace("Closing");
        if (null != reader) {
            reader.close();
        }

        if (null != responseGet) {
            logger.trace("Consuming the entity");
            EntityUtils.consume(responseGet.getEntity());
            logger.trace("Closing the GET request");
            responseGet.close();
        }

        if (null != httpClient) {
            logger.trace("Closing the HTTP client");
            httpClient.close();
        }
    }

    @Override
    public Object readItem() throws Exception {
        logger.trace("Reading item");
        String line = reader.readLine();
        if (null != line) {
            String[] parts = line.split(",");
            String left = parts[0];
            CurrencyUnit to = currencyAdapter.adapt(left.substring(4, 7));
            BigDecimal rate = bigDecimalAdapter.adapt(parts[1]);
            ExchangeRate exchangeRate = new ExchangeRate(CurrencyUnit.of(from), to, rate, LocalDate.now());

            checkpoint.incrementAndGet();
            logger.trace("Item read: {}", exchangeRate);
            return exchangeRate;
        } else {
            return null;
        }
    }

    @Override
    public Serializable checkpointInfo() throws Exception {
        logger.trace("Checkpoint info called");
        return checkpoint;
    }
}
