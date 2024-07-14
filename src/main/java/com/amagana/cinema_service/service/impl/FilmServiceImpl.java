package com.amagana.cinema_service.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.amagana.cinema_service.dto.FilmRequestDto;
import com.amagana.cinema_service.dto.FilmResponseDto;
import com.amagana.cinema_service.entity.Category;
import com.amagana.cinema_service.entity.Film;
import com.amagana.cinema_service.repository.FilmRepository;
import com.amagana.cinema_service.service.CategoryService;
import com.amagana.cinema_service.service.FilmService;
import com.amagana.cinema_service.utils.FilmMapper;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class FilmServiceImpl implements FilmService {

    private final FilmRepository filmRepository;
    private final CategoryService categoryService;
    private static final FilmMapper filmMapper = FilmMapper.INSTANCE;

    @Override
    public List<FilmResponseDto> getAllFilm() {
        return filmRepository.findAll().stream()
                             .map(filmMapper::filmToFilmResponseDto)
                             .toList();
    }

    @Override
    public FilmResponseDto getFilmById(Long id) {
        return filmMapper.filmToFilmResponseDto(findFilmById(id));
    }

    @Override
    public FilmResponseDto createNewFilm(FilmRequestDto filmRequestDto) {
        Film film = filmMapper.filmRequestDtoToFilm(filmRequestDto);
        if (filmRequestDto.CategoryId() != null) {
            Category category = categoryService.findCategoryById(filmRequestDto.CategoryId());
            film.setCategory(category);
        }
        return filmMapper.filmToFilmResponseDto(filmRepository.save(film));
    }

    @Override
    public FilmResponseDto updateFilm(FilmRequestDto filmRequestDto, Long id) {
        Film film = findFilmById(id);
        if (filmRequestDto.CategoryId() != null) {
            Category category = categoryService.findCategoryById(filmRequestDto.CategoryId());
            film.setCategory(category);
        }
        film.setDescription(filmRequestDto.description());
        film.setDuration(filmRequestDto.duration());
        film.setOutDate(filmRequestDto.outDate());
        film.setRealisator(filmRequestDto.realisator());
        film.setTitle(filmRequestDto.title());
        return filmMapper.filmToFilmResponseDto(filmRepository.save(film));
    }

    @Override
    public void deleteFilm(Long id) {
        filmRepository.delete(findFilmById(id));
    }

    @Override
    public Film findFilmById(Long id) {
        return filmRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("Film not found with id"+id));
    }

}
