package com.amagana.cinema_service.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.amagana.cinema_service.dto.SessionRequestDto;
import com.amagana.cinema_service.dto.SessionResponseDto;
import com.amagana.cinema_service.model.APIResponse;
import com.amagana.cinema_service.service.SessionService;

import lombok.AllArgsConstructor;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;




@RestController
@RequestMapping("/api/v1/session")
@AllArgsConstructor
public class SessionController {

    private final SessionService sessionService;

    @GetMapping
    public ResponseEntity<APIResponse<List<SessionResponseDto>>> getAllSessions() {
        return ResponseEntity.status(HttpStatus.OK).body(APIResponse.apiResponseListResult(
            HttpStatus.OK.value(), "All Session retrieves", sessionService.getAllSession()));
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<APIResponse<SessionResponseDto>> getSessionById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(APIResponse.apiResponseSingleResult(
            HttpStatus.OK.value(), "Session Found", sessionService.getSessionById(id)));
    }
    
    @PostMapping
    public ResponseEntity<APIResponse<SessionResponseDto>> createNewSession(@RequestBody SessionRequestDto sessionRequestDto) {
       return ResponseEntity.status(HttpStatus.CREATED).body(APIResponse.apiResponseSingleResult(
        HttpStatus.CREATED.value(), "Session created", sessionService.createNewSession(sessionRequestDto)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<APIResponse<SessionResponseDto>> updateSession(@PathVariable Long id, 
                                               @RequestBody SessionRequestDto sessionRequestDto) {
        return ResponseEntity.status(HttpStatus.OK).body(APIResponse.apiResponseSingleResult(
            HttpStatus.OK.value(), "Session updated", sessionService.updateSession(sessionRequestDto, id)));
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<APIResponse<String>> deleteSession(@PathVariable Long id) {
        sessionService.deleteSession(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(APIResponse.apiResponseMessage(
            HttpStatus.NO_CONTENT.value(), "Session deleted"));
    }
}
