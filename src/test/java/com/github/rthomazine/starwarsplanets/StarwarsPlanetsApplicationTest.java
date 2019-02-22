package com.github.rthomazine.starwarsplanets;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;

import java.time.LocalDateTime;

@SpringBootTest(classes = StarwarsPlanetsApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration
@AutoConfigureMockMvc
public class StarwarsPlanetsApplicationTest {

    protected Object lastValue;
    protected MockHttpServletResponse httpResponse;
    protected static final Gson gson = new GsonBuilder()
            .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeConverter())
            .create();

}
