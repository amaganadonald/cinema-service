package com.amagana.cinema_service.service;

import java.util.List;

import com.amagana.cinema_service.dto.FilmRequestDto;
import com.amagana.cinema_service.dto.FilmResponseDto;
import com.amagana.cinema_service.entity.Film;

public interface FilmService {

    List<FilmResponseDto> getAllFilm();
    Film findFilmById(Long id);
    FilmResponseDto getFilmById(Long id);
    FilmResponseDto createNewFilm(FilmRequestDto filmRequestDto);
    FilmResponseDto updateFilm(FilmRequestDto filmRequestDto, Long id);
    void deleteFilm(Long id);
}
