package com.developwithhomer.pcmexporter.controller;

import jakarta.inject.Inject;
import org.springframework.context.MessageSource;

import java.io.Serializable;
import java.util.Locale;

public class CommonBackingBean implements Serializable {

    @Inject
    private MessageSource messageSource;

    public String getMessage(String code) {
        return messageSource.getMessage(code, null, Locale.ITALIAN);
    }

    public String getMessage(String code, Object[] args) {
        return messageSource.getMessage(code, args, Locale.ITALIAN);
    }

}
