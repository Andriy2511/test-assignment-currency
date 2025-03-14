package org.example.currency.dto;

import lombok.Builder;
import lombok.Getter;
import org.example.currency.model.Currency;

@Getter
@Builder(toBuilder = true)
public class CurrencyDTO {
    String currencyCode;

    public static Currency toCurrency(CurrencyDTO currencyDTO) {
        Currency currency = new Currency();
        currency.setCode(currencyDTO.getCurrencyCode());
        return currency;
    }
    public static CurrencyDTO toDTO(Currency currency) {
        return CurrencyDTO.builder()
                .currencyCode(currency.getCode())
                .build();
    }
}
