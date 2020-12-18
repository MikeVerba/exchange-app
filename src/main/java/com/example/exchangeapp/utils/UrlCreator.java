package com.example.exchangeapp.utils;

import lombok.experimental.UtilityClass;

@UtilityClass
public class UrlCreator {
    private static final String DELIMITER = "/";
    private static final String FORMAT_VARIABLE_SIGN = "?format=";

    public String createUrl(String baseUrl, String exchangeGroup, String currencyCode, String format) {
        return createBase(baseUrl, exchangeGroup) + currencyCode + DELIMITER + FORMAT_VARIABLE_SIGN + format;
    }

    private String createBase(String baseUrl, String exchangeGroup) {
        return baseUrl + DELIMITER + exchangeGroup + DELIMITER;
    }
}
