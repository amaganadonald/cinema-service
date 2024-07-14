package com.amagana.cinema_service.dto;

import java.util.ArrayList;
import java.util.List;

import com.amagana.cinema_service.entity.Salle;
import com.amagana.cinema_service.entity.Town;

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
@ToString
@Builder
public class CinemaResponseDto {

    private Long id;
    private String name;
    private double longitude;
    private double latitude;
    private double altitude;
    private int numberRoom;
    private Town town;

    @Builder.Default
    private List<Salle> salle = new ArrayList<>();
}
