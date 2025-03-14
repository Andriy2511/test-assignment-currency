package org.example.currency.controller.implementation;

import org.example.currency.controller.CurrencyController;
import org.example.currency.dto.CurrencyDTO;
import org.example.currency.service.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/currency")
public class CurrencyControllerImpl implements CurrencyController {

    private final CurrencyService currencyService;

    @Autowired
    public CurrencyControllerImpl(CurrencyService currencyService) {
        this.currencyService = currencyService;
    }

    @Override
    @GetMapping
    public List<CurrencyDTO> getAllCurrencies() {
        return currencyService.getCurrencies();
    }
}
