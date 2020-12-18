package com.example.exchangeapp.forms;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserInputForm {

    @NotNull
    String currencyCode1;
    @NotNull
    String currencyCode2;
    @NotNull
    @Digits(integer = 10, fraction = 2)
    @Min(value = 0)
    BigDecimal givenAmount;
}
