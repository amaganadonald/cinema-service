package com.amagana.cinema_service.service;

import java.util.List;

import com.amagana.cinema_service.dto.SalleRequestDto;
import com.amagana.cinema_service.dto.SalleResponseDto;
import com.amagana.cinema_service.entity.Salle;

public interface SalleService {

    List<SalleResponseDto> getAllSalle();
    List<Salle> getSalleByList(List<Integer> sallesId);
    Salle findSalleById(Long id);
    SalleResponseDto getSalleById(Long id);
    SalleResponseDto createNewSalle(SalleRequestDto salleRequestDto);
    SalleResponseDto updateSalle(SalleRequestDto salleRequestDto, Long id);
    void deleteSalle(Long id);
    
}
