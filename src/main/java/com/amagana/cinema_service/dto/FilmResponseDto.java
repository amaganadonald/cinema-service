package com.amagana.cinema_service.dto;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import com.amagana.cinema_service.entity.Category;
import com.amagana.cinema_service.entity.ProjectionMovie;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class FilmResponseDto {

    private Long id;
    private String title;
    private double duration;
    private String realisator;
    private String description;
    private String picture;
    private LocalDate outDate;
    private Category category;

    @Builder.Default
    private Set<ProjectionMovie> projectionMovies = new HashSet<>();
}
