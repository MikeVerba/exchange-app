package com.example.exchangeapp.services;

import com.example.exchangeapp.models.CurrencyDto;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CurrencyService {


    public CurrencyDto getCurrency(String currencyCode){

        CurrencyDto returnDto = getRestTemplate().getForObject("http://api.nbp.pl/api/exchangerates/rates/C/"+currencyCode+"/?format=json",
                CurrencyDto.class);

        System.out.println(returnDto.getRates().get(0).getBid());
        System.out.println(returnDto.getRates().get(0).getAsk());

        return returnDto;
    }

    public double countExchangeResult(double amountOfGivenCurrency,
                                      CurrencyDto givenCurrency,
                                      CurrencyDto resultCurrency){

        double givenSellingValue = givenCurrency.getRates().get(0).getAsk();
        double resultBuyingValue = resultCurrency.getRates().get(0).getBid();

        return (amountOfGivenCurrency*givenSellingValue)/resultBuyingValue;
    }


    @Bean
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }
}
