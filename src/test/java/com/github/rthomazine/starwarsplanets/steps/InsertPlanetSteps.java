package com.github.rthomazine.starwarsplanets.steps;

import com.github.rthomazine.starwarsplanets.StarwarsPlanetsApplicationTest;
import com.github.rthomazine.starwarsplanets.model.PlanetModel;
import com.github.rthomazine.starwarsplanets.persistence.entity.Planet;
import com.github.rthomazine.starwarsplanets.persistence.repository.PlanetRepository;
import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class InsertPlanetSteps extends StarwarsPlanetsApplicationTest {

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private MockMvc mvc;
    @Autowired
    private PlanetRepository planetRepository;

    @Given("^the database is empty$")
    public void the_database_is_empty() throws Throwable {
        planetRepository.deleteAll();
    }

    @When("^call the insertion API with POST method \"([^\"]*)\" with given JSON body:$")
    public void call_the_insertion_API_with_POST_method_with_given_JSON_body(String apiPath, String jsonRequest) throws Throwable {
        ResultActions resultActions = mvc.perform(MockMvcRequestBuilders.post(apiPath)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonRequest));
        httpResponse = resultActions.andReturn().getResponse();
    }

    @Then("^the insertion API should return HTTP status code: (\\d+)$")
    public void the_insertion_API_should_return_HTTP_status_code(int statusCode) throws Throwable {
        assertTrue(httpResponse.getStatus() == statusCode);
    }

    @Then("^the planet should be inserted into database$")
    public void the_planet_should_be_inserted_into_database() throws Throwable {
        Planet planetFromRequest = gson.fromJson(httpResponse.getContentAsString(), Planet.class);
        Planet planetFromRepository = planetRepository.findByName(planetFromRequest.getName()).get();
        assertNotNull(planetFromRepository);
        assertTrue(planetFromRequest.getId().longValue() == planetFromRepository.getId().longValue());
        assertTrue(planetFromRequest.getTerrain().equals(planetFromRepository.getTerrain()));
        assertTrue(planetFromRequest.getWeather().equals(planetFromRepository.getWeather()));
        assertTrue(planetFromRequest.getViews().intValue() == planetFromRepository.getViews().intValue());
    }

    @Then("^the planet inserted should look like:$")
    public void the_planet_inserted_should_look_like(List<PlanetModel> planets) throws Throwable {
        assertTrue(planets.size() == 1);
        PlanetModel model = planets.get(0);
        Planet planetFromRequest = gson.fromJson(httpResponse.getContentAsString(), Planet.class);
        assertTrue(planetFromRequest.getTerrain().equals(model.getTerrain()));
        assertTrue(planetFromRequest.getWeather().equals(model.getWeather()));
        assertTrue(planetFromRequest.getViews().intValue() == model.getViews());
    }

}
