package com.developwithhomer.pcmexporter.repository.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "DYN_scout")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Scout implements Serializable {

    private static final long serialVersionUID = 4530471570023277493L;

    @Id
    @Column(name ="IDscout")
    private Long id;
    @Column(name = "gene_sz_firstname")
    private String firstName;
    @Column(name="gene_sz_lastname")
    private String lastName;
    @Column(name="finan_i_wage")
    private BigDecimal wage;
    @Column(name="gene_i_contract_end")
    private BigDecimal contractEnd;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fkIDteam", nullable = false)
    @JsonBackReference
    private Team team;
}
