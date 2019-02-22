package com.github.rthomazine.starwarsplanets.steps;

import com.github.rthomazine.starwarsplanets.StarwarsPlanetsApplicationTest;
import com.github.rthomazine.starwarsplanets.model.PlanetModel;
import com.github.rthomazine.starwarsplanets.persistence.entity.Planet;
import com.github.rthomazine.starwarsplanets.persistence.repository.PlanetRepository;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class ListPlanetSteps extends StarwarsPlanetsApplicationTest {

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private MockMvc mvc;
    @Autowired
    private PlanetRepository planetRepository;

    @Given("^the following planets exist within local database$")
    public void the_following_planets_exist_within_local_database(List<Planet> planets) throws Throwable {
        List<Planet> repositoryPlanets = planetRepository.findAll();
        List<String> notPersisted = CollectionUtils.disjunction(
                repositoryPlanets.stream().map(Planet::getName).collect(Collectors.toList()),
                planets.stream().map(Planet::getName).collect(Collectors.toList()))
                .stream()
                .collect(Collectors.toList());
        if (notPersisted.size() > 0) {
            notPersisted.forEach(planet -> planets.stream()
                    .filter(p -> p.getName().equals(planet))
                    .forEach(planetToPersist -> planetRepository.saveAndFlush(planetToPersist)));
        }
    }

    @When("^call the list API with GET method \"([^\"]*)\"$")
    public void call_the_list_API_with_GET_method(String apiPath) throws Throwable {
        ResultActions resultActions = mvc.perform(MockMvcRequestBuilders.get(apiPath)
                .accept(MediaType.APPLICATION_JSON));
        httpResponse = resultActions.andReturn().getResponse();
    }

    @Then("^the list API should return HTTP status code: (\\d+)$")
    public void the_list_API_should_return_HTTP_status_code(int statusCode) throws Throwable {
        assertTrue(httpResponse.getStatus() == statusCode);
    }

    @And("^the list of all planets that exist within local database should look like:$")
    public void the_list_of_all_planets_that_exist_within_local_database_should_look_like(List<PlanetModel> planets) throws Throwable {
        List<Planet> planetFromResponse = gson.fromJson(httpResponse.getContentAsString(), List.class);
        assertNotNull(planetFromResponse);
        assertTrue(planets.size() == planetFromResponse.size());
        Optional<Planet> planet = planetFromResponse.stream().filter(p -> p.getName().equals("Alderaan")).findFirst();
        assertTrue(planet.isPresent());
        lastValue = planet.get().getId();
    }

    @When("^call the get by name API with GET method \"([^\"]*)\"$")
    public void call_the_get_by_name_API_with_GET_method(String apiPath) throws Throwable {
        ResultActions resultActions = mvc.perform(MockMvcRequestBuilders.get(apiPath)
                .accept(MediaType.APPLICATION_JSON));
        httpResponse = resultActions.andReturn().getResponse();
    }

    @And("^the returned planet from local database should look like:$")
    public void the_returned_planet_from_local_database_should_look_like(List<PlanetModel> planets) throws Throwable {
        Planet planetFromResponse = gson.fromJson(httpResponse.getContentAsString(), Planet.class);
        assertNotNull(planetFromResponse);
        PlanetModel planetModel = planets.get(0);
        assertTrue(planetFromResponse.getName().equals(planetModel.getName()));
        assertTrue(planetFromResponse.getWeather().equals(planetModel.getWeather()));
        assertTrue(planetFromResponse.getTerrain().equals(planetModel.getTerrain()));
        assertTrue(planetFromResponse.getViews().intValue() == planetModel.getViews());
    }

    @When("^call the get by id API with GET method \"([^\"]*)\"$")
    public void call_the_get_by_id_API_with_GET_method(String apiPath) throws Throwable {
        assertNotNull(lastValue);
        assertTrue(lastValue instanceof Long);
        ResultActions resultActions = mvc.perform(MockMvcRequestBuilders.get(String.format(apiPath, lastValue))
                .accept(MediaType.APPLICATION_JSON));
        httpResponse = resultActions.andReturn().getResponse();
        throw new PendingException();
    }
}
