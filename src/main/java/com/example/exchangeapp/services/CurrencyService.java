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

        BigDecimal givenSellingValue = BigDecimal.valueOf(givenCurrency.getRates().get(0).getAsk());
        BigDecimal resultBuyingValue = BigDecimal.valueOf(resultCurrency.getRates().get(0).getBid());

        return amountOfGivenCurrency.multiply(givenSellingValue).divide(resultBuyingValue,2, RoundingMode.CEILING);

    }


    @Bean
    private RestTemplate getRestTemplate(){
        return new RestTemplate();
    }
}
