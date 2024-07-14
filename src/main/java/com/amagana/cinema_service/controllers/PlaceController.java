package com.amagana.cinema_service.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.amagana.cinema_service.dto.PlaceRequestDto;
import com.amagana.cinema_service.dto.PlaceResponseDto;
import com.amagana.cinema_service.model.APIResponse;
import com.amagana.cinema_service.service.PlaceService;

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
@RequestMapping("/api/v1/place")
@AllArgsConstructor
public class PlaceController {

    private final PlaceService placeService;

    @GetMapping
    public ResponseEntity<APIResponse<List<PlaceResponseDto>>> getAllPlaces() {
        return ResponseEntity.status(HttpStatus.OK).body(APIResponse.apiResponseListResult(
            HttpStatus.OK.value(), "All Places retrieve successfully", placeService.getAllPlace()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<APIResponse<PlaceResponseDto>> getPlaceById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(APIResponse.apiResponseSingleResult(
            HttpStatus.OK.value(), "Place retrieve successfully", placeService.getPlaceById(id)));
    }
    
    @PostMapping
    public ResponseEntity<APIResponse<PlaceResponseDto>> createNewPlace(@RequestBody PlaceRequestDto placeRequestDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(APIResponse.apiResponseSingleResult(
            HttpStatus.CREATED.value(), "Place created successfully", placeService.createNewPlace(placeRequestDto)));
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<APIResponse<PlaceResponseDto>> updateplace(@PathVariable Long id, 
                                          @RequestBody PlaceRequestDto placeRequestDto) {
        return ResponseEntity.status(HttpStatus.OK).body(APIResponse.apiResponseSingleResult(
            HttpStatus.OK.value(), "Update Place sucessfully", placeService.updatePlace(placeRequestDto, id)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<APIResponse<String>> deletePlace(@PathVariable Long id) {
        placeService.deletePlace(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(APIResponse.apiResponseMessage(
            HttpStatus.NO_CONTENT.value(), "Place deleted"));
    }
}
