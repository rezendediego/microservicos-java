package br.diego.conversionapp.conversionservice.conversion.dto;


import br.diego.conversionapp.conversionservice.conversion.commons.MoneyAmountWithCurrency;
import com.fasterxml.jackson.annotation.JsonProperty;


public class ConversionRequest {
    @JsonProperty("amountSender")
    MoneyAmountWithCurrency amountSender;
    @JsonProperty("targetCurrency")
    String targetCurrency;

    public ConversionRequest() {
    }

    public MoneyAmountWithCurrency getAmountSender() {
        return amountSender;
    }

    public void setAmountSender(MoneyAmountWithCurrency amountSender) {
        this.amountSender = amountSender;
    }

    public String getTargetCurrency() {
        return targetCurrency;
    }

    public void setTargetCurrency(String targetCurrency) {
        this.targetCurrency = targetCurrency;
    }

    @Override
    public String toString() {
        return "ConversionRequest{" +
                "amountSender=" + amountSender +
                ", targetCurrency='" + targetCurrency + '\'' +
                '}';
    }
}