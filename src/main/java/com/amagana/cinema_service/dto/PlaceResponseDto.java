package com.amagana.cinema_service.dto;

import java.util.HashSet;
import java.util.Set;

import com.amagana.cinema_service.entity.Ticket;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
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
@EqualsAndHashCode
public class PlaceResponseDto {

    private Long id;
    private int number;
    private double longitude;
    private double latitude;
    private double altitude;

    @Builder.Default
    private Set<Ticket> tickets = new HashSet<>();
}
