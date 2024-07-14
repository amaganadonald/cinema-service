package com.amagana.cinema_service.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.amagana.cinema_service.dto.SalleRequestDto;
import com.amagana.cinema_service.dto.SalleResponseDto;
import com.amagana.cinema_service.entity.Salle;
import com.amagana.cinema_service.model.APIResponse;
import com.amagana.cinema_service.service.SalleService;

import lombok.AllArgsConstructor;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;




@RestController
@RequestMapping("/api/v1/salle")
@AllArgsConstructor
public class SalleController {

    private final SalleService salleService;

    @GetMapping
    public ResponseEntity<APIResponse<List<SalleResponseDto>>> getAllSalles() {
        return ResponseEntity.status(HttpStatus.OK).body(APIResponse.apiResponseListResult(
            HttpStatus.OK.value(), "Salle inserted successfully", salleService.getAllSalle()));
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<APIResponse<SalleResponseDto>> getSalleById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(APIResponse.apiResponseSingleResult(
            HttpStatus.OK.value(), "Salle found", salleService.getSalleById(id)));
    }

    @GetMapping("/list")
    public ResponseEntity<APIResponse<List<Salle>>> getSalleByList(@RequestBody List<Integer> salle) {
        return ResponseEntity.status(HttpStatus.OK).body(APIResponse.apiResponseListResult(
            HttpStatus.OK.value(), "List Salle found", salleService.getSalleByList(salle)));
    }
    
    @PostMapping
    public ResponseEntity<APIResponse<SalleResponseDto>> createNewSalle(@RequestBody SalleRequestDto salleRequestDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(APIResponse.apiResponseSingleResult(
            HttpStatus.OK.value(), "Salle created", salleService.createNewSalle(salleRequestDto)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<APIResponse<SalleResponseDto>> updateSalle(@PathVariable Long id, @RequestBody SalleRequestDto salleRequestDto) {
        return ResponseEntity.status(HttpStatus.OK).body(APIResponse.apiResponseSingleResult(
            HttpStatus.OK.value(), "Salle update successfully", salleService.updateSalle(salleRequestDto, id)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<APIResponse<String>> deleteSalle(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(APIResponse.apiResponseMessage(
            HttpStatus.NO_CONTENT.value(), "Salle deleted successfully"));
    }
    
}
