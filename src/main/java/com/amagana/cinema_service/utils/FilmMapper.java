package com.amagana.cinema_service.utils;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.amagana.cinema_service.dto.FilmRequestDto;
import com.amagana.cinema_service.dto.FilmResponseDto;
import com.amagana.cinema_service.entity.Film;

@Mapper
public interface FilmMapper {

    FilmMapper INSTANCE = Mappers.getMapper(FilmMapper.class);

    
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "category", ignore = true)
    @Mapping(target = "projectionMovies", ignore = true)
    Film filmRequestDtoToFilm(FilmRequestDto filmRequestDto);

    FilmResponseDto filmToFilmResponseDto(Film film);
    
}
