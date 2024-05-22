package br.diego.conversionapp.conversionservice.conversion.dto;


import br.diego.conversionapp.conversionservice.conversion.commons.MoneyAmountWithCurrency;
import com.fasterxml.jackson.annotation.JsonProperty;



public class ConversionResponse {
    @JsonProperty("retrievedAt")
    public Long retrievedAt;
    @JsonProperty("success")
    public Boolean success;
    @JsonProperty("currencyPair")
    public String currencyPair;
    @JsonProperty("quote")
    public Double quote;
    @JsonProperty("amountSender")
    public MoneyAmountWithCurrency amountSender;
    @JsonProperty("amountReceiver")
    public MoneyAmountWithCurrency amountReceiver;

    public Long getRetrievedAt() {
        return retrievedAt;
    }

    public void setRetrievedAt(Long retrievedAt) {
        this.retrievedAt = retrievedAt;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getCurrencyPair() {
        return currencyPair;
    }

    public void setCurrencyPair(String currencyPair) {
        this.currencyPair = currencyPair;
    }

    public Double getQuote() {
        return quote;
    }

    public void setQuote(Double quote) {
        this.quote = quote;
    }

    public MoneyAmountWithCurrency getAmountSender() {
        return amountSender;
    }

    public void setAmountSender(MoneyAmountWithCurrency amountSender) {
        this.amountSender = amountSender;
    }

    public MoneyAmountWithCurrency getAmountReceiver() {
        return amountReceiver;
    }

    public void setAmountReceiver(MoneyAmountWithCurrency amountReceiver) {
        this.amountReceiver = amountReceiver;
    }

    @Override
    public String toString() {
        return "ConversionResponse{" +
                "retrievedAt=" + retrievedAt +
                ", success=" + success +
                ", currencyPair='" + currencyPair + '\'' +
                ", quote=" + quote +
                ", amountSender=" + amountSender +
                ", amountReceiver=" + amountReceiver +
                '}';
    }
}
