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

import org.joda.money.CurrencyUnit;
import org.slf4j.Logger;

import javax.batch.api.partition.PartitionPlanImpl;
import javax.inject.Inject;
import java.util.List;
import java.util.Properties;

/**
 * @author <a href="mailto:juraci.javadoc@kroehling.de">Juraci Paixão Kröhling</a>
 */
public class ExchangeRatePartitionPlan extends PartitionPlanImpl {

    private static List<CurrencyUnit> availableCurrencies = CurrencyUnit.registeredCurrencies();

    @Inject
    Logger logger;

    private static final int MAX_THREADS = 25;

    @Override
    public int getPartitions() {
        int partitions = availableCurrencies.size();
        logger.trace("getPartitions: {}", partitions);
        return partitions;
    }

    @Override
    public int getThreads() {
        return Math.min(availableCurrencies.size(), MAX_THREADS); // at most, MAX_THREADS threads
    }

    @Override
    public Properties[] getPartitionProperties() {
        Properties[] properties = getCurrenciesToProcess();
        logger.trace("Final properties size: {}", properties.length);
        return properties;
    }

    private Properties[] getCurrenciesToProcess() {
        Properties[] properties = new Properties[getPartitions()];
        for (int i = 0 ; i < getPartitions() ; i++) {
            CurrencyUnit currency = availableCurrencies.get(i);
            logger.trace("Adding to {} partition plan position {}", currency.getCurrencyCode(), i);
            properties[i] = new Properties();
            properties[i].put("from", currency.getCurrencyCode());
        }
        return properties;
    }
}
