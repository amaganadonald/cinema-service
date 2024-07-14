package com.amagana.cinema_service.dto;

import java.util.ArrayList;
import java.util.List;

import com.amagana.cinema_service.entity.Cinema;

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
public class TownResponseDto {

    private Long id;
    private String name;
    private double longitude;
    private double latitude;
    private double altitude;

    @Builder.Default
    private List<Cinema> cinema = new ArrayList<>();
}
