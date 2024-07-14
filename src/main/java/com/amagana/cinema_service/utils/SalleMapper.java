package com.amagana.cinema_service.utils;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.amagana.cinema_service.dto.SalleRequestDto;
import com.amagana.cinema_service.dto.SalleResponseDto;
import com.amagana.cinema_service.entity.Salle;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class SalleMapper {

    private final ModelMapper modelMapper;

    public Salle salleRequestDtoToSalle(SalleRequestDto salleRequestDto) {
        return modelMapper.map(salleRequestDto, Salle.class);
    }

    public SalleResponseDto salleToSalleResponseDto(Salle salle) {
        return modelMapper.map(salle, SalleResponseDto.class);
    }
}
