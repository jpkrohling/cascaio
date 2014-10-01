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
package com.cascaio.backend.v1.entity.user.adapter;

import com.cascaio.api.v1.user.CheckingAccountTransactionCreateRequest;
import com.cascaio.api.v1.user.CheckingAccountTransactionResponse;
import com.cascaio.api.v1.user.CheckingAccountTransactionUpdateRequest;
import com.cascaio.backend.v1.boundary.reference.CategoryService;
import com.cascaio.backend.v1.boundary.user.CheckingAccountService;
import com.cascaio.backend.v1.entity.EntityAdapter;
import com.cascaio.backend.v1.entity.reference.adapter.BigDecimalAdapter;
import com.cascaio.backend.v1.entity.reference.adapter.CurrencyAdapter;
import com.cascaio.backend.v1.entity.reference.adapter.LocalDateAdapter;
import com.cascaio.backend.v1.entity.reference.adapter.ZonedDateTimeAdapter;
import com.cascaio.backend.v1.entity.user.CheckingAccount;
import com.cascaio.backend.v1.entity.user.CheckingTransaction;
import java.math.BigDecimal;
import java.time.LocalDate;
import javax.inject.Inject;

/**
 *
 * @author Juraci Paixão Kröhling <juraci at kroehling.de>
 */
public class CheckingAccountTransactionAdapter extends
        EntityAdapter<CheckingAccountTransactionCreateRequest, CheckingAccountTransactionUpdateRequest, CheckingAccountTransactionResponse, CheckingTransaction> {

    @Inject
    BigDecimalAdapter bigDecimalAdapter;

    @Inject
    LocalDateAdapter dateTimeAdapter;

    @Inject
    CurrencyAdapter currencyAdapter;

    @Inject
    CheckingAccountService accountService;

    @Inject
    CategoryService categoryService;

    @Override
    public CheckingAccountTransactionResponse adaptPersistent(CheckingTransaction persistent) {
        CheckingAccountTransactionResponse response = new CheckingAccountTransactionResponse();
        response.setAccountId(persistent.getAccount().getId());
        response.setAmount(bigDecimalAdapter.adapt(persistent.getAmount()));
        if (null != persistent.getCategory()) {
            response.setCategoryId(persistent.getCategory().getId());
        }
        response.setDate(dateTimeAdapter.adapt(persistent.getDate()));
        response.setExchangeRate(bigDecimalAdapter.adapt(persistent.getExchangeRate()));
        response.setId(persistent.getId());
        response.setName(persistent.getName());
        response.setOriginalAmount(bigDecimalAdapter.adapt(persistent.getOriginalAmount()));
        response.setOriginalCurrency(currencyAdapter.adapt(persistent.getOriginalCurrency()));
        response.setOriginalDescription(persistent.getOriginalDescription());

        return response;
    }

    @Override
    public CheckingTransaction adaptUpdate(CheckingAccountTransactionUpdateRequest request, CheckingTransaction toUpdate) {
        toUpdate.setAmount(bigDecimalAdapter.adapt(request.getAmount()));
        toUpdate.setDate(dateTimeAdapter.adapt(request.getDate()));
        toUpdate.setExchangeRate(bigDecimalAdapter.adapt(request.getExchangeRate()));
        toUpdate.setName(request.getName());
        toUpdate.setOriginalAmount(bigDecimalAdapter.adapt(request.getOriginalAmount()));
        toUpdate.setOriginalCurrency(currencyAdapter.adapt(request.getOriginalCurrency()));
        toUpdate.setOriginalDescription(request.getOriginalDescription());
        if (null != request.getCategoryId()) {
            toUpdate.setCategory(categoryService.readAsEntity(request.getCategoryId()));
        }
        return toUpdate;
    }

    @Override
    public CheckingTransaction adaptCreate(CheckingAccountTransactionCreateRequest request) {
        LocalDate date = dateTimeAdapter.adapt(request.getDate());
        BigDecimal amount = bigDecimalAdapter.adapt(request.getAmount());
        CheckingAccount account = accountService.readAsEntity(request.getAccountId());
        CheckingTransaction transaction = new CheckingTransaction(date, amount, account);

        transaction.setExchangeRate(bigDecimalAdapter.adapt(request.getExchangeRate()));
        transaction.setName(request.getName());
        transaction.setOriginalAmount(bigDecimalAdapter.adapt(request.getOriginalAmount()));
        transaction.setOriginalCurrency(currencyAdapter.adapt(request.getOriginalCurrency()));
        transaction.setOriginalDescription(request.getOriginalDescription());
        if (null != request.getCategoryId()) {
            transaction.setCategory(categoryService.readAsEntity(request.getCategoryId()));
        }

        return transaction;
    }

}
