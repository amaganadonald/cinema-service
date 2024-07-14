package com.amagana.cinema_service.dto;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.amagana.cinema_service.entity.Cinema;
import com.amagana.cinema_service.entity.Place;
import com.amagana.cinema_service.entity.ProjectionMovie;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@Builder
@EqualsAndHashCode
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SalleResponseDto {

    private Long id;
    private String name;
    private int numberSeat;
    private Cinema cinema;

    @Builder.Default
    private List<Place> places = new ArrayList<>();

    @Builder.Default
    private Set<ProjectionMovie> projectionMovies = new HashSet<>();
}
