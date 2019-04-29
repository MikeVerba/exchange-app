package com.example.exchangeapp.controllers;

import com.example.exchangeapp.services.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;

@Controller
public class IndexController {

    private final CurrencyService currencyService;

    @Autowired
    public IndexController(CurrencyService currencyService) {
        this.currencyService = currencyService;
    }

    @GetMapping("/")
    public String index(){
        return "index";
    }

    @PostMapping("/")
    public String index(@RequestParam("currencyCode1") String currencyCode1,
                        @RequestParam("currencyCode2") String currencyCode2,
                        @RequestParam("givenAmount") BigDecimal givenAmount,
                        Model model){
        model.addAttribute("currency1",currencyService.getCurrency(currencyCode1));
        model.addAttribute("currency2",currencyService.getCurrency(currencyCode2));
        model.addAttribute("givenAmount",givenAmount);
        model.addAttribute("resultAmount",currencyService.countExchangeResult(givenAmount,currencyService.getCurrency(currencyCode1),
                currencyService.getCurrency(currencyCode2)));
        return "index";
    }
}
