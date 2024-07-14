package com.amagana.cinema_service.dto;

import java.time.LocalDate;

import lombok.Builder;


@Builder
public record FilmRequestDto(String title, double duration, String realisator, String description
                             , String picture, LocalDate outDate, Long CategoryId) {
} 
