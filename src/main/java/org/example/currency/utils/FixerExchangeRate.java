package org.example.currency.utils;

import java.util.Map;

public class FixerExchangeRate {

    private static FixerExchangeRate INSTANCE;

    private boolean success;
    private Long timestamp;
    private String base;
    private String date;
    private Map<String, Double> rates;

    private FixerExchangeRate(){}

    public static FixerExchangeRate getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new FixerExchangeRate();
        }

        return INSTANCE;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Map<String, Double> getRates() {
        return rates;
    }

    public void setRates(Map<String, Double> rates) {
        this.rates = rates;
    }
}
