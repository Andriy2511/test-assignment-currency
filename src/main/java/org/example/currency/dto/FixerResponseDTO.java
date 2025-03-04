package org.example.currency.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class FixerResponseDTO {
    private boolean success;
    private Long timestamp;
    private String base;
    private String date;
    private Map<String, Double> rates;
}
