package com.amagana.cinema_service.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.amagana.cinema_service.dto.TownRequestDto;
import com.amagana.cinema_service.dto.TownResponse;
import com.amagana.cinema_service.dto.TownResponseDto;
import com.amagana.cinema_service.model.APIResponse;
import com.amagana.cinema_service.service.TownService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;






@RestController
@RequestMapping("/api/v1/town")
@AllArgsConstructor
public class TownController {

    private final TownService townService;

    @GetMapping
    public ResponseEntity<APIResponse<List<TownResponseDto>>> getAllTown() {
        return ResponseEntity.status(HttpStatus.OK).body(
            APIResponse.apiResponseListResult(HttpStatus.OK.value(), "All Town retrieve successfully", townService.getAllTown())
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<APIResponse<TownResponseDto>> getTownById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(
            APIResponse.apiResponseSingleResult(HttpStatus.OK.value(), "Town retrieve successfully", townService.getTownById(id))
        );
    }
    
    @PostMapping
    public ResponseEntity<APIResponse<TownResponseDto>> createNewTown(@RequestBody @Valid TownRequestDto townRequestDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(
            APIResponse.apiResponseSingleResult(HttpStatus.CREATED.value(),"New Town created successfully", townService.createNewTown(townRequestDto))
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<APIResponse<TownResponseDto>> updateTown(@PathVariable Long id, @RequestBody @Valid TownRequestDto townRequestDto) {
        return ResponseEntity.status(HttpStatus.OK).body(
            APIResponse.apiResponseSingleResult(HttpStatus.OK.value(), "Updated Town successfully", townService.updateTown(townRequestDto, id))
        );
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<APIResponse<String>> deleteTown(@PathVariable Long id) {
        townService.deleteTown(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(
            APIResponse.apiResponseSingleResult(HttpStatus.NO_CONTENT.value(), "Town deleted successful", ""));
    }

    @GetMapping("/pages")
    public ResponseEntity<Page<TownResponse>> getTownPage(@RequestParam(defaultValue = "0") int page, 
                                                         @RequestParam(defaultValue = "5")int size) {
        return ResponseEntity.status(HttpStatus.OK).body(townService.getTownByPage(page, size));
    }
    
    
}
