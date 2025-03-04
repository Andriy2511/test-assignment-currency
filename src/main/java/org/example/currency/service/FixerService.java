package org.example.currency.service;

import org.springframework.scheduling.annotation.Scheduled;

public interface FixerService {
    @Scheduled(fixedRate = 60*60*1000, initialDelay = 0)
    void scheduleUpdateChecker();

    Double getCurrencyRate(String currencyCode);
}
