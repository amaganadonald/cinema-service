package com.amagana.cinema_service.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.amagana.cinema_service.dto.PlaceRequestDto;
import com.amagana.cinema_service.dto.PlaceResponseDto;
import com.amagana.cinema_service.entity.Place;
import com.amagana.cinema_service.entity.Salle;
import com.amagana.cinema_service.repository.PlaceRepository;
import com.amagana.cinema_service.service.PlaceService;
import com.amagana.cinema_service.service.SalleService;
import com.amagana.cinema_service.utils.PlaceMapper;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
@Transactional
public class PlaceServiceImpl implements PlaceService {

    private final PlaceRepository placeRepository;
    private final SalleService salleService;
    private static final PlaceMapper placeMapper = PlaceMapper.INSTANCE;

    @Override
    public List<PlaceResponseDto> getAllPlace() {
        return placeRepository.findAll().stream()
                              .map(placeMapper::placeToPlaceResponseDto)
                              .toList();
    }

    @Override
    public PlaceResponseDto getPlaceById(Long id) {
        return placeMapper.placeToPlaceResponseDto(findPlaceById(id));
    }

    @Override
    public Place findPlaceById(Long id) {
        return placeRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("Place not found "+id));
    }

    @Override
    public PlaceResponseDto createNewPlace(PlaceRequestDto placeRequestDto) {
        Place place = placeMapper.placeRequestDtoToPlace(placeRequestDto);
        Salle salle = salleService.findSalleById(placeRequestDto.getSalleId());
        place.setSalle(salle);
        return placeMapper.placeToPlaceResponseDto(
            placeRepository.save(place)
        );
    }

    @Override
    public PlaceResponseDto updatePlace(PlaceRequestDto placeRequestDto, Long id) {
        Place place = findPlaceById(id);
        Salle salle = salleService.findSalleById(placeRequestDto.getSalleId());
        salle.getPlaces().add(place);
        place.setSalle(salle);
        place.setAltitude(placeRequestDto.getAltitude());
        place.setLatitude(placeRequestDto.getLatitude());
        place.setLongitude(placeRequestDto.getLongitude());
        place.setNumber(placeRequestDto.getNumber());
        return placeMapper.placeToPlaceResponseDto(placeRepository.save(place));
    }

    @Override
    public void deletePlace(Long id) {
        placeRepository.delete(findPlaceById(id));
    }

}
