package com.example.exchangeapp.services;

import com.example.exchangeapp.config.ApiProperties;
import com.example.exchangeapp.models.CurrencyDto;
import com.example.exchangeapp.utils.UrlCreator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Slf4j
@Service
@RequiredArgsConstructor
public class CurrencyService {

    private final RestTemplate restTemplate;
    private final ApiProperties apiProperties;

    public CurrencyDto getCurrency(String currencyCode) {

        String url = UrlCreator.createUrl(
                apiProperties.getBaseUrl(),
                apiProperties.getExchangeGroup(), currencyCode,
                apiProperties.getFormatVariable());

        log.info("Inside get currency. Executing getForObject for url: {}", url);

        return restTemplate.getForObject(url,
                CurrencyDto.class);
    }

    public BigDecimal countExchangeResult(BigDecimal amountOfGivenCurrency,
                                          CurrencyDto givenCurrency,
                                          CurrencyDto resultCurrency) {

        BigDecimal givenSellingValue = getGivenSellingValue(givenCurrency);
        BigDecimal resultBuyingValue = getResultBuyingValue(resultCurrency);

        return countExchangeValue(amountOfGivenCurrency, givenSellingValue, resultBuyingValue);
    }

    public BigDecimal countExchangeValue(BigDecimal amountOfGivenCurrency, BigDecimal givenSellingValue, BigDecimal resultBuyingValue) {
        return amountOfGivenCurrency.multiply(givenSellingValue).divide(resultBuyingValue, 2, RoundingMode.CEILING);
    }

    private BigDecimal getResultBuyingValue(CurrencyDto resultCurrency) {
        return BigDecimal.valueOf(resultCurrency.getRates().get(0).getBid());
    }

    private BigDecimal getGivenSellingValue(CurrencyDto givenCurrency) {
        return BigDecimal.valueOf(givenCurrency.getRates().get(0).getAsk());
    }
}
