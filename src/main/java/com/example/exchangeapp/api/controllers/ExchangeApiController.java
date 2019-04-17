package com.example.exchangeapp.api.controllers;

import com.example.exchangeapp.services.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class ExchangeApiController {

    private final CurrencyService currencyService;

    @Autowired
    public ExchangeApiController(CurrencyService currencyService) {
        this.currencyService = currencyService;
    }

    @GetMapping("/{currencyCode}")
    public ResponseEntity showExchangedValue(@PathVariable("currencyCode") String currencyCode){
        return ResponseEntity.ok(currencyService.getCurrency(currencyCode));
    }

}
