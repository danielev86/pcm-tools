package com.developwithhomer.pcmexporter.controller.bean;

import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class ScoutViewBean implements Serializable {

    private Long id;
    private String firstName;
    private String lastName;
    private BigDecimal wage;
    private BigDecimal contractEnd;
}
