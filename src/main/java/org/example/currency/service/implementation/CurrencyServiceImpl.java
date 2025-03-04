package org.example.currency.service.implementation;

import org.example.currency.exception.CurrencyNotFoundException;
import org.example.currency.model.Currency;
import org.example.currency.repository.CurrencyRepository;
import org.example.currency.service.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CurrencyServiceImpl implements CurrencyService {

    private final CurrencyRepository currencyRepository;

    @Autowired
    public CurrencyServiceImpl(CurrencyRepository currencyRepository) {
        this.currencyRepository = currencyRepository;
    }

    @Override
    public Currency getCurrencyByCode(String code) {
        return currencyRepository.findByCode(code).orElseThrow(() -> new CurrencyNotFoundException("Currency doesn't exist"));
    }

    @Override
    public void addCurrencyByCode(String currencyCode) {
        Currency currency = new Currency();
        currency.setCode(currencyCode);
        currencyRepository.save(currency);
    }
}
