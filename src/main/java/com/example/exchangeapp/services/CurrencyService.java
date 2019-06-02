package com.example.exchangeapp.services;

import com.example.exchangeapp.models.CurrencyDto;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
public class CurrencyService {


    public CurrencyDto getCurrency(String currencyCode){

        return getRestTemplate().getForObject("http://api.nbp.pl/api/exchangerates/rates/C/"+currencyCode+"/?format=json",
                CurrencyDto.class);
    }

    public BigDecimal countExchangeResult(BigDecimal amountOfGivenCurrency,
                                      CurrencyDto givenCurrency,
                                      CurrencyDto resultCurrency){

        BigDecimal givenSellingValue = getGivenSellingValue(givenCurrency);
        BigDecimal resultBuyingValue = getResultBuyingValue(resultCurrency);

        return countExchangeValue(amountOfGivenCurrency, givenSellingValue, resultBuyingValue);

    }

    public BigDecimal countExchangeValue(BigDecimal amountOfGivenCurrency, BigDecimal givenSellingValue, BigDecimal resultBuyingValue) {
        return amountOfGivenCurrency.multiply(givenSellingValue).divide(resultBuyingValue,2, RoundingMode.CEILING);
    }

    private BigDecimal getResultBuyingValue(CurrencyDto resultCurrency) {
        return BigDecimal.valueOf(resultCurrency.getRates().get(0).getBid());
    }

    private BigDecimal getGivenSellingValue(CurrencyDto givenCurrency) {
        return BigDecimal.valueOf(givenCurrency.getRates().get(0).getAsk());
    }


    @Bean
    private RestTemplate getRestTemplate(){
        return new RestTemplate();
    }
}
