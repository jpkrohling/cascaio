package com.cascaio.api.v1.user;

import com.cascaio.api.v1.validation.Currency;
import com.cascaio.api.v1.validation.ISODate;
import com.cascaio.api.v1.validation.NumericRate;
import javax.validation.constraints.NotNull;

/**
 *
 * @author jpkroehling
 */
public class CheckingAccountTransactionCreateRequest {

    @NotNull
    private String accountId;

    @NotNull
    @ISODate
    private String date;

    @NotNull
    @NumericRate
    private String amount;

    @Currency
    private String originalCurrency;

    @NumericRate
    private String originalAmount;

    @NumericRate
    private String exchangeRate;

    private String name;
    private String originalDescription;
    private String categoryId;

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getOriginalCurrency() {
        return originalCurrency;
    }

    public void setOriginalCurrency(String originalCurrency) {
        this.originalCurrency = originalCurrency;
    }

    public String getOriginalAmount() {
        return originalAmount;
    }

    public void setOriginalAmount(String originalAmount) {
        this.originalAmount = originalAmount;
    }

    public String getExchangeRate() {
        return exchangeRate;
    }

    public void setExchangeRate(String exchangeRate) {
        this.exchangeRate = exchangeRate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOriginalDescription() {
        return originalDescription;
    }

    public void setOriginalDescription(String originalDescription) {
        this.originalDescription = originalDescription;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

}
