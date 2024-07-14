package com.amagana.cinema_service.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.amagana.cinema_service.dto.TownRequestDto;
import com.amagana.cinema_service.dto.TownResponse;
import com.amagana.cinema_service.dto.TownResponseDto;
import com.amagana.cinema_service.entity.Town;

public interface TownService {

    List<TownResponseDto> getAllTown();
    TownResponseDto getTownById(Long id);
    TownResponseDto createNewTown(TownRequestDto townRequestDto);
    TownResponseDto updateTown(TownRequestDto townRequestDto, Long id);
    Page<TownResponse> getTownByPage(int page, int size);
    Town findTownById(Long id);
    void deleteTown(Long id);
}
