package com.amagana.cinema_service.utils;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.amagana.cinema_service.dto.ProjectionMovieRequestDto;
import com.amagana.cinema_service.dto.ProjectionMovieResponseDto;
import com.amagana.cinema_service.entity.ProjectionMovie;

@Mapper
public interface ProjectionMovieMapper {

    ProjectionMovieMapper INSTANCE = Mappers.getMapper(ProjectionMovieMapper.class);

    @Mapping(target = "film", ignore = true)
    @Mapping(target = "salle", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "sessions", ignore = true)
    @Mapping(target = "tickets", ignore = true)
    ProjectionMovie projectionMovieRequestDtoToProjectionMovie(ProjectionMovieRequestDto projectionMovieRequestDto);

    ProjectionMovieResponseDto projectionMovieToProjectionMovieResponseDto(ProjectionMovie projectionMovie);
}
