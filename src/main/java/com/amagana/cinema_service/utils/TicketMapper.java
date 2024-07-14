package com.amagana.cinema_service.utils;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.amagana.cinema_service.dto.TicketRequestDto;
import com.amagana.cinema_service.dto.TicketResponseDto;
import com.amagana.cinema_service.entity.Ticket;

@Mapper
public interface TicketMapper {

    TicketMapper INSTANCE = Mappers.getMapper(TicketMapper.class);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "projectionMovie", ignore = true)
    @Mapping(target = "place", ignore = true)
    Ticket ticketRequestToTicket(TicketRequestDto ticketRequestDto);

    TicketResponseDto ticketToTicketResponseDto(Ticket ticket);
}
