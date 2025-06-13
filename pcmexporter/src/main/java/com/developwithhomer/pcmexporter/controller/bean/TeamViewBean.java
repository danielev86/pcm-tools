package com.developwithhomer.pcmexporter.controller.bean;

import lombok.*;

import java.io.Serializable;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class TeamViewBean implements Serializable {

    private Long id;

    private String shortName;

    private String name;

    private String licensed;

    private Set<ScoutViewBean> scout;

    private Set<CyclistViewBean> cyclists;

}
