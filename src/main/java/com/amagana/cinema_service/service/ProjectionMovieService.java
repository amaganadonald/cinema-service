package com.amagana.cinema_service.service;

import java.util.List;

import com.amagana.cinema_service.dto.ProjectionMovieRequestDto;
import com.amagana.cinema_service.dto.ProjectionMovieResponseDto;
import com.amagana.cinema_service.entity.ProjectionMovie;

public interface ProjectionMovieService {

    List<ProjectionMovieResponseDto> getAllProjections();
    ProjectionMovieResponseDto getProjectionMovieById(Long id);
    ProjectionMovie findProjectionMovie(Long id);
    ProjectionMovieResponseDto createNewProjectionMovie(ProjectionMovieRequestDto projectionMovieRequestDto);
    ProjectionMovieResponseDto updateProjectionMovie(ProjectionMovieRequestDto projectionMovieRequestDto, Long id);
    void deleteProjectionMovie(Long id);
}
