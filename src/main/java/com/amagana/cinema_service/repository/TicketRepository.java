package com.amagana.cinema_service.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.amagana.cinema_service.dto.TicketResponseDto;
import com.amagana.cinema_service.entity.Ticket;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {

    @Query("SELECT t FROM Ticket t")
    Page<TicketResponseDto> findAllByPage(PageRequest pageRequest);
}
