package com.amagana.cinema_service.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.amagana.cinema_service.dto.TicketRequestDto;
import com.amagana.cinema_service.dto.TicketResponseDto;
import com.amagana.cinema_service.entity.Ticket;

public interface TicketService {

    List<TicketResponseDto> getAllTicket();
    TicketResponseDto getTicketById(Long id);
    Ticket findTicketById(Long id);
    Page<TicketResponseDto> getAllTicketByPage(int page, int size);
    TicketResponseDto createNewTicket(TicketRequestDto ticketRequestDto);
    TicketResponseDto updateTicket(TicketRequestDto ticketRequestDto, Long id);
    void deleteTicket(Long id);
}
