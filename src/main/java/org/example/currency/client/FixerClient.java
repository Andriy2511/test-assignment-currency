package org.example.currency.client;

import org.example.currency.dto.FixerResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(value = "currencyClient", url = "${fixer.url}", configuration = FixerFeignClientConfiguration.class)
public interface FixerClient {

    @GetMapping
    FixerResponseDTO getCurrenciesRates();
}
