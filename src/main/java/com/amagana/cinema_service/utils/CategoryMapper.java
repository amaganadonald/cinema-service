package com.amagana.cinema_service.utils;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.amagana.cinema_service.dto.CategoryRequestDto;
import com.amagana.cinema_service.dto.CategoryResponseDto;
import com.amagana.cinema_service.entity.Category;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class CategoryMapper {

    private ModelMapper modelMapper;

    public Category categoryRequestDtoTCategory(CategoryRequestDto categoryRequestDto) {
        return modelMapper.map(categoryRequestDto, Category.class);
    }

    public CategoryResponseDto categoryToCategoryResponseDto(Category category) {
        return modelMapper.map(category, CategoryResponseDto.class);
    }
}
