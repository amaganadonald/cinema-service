package com.amagana.cinema_service.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.amagana.cinema_service.dto.CategoryRequestDto;
import com.amagana.cinema_service.dto.CategoryResponseDto;
import com.amagana.cinema_service.model.APIResponse;
import com.amagana.cinema_service.service.CategoryService;

import jakarta.validation.Valid;
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
@RequestMapping("/api/v1/category")
@AllArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping
    public ResponseEntity<APIResponse<List<CategoryResponseDto>>> getAllCategory() {
        return ResponseEntity.status(HttpStatus.OK).body(
            APIResponse.apiResponseListResult(HttpStatus.OK.value(), "All Category Retrieve Successfully", categoryService.getAllCategory())
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<APIResponse<CategoryResponseDto>> getCategoryById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(
            APIResponse.apiResponseSingleResult(HttpStatus.OK.value(), "Category Retrieve Successfully", categoryService.getCategoryById(id))
        );
    }
    
    @PostMapping
    public ResponseEntity<APIResponse<CategoryResponseDto>> createNewCategory(@RequestBody @Valid CategoryRequestDto categoryRequestDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(
            APIResponse.apiResponseSingleResult(HttpStatus.CREATED.value(), "New Category created", categoryService.createNewCategory(categoryRequestDto))
        );
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<APIResponse<CategoryResponseDto>> updateCategory(@PathVariable Long id, @RequestBody @Valid CategoryRequestDto categoryRequestDto) {
        return ResponseEntity.status(HttpStatus.OK).body(APIResponse.apiResponseSingleResult(
            HttpStatus.OK.value(), "Category Updated Successfully", categoryService.updateCategory(categoryRequestDto, id)));
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<APIResponse<String>> deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(APIResponse.apiResponseSingleResult(
            HttpStatus.NO_CONTENT.value(), "Category deleted successfully", ""));
    }
}
