package com.example.exchangeapp.controllers;

import com.example.exchangeapp.models.CurrencyDto;
import com.example.exchangeapp.services.CurrencyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ExchangeApiController {

    private final CurrencyService currencyService;

    @GetMapping("/{currencyCode}")
    public ResponseEntity<CurrencyDto> showExchangedValue(@PathVariable String currencyCode){
        return ResponseEntity.ok(currencyService.getCurrency(currencyCode));
    }
}
