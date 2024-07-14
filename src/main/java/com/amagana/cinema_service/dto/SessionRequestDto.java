package com.amagana.cinema_service.dto;

import java.time.LocalDate;

public record SessionRequestDto(Long id, LocalDate startHour) {

}
