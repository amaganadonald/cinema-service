package com.amagana.cinema_service.service;

import java.util.List;

import com.amagana.cinema_service.dto.CategoryRequestDto;
import com.amagana.cinema_service.dto.CategoryResponseDto;
import com.amagana.cinema_service.entity.Category;

public interface CategoryService {

    List<CategoryResponseDto> getAllCategory();
    CategoryResponseDto getCategoryById(Long id);
    Category findCategoryById(Long id);
    CategoryResponseDto createNewCategory(CategoryRequestDto categoryRequestDto);
    CategoryResponseDto updateCategory(CategoryRequestDto categoryRequestDto, Long id);
    void deleteCategory(Long id);
}
