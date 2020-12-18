package com.example.exchangeapp.services;

import com.example.exchangeapp.config.ApiProperties;
import com.example.exchangeapp.models.CurrencyDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CurrencyServiceTest {

    CurrencyService currencyService;

    CurrencyDto givenCurrency;

    CurrencyDto resultCurrency;

    List<CurrencyDto.RatesDto> ratesDtoListGiven;

    List<CurrencyDto.RatesDto> ratesDtoListResult;

    CurrencyDto.RatesDto ratesDtoGiven;

    CurrencyDto.RatesDto ratesDtoResult;

    ApiProperties apiProperties;

    @Mock
    RestTemplate restTemplate;



    @BeforeEach
    void setUp() {

        ratesDtoGiven = new CurrencyDto.RatesDto();
        ratesDtoGiven.setAsk(12);
        ratesDtoGiven.setBid(12);


        ratesDtoListGiven = new ArrayList<>();
        ratesDtoListGiven.add(ratesDtoGiven);


        givenCurrency = new CurrencyDto();
        givenCurrency.setRates(ratesDtoListGiven);
        givenCurrency.setDescription("Dolar");
        givenCurrency.setCurrencyCode("USD");


        ratesDtoResult = new CurrencyDto.RatesDto();
        ratesDtoResult.setBid(23);
        ratesDtoResult.setAsk(23);

        ratesDtoListResult = new ArrayList<>();
        ratesDtoListResult.add(ratesDtoResult);


        resultCurrency = new CurrencyDto();
        resultCurrency.setRates(ratesDtoListResult);
        resultCurrency.setDescription("Frank");
        resultCurrency.setCurrencyCode("CHF");

        apiProperties = new ApiProperties();
        apiProperties.setBaseUrl("www.test.com");
        apiProperties.setExchangeGroup("B");
        apiProperties.setFormatVariable("json");

        currencyService = new CurrencyService(restTemplate,apiProperties);
    }

    @Test
    void getCurrency() {
    }

    @Test
    void countExchangeResult() {

        BigDecimal result = currencyService.countExchangeResult(new BigDecimal(123),
                givenCurrency,
                resultCurrency);

        assertEquals(result,new BigDecimal(64.18).round(new MathContext(4)));

    }

    @Test
    void countExchangeValue() {

       BigDecimal result =  currencyService.countExchangeValue(new BigDecimal(230),
               new BigDecimal(789),
               new BigDecimal(10));

        assertEquals(result.compareTo(new BigDecimal(18147.00)),0);

    }


}