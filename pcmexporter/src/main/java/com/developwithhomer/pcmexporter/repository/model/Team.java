package com.developwithhomer.pcmexporter.repository.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "DYN_team")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Team implements Serializable {


    @Id
    @Column(name = "IDteam")
    private Long id;

    @Column(name = "gene_sz_shortname")
    private String shortName;

    @Column(name = "gene_sz_name")
    private String name;

    @Column(name = "gene_b_licensed")
    private String licensed;

    @OneToMany(mappedBy = "team",fetch = FetchType.LAZY)
    @JsonManagedReference
    private Set<Cyclist> cyclists;
    @OneToMany(mappedBy = "team",fetch = FetchType.LAZY)
    @JsonManagedReference
    private Set<Scout> scout;

}
