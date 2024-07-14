package com.amagana.cinema_service.dto;

public record TicketRequestDto(String customerName, double price, int paymentCode, boolean status,
                              Long projectionMovieId, Long placeId) {

}
