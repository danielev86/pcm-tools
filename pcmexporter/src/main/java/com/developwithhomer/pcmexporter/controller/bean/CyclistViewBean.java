package com.developwithhomer.pcmexporter.controller.bean;

import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class CyclistViewBean implements Serializable {

    private long idCyclist;

    private String lastName;

    private String firstName;

    private String birthdate;

    private BigDecimal popularity;

    private BigDecimal size;

    private BigDecimal weight;

    private BigDecimal characPlain;

    private BigDecimal limitPlain;

    private BigDecimal characMountain;

    private BigDecimal limitMountain;

    private BigDecimal characMediumMountain;

    private BigDecimal limitMediumMountain;

    private BigDecimal charachDownhill;

    private BigDecimal limitDownhill;

    private BigDecimal characCobble;

    private BigDecimal limitCobble;

    private BigDecimal characTimetrial;

    private BigDecimal limitTimetrial;

    private BigDecimal characPrologue;

    private BigDecimal limitPrologue;

    private BigDecimal characSprint;

    private BigDecimal limitSprint;

    private BigDecimal characAcceleration;

    private BigDecimal limitAcceleration;

    private BigDecimal characEndurance;

    private BigDecimal limitEndurance;

    private BigDecimal characResistance;

    private BigDecimal limitResistance;

    private BigDecimal characRecuperation;

    private BigDecimal limitRecuperation;

    private BigDecimal characHill;

    private BigDecimal limitHill;

    private BigDecimal characBaroudeur;

    private BigDecimal limitBaroudeur;

    private Date birthdayDate;

}
