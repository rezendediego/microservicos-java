package br.diego.conversionapp.conversionservice.conversion.commons;


import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.util.Objects;

public class MoneyAmountWithCurrency {
    @JsonProperty("currency")
    public String currency;
    @JsonProperty("amount")
    public BigDecimal amount;

    public MoneyAmountWithCurrency() {
    }

    public MoneyAmountWithCurrency(String currency, BigDecimal amount) {
        this.currency = currency;
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MoneyAmountWithCurrency that)) return false;
        return currency.equals(that.currency) && amount.equals(that.amount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(currency, amount);
    }

    @Override
    public String toString() {
        return "MoneyAmountWithCurrency{" +
                "currency='" + currency + '\'' +
                ", amount=" + amount +
                '}';
    }
}
