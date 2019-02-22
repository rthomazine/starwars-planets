package com.github.rthomazine.starwarsplanets.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.mvc.ProxyExchange;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "StarWarsAPI Proxy")
@RestController
public class StarWarsAPI {

    @Value("${swapi.endpoint}")
    private String swapiEndpoint;

    @GetMapping("/swapi/**")
    @ApiOperation("SWAPI interaction, supports only get methods")
    public ResponseEntity getSwapiPlanets(ProxyExchange<Object> proxy) {
        // TODO: change to Spring Cloud Gateway
        String swapiPath = proxy.path("/swapi/");
        return proxy.uri(swapiEndpoint + swapiPath).get();
    }

}
