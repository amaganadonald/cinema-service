package com.amagana.cinema_service.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.amagana.cinema_service.dto.FilmRequestDto;
import com.amagana.cinema_service.dto.FilmResponseDto;
import com.amagana.cinema_service.model.APIResponse;
import com.amagana.cinema_service.service.FilmService;

import lombok.AllArgsConstructor;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PutMapping;





@RestController
@RequestMapping("/api/v1/film")
@AllArgsConstructor
public class FilmController {

    private FilmService filmService;

    @GetMapping
    public ResponseEntity<APIResponse<List<FilmResponseDto>>> getAllFilm() {
        return ResponseEntity.status(HttpStatus.OK).body(APIResponse.apiResponseListResult(
            HttpStatus.OK.value(), "List All Film", filmService.getAllFilm()
        ));
    }

    @GetMapping("/{id}")
    public ResponseEntity<APIResponse<FilmResponseDto>> getFilmById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(APIResponse.apiResponseSingleResult(
            HttpStatus.OK.value(), "Found Film successfully", filmService.getFilmById(id)));
    }
    
    @PostMapping
    public ResponseEntity<APIResponse<FilmResponseDto>> createNewFilm(@RequestBody FilmRequestDto filmRequestDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(APIResponse.apiResponseSingleResult(
            HttpStatus.CREATED.value(), "Film created successfull", filmService.createNewFilm(filmRequestDto)));
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<APIResponse<FilmResponseDto>> updateFilm(@PathVariable Long id, @RequestBody FilmRequestDto filmRequestDto) {
        return ResponseEntity.status(HttpStatus.OK).body(APIResponse.apiResponseSingleResult(
            HttpStatus.OK.value(), "Film updated", filmService.updateFilm(filmRequestDto, id)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<APIResponse<String>> deleteFilm(@PathVariable Long id) {
        filmService.deleteFilm(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(APIResponse.apiResponseMessage(
            HttpStatus.NO_CONTENT.value(), "Film deleted successfully"));
    }
}
