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
package com.cascaio.api.v1.reference;

import com.cascaio.api.v1.validation.Currency;
import com.cascaio.api.v1.validation.ISODate;

import javax.validation.constraints.NotNull;
import javax.ws.rs.QueryParam;

/**
 * @author <a href="mailto:juraci.javadoc@kroehling.de">Juraci Paixão Kröhling</a>
 */
public class ExchangeRateQueryRequest {

    @QueryParam("currencyFrom")
    @NotNull
    @Currency
    private String currencyFrom;

    @QueryParam("currencyTo")
    @NotNull
    @Currency
    private String currencyTo;

    @QueryParam("dateStart")
    @ISODate
    private String dateStart;

    @QueryParam("dateEnd")
    @ISODate
    private String dateEnd;

    public ExchangeRateQueryRequest(String currencyFrom, String currencyTo, String dateStart, String dateEnd) {
        this.currencyFrom = currencyFrom;
        this.currencyTo = currencyTo;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
    }

    public ExchangeRateQueryRequest() {
    }

    public String getCurrencyFrom() {
        return currencyFrom;
    }

    public void setCurrencyFrom(String currencyFrom) {
        this.currencyFrom = currencyFrom;
    }

    public String getCurrencyTo() {
        return currencyTo;
    }

    public void setCurrencyTo(String currencyTo) {
        this.currencyTo = currencyTo;
    }

    public String getDateStart() {
        return dateStart;
    }

    public void setDateStart(String dateStart) {
        this.dateStart = dateStart;
    }

    public String getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(String dateEnd) {
        this.dateEnd = dateEnd;
    }
}
