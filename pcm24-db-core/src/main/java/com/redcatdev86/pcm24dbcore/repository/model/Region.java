package com.redcatdev86.pcm24dbcore.repository.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

@Data
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class Region implements Serializable {

    private Long id;

    private String regionName;

    private Country country;
}
