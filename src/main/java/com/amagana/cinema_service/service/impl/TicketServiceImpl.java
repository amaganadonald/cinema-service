package com.amagana.cinema_service.service.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.amagana.cinema_service.dto.TicketRequestDto;
import com.amagana.cinema_service.dto.TicketResponseDto;
import com.amagana.cinema_service.entity.Place;
import com.amagana.cinema_service.entity.ProjectionMovie;
import com.amagana.cinema_service.entity.Ticket;
import com.amagana.cinema_service.exception.TicketPriceNotEqualsProjectionException;
import com.amagana.cinema_service.repository.TicketRepository;
import com.amagana.cinema_service.service.PlaceService;
import com.amagana.cinema_service.service.ProjectionMovieService;
import com.amagana.cinema_service.service.TicketService;
import com.amagana.cinema_service.utils.TicketMapper;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
@Transactional
public class TicketServiceImpl implements TicketService{

    private final TicketRepository ticketRepository;
    private final PlaceService placeService;
    private final ProjectionMovieService projectionMovieService;
    private static final TicketMapper ticketMapper = TicketMapper.INSTANCE;

    @Override
    public List<TicketResponseDto> getAllTicket() {
       return ticketRepository.findAll().stream()
                              .map(ticketMapper::ticketToTicketResponseDto)
                              .toList();
    }

    @Override
    public TicketResponseDto getTicketById(Long id) {
        return ticketMapper.ticketToTicketResponseDto(findTicketById(id));
    }

    @Override
    public Ticket findTicketById(Long id) {
        return ticketRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("Ticket not found "+id));
    }

    @Override
    public TicketResponseDto createNewTicket(TicketRequestDto ticketRequestDto) {
        Ticket ticket = ticketMapper.ticketRequestToTicket(ticketRequestDto);
        ticket.setTicketCode(UUID.randomUUID());
        if (ticketRequestDto.placeId() != null) {
            Place place = placeService.findPlaceById(ticketRequestDto.placeId());
            ticket.setPlace(place);
        }
        if (ticketRequestDto.projectionMovieId() != null) {
            ProjectionMovie projectionMovie = projectionMovieService.findProjectionMovie(ticketRequestDto.projectionMovieId());
            if (ticket.getPrice() != projectionMovie.getPrice()) {
                throw new TicketPriceNotEqualsProjectionException("Ticket is not equals to projection movie who is :" + projectionMovie.getPrice());
            }
            ticket.setProjectionMovie(projectionMovie);
        }
        return ticketMapper.ticketToTicketResponseDto(
            ticketRepository.save(ticket)
        );
    }

    @Override
    public TicketResponseDto updateTicket(TicketRequestDto ticketRequestDto, Long id) {
        Ticket ticket = findTicketById(id);
        if (ticketRequestDto.placeId() != null) {
            Place place = placeService.findPlaceById(ticketRequestDto.placeId());
            place.getTickets().add(ticket);
            ticket.setPlace(place);
        }
        if (ticketRequestDto.projectionMovieId() != null) {
            ProjectionMovie projectionMovie = projectionMovieService.findProjectionMovie(ticketRequestDto.projectionMovieId());
            if (ticket.getPrice() != projectionMovie.getPrice()) {
                throw new TicketPriceNotEqualsProjectionException("Ticket is not equals to projection movie who is :" + projectionMovie.getPrice());
            }
            projectionMovie.getTickets().add(ticket);
            ticket.setProjectionMovie(projectionMovie);
        }
        ticket.setCustomerName(ticketRequestDto.customerName());
        ticket.setPaymentCode(ticketRequestDto.paymentCode());
        ticket.setPrice(ticketRequestDto.price());
        ticket.setStatus(ticketRequestDto.status());
        return ticketMapper.ticketToTicketResponseDto(ticketRepository.save(ticket));
    }

    @Override
    public void deleteTicket(Long id) {
        ticketRepository.delete(findTicketById(id));
    }

    @Override
    public Page<TicketResponseDto> getAllTicketByPage(int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size, Sort.by("title").ascending());
        return ticketRepository.findAllByPage(pageRequest);
    }

}
