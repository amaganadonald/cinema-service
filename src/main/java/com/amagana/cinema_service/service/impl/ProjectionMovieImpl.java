package com.amagana.cinema_service.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.amagana.cinema_service.dto.ProjectionMovieRequestDto;
import com.amagana.cinema_service.dto.ProjectionMovieResponseDto;
import com.amagana.cinema_service.entity.Film;
import com.amagana.cinema_service.entity.ProjectionMovie;
import com.amagana.cinema_service.entity.Salle;
import com.amagana.cinema_service.entity.Session;
import com.amagana.cinema_service.repository.ProjectionMovieRepository;
import com.amagana.cinema_service.service.FilmService;
import com.amagana.cinema_service.service.ProjectionMovieService;
import com.amagana.cinema_service.service.SalleService;
import com.amagana.cinema_service.service.SessionService;
import com.amagana.cinema_service.utils.ProjectionMovieMapper;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
@Transactional
public class ProjectionMovieImpl implements ProjectionMovieService {

    private final ProjectionMovieRepository projectionMovieRepository;
    private final FilmService filmService;
    private final SalleService salleService;
    private final SessionService sessionService;

    private static final ProjectionMovieMapper projectionMovieMapper = ProjectionMovieMapper.INSTANCE;

    @Override
    public List<ProjectionMovieResponseDto> getAllProjections() {
        return projectionMovieRepository.findAll().stream()
                                        .map(projectionMovieMapper::projectionMovieToProjectionMovieResponseDto)
                                        .toList();
    }

    @Override
    public ProjectionMovieResponseDto getProjectionMovieById(Long id) {
        return projectionMovieMapper.projectionMovieToProjectionMovieResponseDto(findProjectionMovie(id));
    }

    @Override
    public ProjectionMovie findProjectionMovie(Long id) {
        return projectionMovieRepository.findById(id).orElseThrow(()-> 
                            new EntityNotFoundException("Movie Projection not found with id: "+ id));
    }

    @Override
    public ProjectionMovieResponseDto createNewProjectionMovie(ProjectionMovieRequestDto projectionMovieRequestDto) {
        ProjectionMovie projectionMovie = projectionMovieMapper.projectionMovieRequestDtoToProjectionMovie(projectionMovieRequestDto);
        Film film = filmService.findFilmById(projectionMovieRequestDto.filmId());
        Salle salle = salleService.findSalleById(projectionMovieRequestDto.salleId());
        Session session = sessionService.findSessionById(projectionMovieRequestDto.sessionId());
        projectionMovie.setFilm(film);
        projectionMovie.setSalle(salle);
        projectionMovie.setSessions(session);
        return projectionMovieMapper.projectionMovieToProjectionMovieResponseDto(
            projectionMovieRepository.save(projectionMovie)
        );
    }

    @Override
    public ProjectionMovieResponseDto updateProjectionMovie(ProjectionMovieRequestDto projectionMovieRequestDto,
            Long id) {
        ProjectionMovie projectionMovie = findProjectionMovie(id);
        Film film = filmService.findFilmById(projectionMovieRequestDto.filmId());
        Salle salle = salleService.findSalleById(projectionMovieRequestDto.salleId());
        Session session = sessionService.findSessionById(projectionMovieRequestDto.sessionId());
        film.getProjectionMovies().add(projectionMovie);
        projectionMovie.setFilm(film);
        salle.getProjectionMovies().add(projectionMovie);
        projectionMovie.setSalle(salle);
        projectionMovie.setPrice(projectionMovieRequestDto.price());
        projectionMovie.setSessions(session);
        projectionMovie.setProjectionDate(projectionMovieRequestDto.projectionDate());
        return projectionMovieMapper.projectionMovieToProjectionMovieResponseDto(
            projectionMovieRepository.save(projectionMovie)
        );
    }

    @Override
    public void deleteProjectionMovie(Long id) {
        projectionMovieRepository.delete(findProjectionMovie(id));
    }

}
