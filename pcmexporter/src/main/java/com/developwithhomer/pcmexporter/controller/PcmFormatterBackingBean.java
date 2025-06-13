package com.developwithhomer.pcmexporter.controller;

import jakarta.faces.bean.RequestScoped;
import jakarta.inject.Named;
import lombok.SneakyThrows;
import org.apache.commons.lang3.StringUtils;

import java.text.SimpleDateFormat;
import java.util.Date;

@Named("pcmFormatterController")
@RequestScoped
public class PcmFormatterBackingBean extends CommonBackingBean{

    private SimpleDateFormat sdf = new SimpleDateFormat("yyyymmdd");

    @SneakyThrows
    public Date formatDate(String date) {
        return StringUtils.isNotEmpty(date) ? sdf.parse(date) : null;
    }

}
