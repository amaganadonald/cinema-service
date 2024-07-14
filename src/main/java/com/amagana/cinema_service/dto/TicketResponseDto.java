package com.amagana.cinema_service.dto;

import com.amagana.cinema_service.entity.Place;
import com.amagana.cinema_service.entity.ProjectionMovie;

public record TicketResponseDto(Long id, String customerName, double price, int paymentCode, 
                               boolean status, ProjectionMovie projectionMovie, Place place) {

}
