package com.example.exchangeapp.controllers;

import com.example.exchangeapp.forms.UserInputForm;
import com.example.exchangeapp.services.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.math.BigDecimal;

@Controller
public class IndexController {

    private final CurrencyService currencyService;

    @Autowired
    public IndexController(CurrencyService currencyService) {
        this.currencyService = currencyService;
    }

    @GetMapping("/")
    public String index(Model model){
        model.addAttribute("userInputForm",new UserInputForm());
        return "index";
    }

    @PostMapping("/")
    public String index(@RequestParam("currencyCode1") String currencyCode1,
                        @RequestParam("currencyCode2") String currencyCode2,
                        @RequestParam("givenAmount") BigDecimal givenAmount,
                        @ModelAttribute("userInputForm") @Valid UserInputForm userInputForm,
                        BindingResult bindingResult,
                        Model model){

        userInputForm.setCurrencyCode1(currencyCode1);
        userInputForm.setCurrencyCode2(currencyCode2);
        userInputForm.setGivenAmount(givenAmount);


        if(bindingResult.hasErrors()){
//
            return "redirect:/index";

        }

        model.addAttribute("currency1",currencyService.getCurrency(currencyCode1));
        model.addAttribute("currency2",currencyService.getCurrency(currencyCode2));
        model.addAttribute("givenAmount",givenAmount);
        model.addAttribute("resultAmount",currencyService.countExchangeResult(givenAmount,currencyService.getCurrency(currencyCode1),
                currencyService.getCurrency(currencyCode2)));


        return "index";
    }
}
