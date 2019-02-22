package com.github.rthomazine.starwarsplanets.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PlanetModel {

    private int id;
    private String name;
    private String weather;
    private String terrain;
    private int views;
    private String created;
    private String updated;

}
