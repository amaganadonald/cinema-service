package com.amagana.cinema_service.service;

import java.util.List;

import com.amagana.cinema_service.dto.PlaceRequestDto;
import com.amagana.cinema_service.dto.PlaceResponseDto;
import com.amagana.cinema_service.entity.Place;

public interface PlaceService {

    List<PlaceResponseDto> getAllPlace();
    PlaceResponseDto getPlaceById(Long id);
    Place findPlaceById(Long id);
    PlaceResponseDto createNewPlace(PlaceRequestDto placeRequestDto);
    PlaceResponseDto updatePlace(PlaceRequestDto placeRequestDto, Long id);
    void deletePlace(Long id);
}
