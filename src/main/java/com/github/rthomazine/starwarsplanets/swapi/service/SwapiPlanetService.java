package com.github.rthomazine.starwarsplanets.swapi.service;

import com.github.rthomazine.starwarsplanets.swapi.model.SwapiResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@Service
public class SwapiPlanetService {

    @Value("${swapi.endpoint}")
    private String swapiEndpoint;
    @Value("${swapi.resource.planet}")
    private String planetResource;
    @Resource
    RestTemplate restTemplate;

    public int getPlanetFilmViews(String planetName) {
        SwapiResponse swapiResponse = restTemplate.getForObject(
                String.format("%s%s/?search=%s", swapiEndpoint, planetResource, planetName),
                SwapiResponse.class);
        if (swapiResponse.getCount() > 0) {
            return swapiResponse.getResults().get(0).getFilms().size();
        }
        return 0;
    }

}
