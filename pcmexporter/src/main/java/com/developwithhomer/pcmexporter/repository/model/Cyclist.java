package com.developwithhomer.pcmexporter.repository.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CollectionId;

import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "DYN_cyclist")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cyclist implements Serializable {

    @Id
    @Column(name = "IDCyclist")
    private long idCyclist;

    @Column(name = "gene_sz_lastname")
    private String lastName;

    @Column(name = "gene_sz_firstname")
    private String firstName;

    @Column(name = "gene_i_birthdate")
    private String birthdate;

    @Column(name = "gene_f_popularity")
    private BigDecimal popularity;

    @Column(name = "gene_i_size")
    private BigDecimal size;

    @Column(name = "gene_i_weight")
    private BigDecimal weight;

    @Column(name = "charac_i_plain")
    private BigDecimal characPlain;

    @Column(name = "limit_i_plain")
    private BigDecimal limitPlain;

    @Column(name = "charac_i_mountain")
    private BigDecimal characMountain;

    @Column(name = "limit_i_mountain")
    private BigDecimal limitMountain;

    @Column(name = "charac_i_medium_mountain")
    private BigDecimal characMediumMountain;

    @Column(name = "limit_i_medium_mountain")
    private BigDecimal limitMediumMountain;

    @Column(name = "charac_i_downhilling")
    private BigDecimal charachDownhill;

    @Column(name = "limit_i_downhilling")
    private BigDecimal limitDownhill;

    @Column(name = "charac_i_cobble")
    private BigDecimal characCobble;

    @Column(name = "limit_i_cobble")
    private BigDecimal limitCobble;

    @Column(name = "charac_i_timetrial")
    private BigDecimal characTimetrial;

    @Column(name = "limit_i_timetrial")
    private BigDecimal limitTimetrial;

    @Column(name = "charac_i_prologue")
    private BigDecimal characPrologue;

    @Column(name = "limit_i_prologue")
    private BigDecimal limitPrologue;

    @Column(name = "charac_i_sprint")
    private BigDecimal characSprint;

    @Column(name = "limit_i_sprint")
    private BigDecimal limitSprint;

    @Column(name = "charac_i_acceleration")
    private BigDecimal characAcceleration;

    @Column(name = "limit_i_acceleration")
    private BigDecimal limitAcceleration;

    @Column(name = "charac_i_endurance")
    private BigDecimal characEndurance;

    @Column(name = "limit_i_endurance")
    private BigDecimal limitEndurance;

    @Column(name = "charac_i_resistance")
    private BigDecimal characResistance;

    @Column(name = "limit_i_resistance")
    private BigDecimal limitResistance;

    @Column(name = "charac_i_recuperation")
    private BigDecimal characRecuperation;

    @Column(name = "limit_i_recuperation")
    private BigDecimal limitRecuperation;

    @Column(name = "charac_i_hill")
    private BigDecimal characHill;

    @Column(name = "limit_i_hill")
    private BigDecimal limitHill;

    @Column(name = "charac_i_baroudeur")
    private BigDecimal characBaroudeur;

    @Column(name = "limit_i_baroudeur")
    private BigDecimal limitBaroudeur;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fkIDteam", nullable = false)
    @JsonBackReference
    private Team team;

}
