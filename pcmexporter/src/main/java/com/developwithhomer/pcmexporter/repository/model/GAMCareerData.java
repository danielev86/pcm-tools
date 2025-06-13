package com.developwithhomer.pcmexporter.repository.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "GAM_career_data")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GAMCareerData implements Serializable {

    @Id
    @Column(name = "UID")
    private Long uid;

    @Column(name = "CONSTANT")
    private String parameterCode;

    @Column(name = "value")
    private BigDecimal parameterValue;

}
