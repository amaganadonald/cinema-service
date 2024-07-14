package com.amagana.cinema_service.dto;

import java.time.LocalDateTime;
import java.util.List;

import com.amagana.cinema_service.entity.Film;
import com.amagana.cinema_service.entity.Salle;
import com.amagana.cinema_service.entity.Session;
import com.amagana.cinema_service.entity.Ticket;

public record ProjectionMovieResponseDto(Long id, LocalDateTime projectionDate, double price, Film film, Salle salle,
                                         List<Ticket> tickets, Session sessions) {

}
