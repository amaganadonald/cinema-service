package com.amagana.cinema_service.utils;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.amagana.cinema_service.dto.TownRequestDto;
import com.amagana.cinema_service.dto.TownResponseDto;
import com.amagana.cinema_service.entity.Town;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class TownMapper {

    private final ModelMapper modelMapper;

    public Town townRequestDtoTown(TownRequestDto townRequestDto) {
        return modelMapper.map(townRequestDto, Town.class);
    }

    public TownResponseDto townToTownResponseDto(Town town) {
        return modelMapper.map(town, TownResponseDto.class);
    }
}
