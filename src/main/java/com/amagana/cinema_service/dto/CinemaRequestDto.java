package com.amagana.cinema_service.dto;


import java.util.List;

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
public class CinemaRequestDto {

    private String name;
    private double longitude;
    private double latitude;
    private double altitude;
    private int numberRoom;
    private Long townId;
    List<Integer> salleId;

}
