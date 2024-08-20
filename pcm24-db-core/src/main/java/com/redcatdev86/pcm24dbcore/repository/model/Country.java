package com.redcatdev86.pcm24dbcore.repository.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class Country implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String countryCode;

    private String countryName;

    private Integer trainingStructure;

    private Integer cyclingPopularity;

}
