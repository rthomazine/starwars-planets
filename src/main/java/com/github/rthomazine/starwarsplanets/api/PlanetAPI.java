package com.github.rthomazine.starwarsplanets.api;

import com.github.rthomazine.starwarsplanets.api.exception.BadRequestException;
import com.github.rthomazine.starwarsplanets.api.exception.NotFoundException;
import com.github.rthomazine.starwarsplanets.api.model.PlanetRequest;
import com.github.rthomazine.starwarsplanets.persistence.entity.Planet;
import com.github.rthomazine.starwarsplanets.persistence.repository.PlanetRepository;
import com.github.rthomazine.starwarsplanets.swapi.service.SwapiPlanetService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

@Api(tags = "StarWars Planets API")
@RequestMapping(path = "/api/planets", produces = {MediaType.APPLICATION_JSON_VALUE})
@RestController
public class PlanetAPI {

    @Resource
    private PlanetRepository planetRepository;
    @Resource
    private SwapiPlanetService swapiPlanetService;

    @PostMapping
    @ApiOperation("Insert a new StarWars planet into planets database")
    public ResponseEntity insertPlanet(@RequestBody PlanetRequest planetRequest) throws Exception {
        if (planetRepository.existsByName(planetRequest.getName()))
            throw new BadRequestException("The planet provided already exists");
        int planetViews = swapiPlanetService.getPlanetFilmViews(planetRequest.getName());
        Planet planet = planetRepository.saveAndFlush(Planet.builder()
                .name(planetRequest.getName())
                .terrain(planetRequest.getTerrain())
                .weather(planetRequest.getWeather())
                .views(planetViews)
                .build());
        return ResponseEntity.ok(planet);
    }

    @GetMapping
    @ApiOperation("List all StarWars planets from planets database")
    public ResponseEntity listAllPlanets() {
        List<Planet> planets = planetRepository.findAll();
        return ResponseEntity.ok(planets);
    }

    @GetMapping("/{id}")
    @ApiOperation("Retrieve a StarWars planet from planets database given its id")
    public ResponseEntity getPlanetById(@PathVariable Long id) throws Exception {
        Optional<Planet> planet = planetRepository.findById(id);
        planet.orElseThrow(() -> new NotFoundException(String.format("Resource with id %s not found", id)));
        return ResponseEntity.ok(planet.get());
    }

    @GetMapping("/planet")
    @ApiOperation("Retrieve a StarWars planet from planets database given its name")
    public ResponseEntity getPlanetByName(@RequestParam("name") Optional<String> name) throws Exception {
        name.orElseThrow(() -> new BadRequestException("The planet name is not present or is not valid"));
        Optional<Planet> planet = planetRepository.findByName(name.get());
        planet.orElseThrow(() -> new NotFoundException(String.format("Resource with name %s not found", name)));
        return ResponseEntity.ok(planet);
    }

    @DeleteMapping("/{id}")
    @ApiOperation("Delete a StarWars planet from planets database given its id")
    public ResponseEntity deletePlanet(@PathVariable Long id) {
        planetRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
