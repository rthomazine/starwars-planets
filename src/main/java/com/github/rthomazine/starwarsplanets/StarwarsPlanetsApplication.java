package com.github.rthomazine.starwarsplanets;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@ComponentScan("com.github.rthomazine.starwarsplanets")
public class StarwarsPlanetsApplication {

	public static void main(String[] args) {
		ApplicationContext applicationContext = new SpringApplicationBuilder().bannerMode(Banner.Mode.OFF)
				.registerShutdownHook(true)
				.sources(StarwarsPlanetsApplication.class)
				.run(args);
	}

}
