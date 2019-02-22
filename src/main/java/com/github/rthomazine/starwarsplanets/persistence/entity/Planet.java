package com.github.rthomazine.starwarsplanets.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "planet", catalog = "starwars")
@Data
@Accessors(chain = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Planet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "planet_id", unique = true, nullable = false)
    private Long id;
    @Column(name = "planet_name", unique = true, nullable = false)
    private String name;
    @Column(name = "planet_weather", nullable = false)
    private String weather;
    @Column(name = "planet_terrain", nullable = false)
    private String terrain;
    @Column(name = "planet_views", nullable = false)
    private Integer views;
    @Column(name = "created_date", updatable = false, insertable = false, nullable = true)
    private LocalDateTime created;
    @Column(name = "updated_date")
    private LocalDateTime updated;

}
