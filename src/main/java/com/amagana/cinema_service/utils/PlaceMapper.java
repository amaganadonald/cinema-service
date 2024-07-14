package com.amagana.cinema_service.utils;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.amagana.cinema_service.dto.PlaceRequestDto;
import com.amagana.cinema_service.dto.PlaceResponseDto;
import com.amagana.cinema_service.entity.Place;

@Mapper
public interface PlaceMapper {

    PlaceMapper INSTANCE = Mappers.getMapper(PlaceMapper.class);

    @Mapping(target = "tickets", ignore = true)
    @Mapping(target="salle", ignore=true)
    Place placeRequestDtoToPlace(PlaceRequestDto placeRequestDto);

    PlaceResponseDto placeToPlaceResponseDto(Place place);
}
