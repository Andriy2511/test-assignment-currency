package org.example.currency.service;

import org.example.currency.dto.CurrencyDTO;
import org.example.currency.model.Currency;

import java.util.List;

public interface CurrencyService {
    Currency getCurrencyByCode(String name);

    List<CurrencyDTO> getCurrencies();

    void addCurrencyByCode(String currencyCode);
}
