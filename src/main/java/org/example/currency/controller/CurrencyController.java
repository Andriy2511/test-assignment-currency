package org.example.currency.controller;

import org.example.currency.dto.CurrencyDTO;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

public interface CurrencyController {
    @GetMapping
    List<CurrencyDTO> getAllCurrencies();
}
