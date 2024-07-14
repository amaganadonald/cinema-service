package com.amagana.cinema_service.dto;

import java.time.LocalDateTime;


public record ProjectionMovieRequestDto(LocalDateTime projectionDate, double price, Long filmId, Long salleId, Long sessionId) {

}
