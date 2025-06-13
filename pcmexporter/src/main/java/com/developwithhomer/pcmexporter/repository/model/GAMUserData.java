package com.developwithhomer.pcmexporter.repository.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "GAM_user")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GAMUserData {

    @Id
    @Column(name = "IDuser")
    private Long id;

    @Column(name = "game_sz_login")
    private String userLogin;

    @Column(name = "fkIDteam_duplicate")
    private Long teamId;

    @Column(name = "fkIDteam_national_duplicate")
    private Long teamNationalId;
}
