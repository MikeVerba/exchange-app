package com.example.exchangeapp.forms;

import org.springframework.stereotype.Component;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.Objects;

public class UserInputForm {

    @NotNull
    private String currencyCode1;
    @NotNull
    private String currencyCode2;
    @NotNull
    @Digits(integer = 10, fraction = 2)
    @Min(value = 0)
    private BigDecimal givenAmount;

    public String getCurrencyCode1() {
        return currencyCode1;
    }

    public void setCurrencyCode1(String currencyCode1) {
        this.currencyCode1 = currencyCode1;
    }

    public String getCurrencyCode2() {
        return currencyCode2;
    }

    public void setCurrencyCode2(String currencyCode2) {
        this.currencyCode2 = currencyCode2;
    }

    public BigDecimal getGivenAmount() {
        return givenAmount;
    }

    public void setGivenAmount(BigDecimal givenAmount) {
        this.givenAmount = givenAmount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserInputForm that = (UserInputForm) o;
        return Objects.equals(currencyCode1, that.currencyCode1) &&
                Objects.equals(currencyCode2, that.currencyCode2) &&
                Objects.equals(givenAmount, that.givenAmount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(currencyCode1, currencyCode2, givenAmount);
    }
}
