package com.amagana.cinema_service.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.amagana.cinema_service.dto.ProjectionMovieRequestDto;
import com.amagana.cinema_service.dto.ProjectionMovieResponseDto;
import com.amagana.cinema_service.model.APIResponse;
import com.amagana.cinema_service.service.ProjectionMovieService;

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
@AllArgsConstructor
@RequestMapping("/api/v1/projectionMovie")
public class ProjectionMovieController {

    private final ProjectionMovieService projectionMovieService;

    @GetMapping
    public ResponseEntity<APIResponse<List<ProjectionMovieResponseDto>>> getAllProjectionMovie() {
        return ResponseEntity.status(HttpStatus.OK).body(APIResponse.apiResponseListResult(
            HttpStatus.OK.value(), "All Projection retrieves", projectionMovieService.getAllProjections()));
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<APIResponse<ProjectionMovieResponseDto>> getProjectionMovieByid(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(APIResponse.apiResponseSingleResult(
            HttpStatus.OK.value(), "Projection Found", projectionMovieService.getProjectionMovieById(id)));
    }

    @PostMapping
    public ResponseEntity<APIResponse<ProjectionMovieResponseDto>> createNewProjection(@RequestBody 
                                                            ProjectionMovieRequestDto projectionMovieRequestDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(APIResponse.apiResponseSingleResult(
            HttpStatus.CREATED.value(), "Projection created successfully", 
            projectionMovieService.createNewProjectionMovie(projectionMovieRequestDto)));
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<APIResponse<ProjectionMovieResponseDto>> updateProjectionMovie(@PathVariable Long id,
                               @RequestBody ProjectionMovieRequestDto projectionMovieRequestDto) {
        return ResponseEntity.status(HttpStatus.OK).body(APIResponse.apiResponseSingleResult(
            HttpStatus.OK.value(), "Projection Movie updated",
             projectionMovieService.updateProjectionMovie(projectionMovieRequestDto, id)));
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<APIResponse<String>> deleteProjectionMovie(@PathVariable Long id) {
        projectionMovieService.deleteProjectionMovie(id);
        return ResponseEntity.status(HttpStatus.OK).body(APIResponse.apiResponseMessage(
            HttpStatus.NO_CONTENT.value(), "Projection Movie deleted"));
    }
}
