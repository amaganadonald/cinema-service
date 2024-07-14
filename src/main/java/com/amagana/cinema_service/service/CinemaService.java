package com.amagana.cinema_service.service;

import java.util.List;

import com.amagana.cinema_service.dto.CinemaRequestDto;
import com.amagana.cinema_service.dto.CinemaResponseDto;
import com.amagana.cinema_service.entity.Cinema;

public interface CinemaService {

   List<CinemaResponseDto> getAllCinema(); 
   CinemaResponseDto getCinemaById(Long id);
   Cinema findCinemaById(Long id);
   CinemaResponseDto createNewCinema(CinemaRequestDto cinemaRequestDto);
   CinemaResponseDto updateCinema(CinemaRequestDto cinemaRequestDto, Long id);
   void deleteCinema(Long id);
} 