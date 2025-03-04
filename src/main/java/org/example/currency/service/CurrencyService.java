package org.example.currency.service;

import org.example.currency.model.Currency;

public interface CurrencyService {
    Currency getCurrencyByCode(String name);

    void addCurrencyByCode(String currencyCode);
}
