package com.github.rthomazine.starwarsplanets.persistence.repository;

import com.github.rthomazine.starwarsplanets.persistence.entity.Planet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PlanetRepository extends JpaRepository<Planet, Long> {

    public boolean existsByName(String name);
    public Optional<Planet> findByName(String name);

}
