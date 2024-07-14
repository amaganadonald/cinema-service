package com.amagana.cinema_service.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.amagana.cinema_service.dto.TicketRequestDto;
import com.amagana.cinema_service.dto.TicketResponseDto;
import com.amagana.cinema_service.model.APIResponse;
import com.amagana.cinema_service.service.TicketService;

import lombok.AllArgsConstructor;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;




@RestController
@RequestMapping("/api/v1/ticket")
@AllArgsConstructor
public class TicketController {

    private final TicketService ticketService;

    @GetMapping
    public ResponseEntity<APIResponse<List<TicketResponseDto>>> getAllTickets() {
        return ResponseEntity.status(HttpStatus.OK).body(APIResponse.apiResponseListResult(
            HttpStatus.OK.value(), "All Tickets retrieves", ticketService.getAllTicket()));
    }

    @GetMapping("/page")
    public ResponseEntity<APIResponse<Page<TicketResponseDto>>> getTicketsPage(@RequestParam(defaultValue = "0") int page, 
                                                                    @RequestParam(defaultValue = "5") int size) {
        return ResponseEntity.status(HttpStatus.OK).body(APIResponse.apiResponseSingleResult(
            HttpStatus.OK.value(), "Tickets page retrieve", ticketService.getAllTicketByPage(page, size)));
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<APIResponse<TicketResponseDto>> getTicketById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(APIResponse.apiResponseSingleResult(
            HttpStatus.OK.value(), "Ticket found", ticketService.getTicketById(id)));
    }

    @PostMapping
    public ResponseEntity<APIResponse<TicketResponseDto>> createNewTicket(@RequestBody TicketRequestDto ticketRequestDto) {
      return ResponseEntity.status(HttpStatus.CREATED).body(APIResponse.apiResponseSingleResult(
        HttpStatus.CREATED.value(), "Ticket created successfully", ticketService.createNewTicket(ticketRequestDto)));
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<APIResponse<TicketResponseDto>> updateTicket(@PathVariable Long id, 
                                 @RequestBody TicketRequestDto ticketRequestDto) {
        return ResponseEntity.status(HttpStatus.OK).body(APIResponse.apiResponseSingleResult(
            HttpStatus.OK.value(), "Ticket updated sucessfully", ticketService.updateTicket(ticketRequestDto, id)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<APIResponse<String>> deleteTicket(@PathVariable Long id) {
        ticketService.deleteTicket(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(APIResponse.apiResponseMessage(
            HttpStatus.NO_CONTENT.value(), "Ticket deleted successfully"));
    }
}
