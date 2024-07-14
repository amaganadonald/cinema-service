package com.amagana.cinema_service.utils;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.amagana.cinema_service.dto.CinemaRequestDto;
import com.amagana.cinema_service.dto.CinemaResponseDto;
import com.amagana.cinema_service.entity.Cinema;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class CinemaMapper {

    private final ModelMapper modelMapper;

    public Cinema cinemaRepositoryToCinema(CinemaRequestDto cinemaRequestDto) {
        return modelMapper.map(cinemaRequestDto, Cinema.class);
    }

    public CinemaResponseDto cinemaToCinemaResponseDto(Cinema cinema) {
        return modelMapper.map(cinema, CinemaResponseDto.class);
    }
}
