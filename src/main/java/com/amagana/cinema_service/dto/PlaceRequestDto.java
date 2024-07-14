package com.amagana.cinema_service.dto;

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
public class PlaceRequestDto {

    private Long id;
    private int number;
    private double longitude;
    private double latitude;
    private double altitude;
    private Long salleId;
}
