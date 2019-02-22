package com.github.rthomazine.starwarsplanets.swapi.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class SwapiResponse {

    private Integer count;
    private String next;
    private String previous;
    private List<SwapiPlanetResponse> results;

}
