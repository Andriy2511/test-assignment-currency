package org.example.currency.service.implementation;

import lombok.extern.slf4j.Slf4j;
import org.example.currency.client.FixerClient;
import org.example.currency.dto.FixerResponseDTO;
import org.example.currency.exception.FixerParsingException;
import org.example.currency.service.FixerService;
import org.example.currency.utils.FixerExchangeRate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Service
@Slf4j
public class FixerServiceImpl implements FixerService {

    private final FixerExchangeRate fixerExchangeRate;
    private final FixerClient fixerClient;

    public FixerServiceImpl(FixerClient fixerClient) {
        this.fixerExchangeRate = FixerExchangeRate.getInstance();
        this.fixerClient = fixerClient;
    }

    @Override
    @Scheduled(fixedRate = 60*60*1000, initialDelay = 0)
    public void scheduleUpdateChecker(){
        if(!isCurrentDate())
            updateRates();
    }

    @Override
    public Double getCurrencyRate(String currencyCode){
        return fixerExchangeRate.getRates().get(currencyCode);
    }

    private void updateRates() {
        setValuesToFixerExchangeRate(fixerClient.getCurrenciesRates());
    }

    private void setValuesToFixerExchangeRate(FixerResponseDTO response) {
        if (response != null && response.isSuccess()) {
            fixerExchangeRate.setSuccess(true);
            fixerExchangeRate.setTimestamp(response.getTimestamp());
            fixerExchangeRate.setBase(response.getBase());
            fixerExchangeRate.setDate(response.getDate());
            fixerExchangeRate.setRates(response.getRates());
        } else {
            throw new FixerParsingException("Failed to parse exchange rates");
        }
    }

    private boolean isCurrentDate(){
        return fixerExchangeRate.getDate() != null && stringDateToDate(fixerExchangeRate.getDate()).isEqual(LocalDate.now());
    }

    private LocalDate stringDateToDate(String date){
        return LocalDate.parse(date, DateTimeFormatter.ISO_LOCAL_DATE);
    }
}
