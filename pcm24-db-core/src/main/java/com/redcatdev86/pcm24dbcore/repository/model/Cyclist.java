package com.redcatdev86.pcm24dbcore.repository.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class Cyclist implements Serializable {

    private Long id;

    private String firstName;

    private String lastName;

    private Date birthdate;
    private BigDecimal size;
    private BigDecimal weight;
    private BigDecimal cPlain;
    private BigDecimal lPlain;
    private BigDecimal cMountain;
    private BigDecimal lMountain;
    private BigDecimal cMediumMontain;
    private BigDecimal lMediumMontain;
    private BigDecimal cDownhilling;
    private BigDecimal lDownhilling;
    private BigDecimal cCobble;
    private BigDecimal lCobble;
    private BigDecimal cTimetrial;
    private BigDecimal lTimetrial;
    private BigDecimal cPrologue;
    private BigDecimal lPrologue;
    private BigDecimal cSprint;
    private BigDecimal lSprint;
    private BigDecimal cAcceleration;
    private BigDecimal lAcceleration;
    private BigDecimal cEndurance;
    private BigDecimal lEndurance;
    private BigDecimal cResistance;
    private BigDecimal lResistance;
    private BigDecimal cRecuperation;
    private BigDecimal lRecuperation;
    private BigDecimal cHill;
    private BigDecimal lHill;
    private BigDecimal cBaroudeur;
    private BigDecimal lBaroudeur;
    private Region region;
}
