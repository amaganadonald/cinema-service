package com.amagana.cinema_service.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.amagana.cinema_service.dto.CinemaRequestDto;
import com.amagana.cinema_service.dto.CinemaResponseDto;
import com.amagana.cinema_service.model.APIResponse;
import com.amagana.cinema_service.service.CinemaService;

import lombok.AllArgsConstructor;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;




@RestController
@RequestMapping("/api/v1/cinema")
@AllArgsConstructor
public class CinemaController {

    private final CinemaService cinemaService;

    @GetMapping
    public ResponseEntity<APIResponse<List<CinemaResponseDto>>> getAllCinema() {
        return ResponseEntity.status(HttpStatus.OK).body(APIResponse.apiResponseListResult(
            HttpStatus.OK.value(), "Cinema retrieve Successfully", cinemaService.getAllCinema()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<APIResponse<CinemaResponseDto>> getCinemaById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(APIResponse.apiResponseSingleResult(
            HttpStatus.OK.value(), "Cinema Found", cinemaService.getCinemaById(id)));
    }
    
    @PostMapping
    public ResponseEntity<APIResponse<CinemaResponseDto>> createNewCinema(@RequestBody CinemaRequestDto cinemaRequestDto) {
       return ResponseEntity.status(HttpStatus.CREATED).body(APIResponse.apiResponseSingleResult(
        HttpStatus.CREATED.value(), "Cinema created with success", cinemaService.createNewCinema(cinemaRequestDto)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<APIResponse<CinemaResponseDto>> updateCreate(@PathVariable Long id,
                                                                         @RequestBody CinemaRequestDto cinemaRequestDto) {
        return ResponseEntity.status(HttpStatus.OK).body(APIResponse.apiResponseSingleResult(
            HttpStatus.OK.value(), "Cinema updated successfully", cinemaService.updateCinema(cinemaRequestDto, id)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<APIResponse<String>> deleteCinema(@PathVariable Long id) {
        cinemaService.deleteCinema(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(APIResponse.apiResponseMessage(
            HttpStatus.NO_CONTENT.value(), "Cinema deleted Successfully"));
    }
    
}
