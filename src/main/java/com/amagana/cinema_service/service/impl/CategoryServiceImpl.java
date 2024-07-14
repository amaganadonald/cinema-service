package com.amagana.cinema_service.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.amagana.cinema_service.dto.CategoryRequestDto;
import com.amagana.cinema_service.dto.CategoryResponseDto;
import com.amagana.cinema_service.entity.Category;
import com.amagana.cinema_service.repository.CategoryRepository;
import com.amagana.cinema_service.service.CategoryService;
import com.amagana.cinema_service.utils.CategoryMapper;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
@Transactional
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @Override
    public List<CategoryResponseDto> getAllCategory() {
        return categoryRepository.findAll()
                                 .stream()
                                 .map(categoryMapper::categoryToCategoryResponseDto)
                                 .toList();
    }

    @Override
    public CategoryResponseDto getCategoryById(Long id) {
        return categoryMapper.categoryToCategoryResponseDto(findCategoryById(id));
    }

    /**
     * create this method for reusability around this class and order class
     * @Author Donald amagana
     */
    @Override
    public Category findCategoryById(Long id) {
        return categoryRepository.findById(id).orElseThrow(()->new EntityNotFoundException("Category not found with id: "+id));
    }

    @Override
    public CategoryResponseDto createNewCategory(CategoryRequestDto categoryRequestDto) {
        return categoryMapper.categoryToCategoryResponseDto(
            categoryRepository.save(categoryMapper.categoryRequestDtoTCategory(categoryRequestDto))
        );
    }

    @Override
    public CategoryResponseDto updateCategory(CategoryRequestDto categoryRequestDto, Long id) {
        Category category = findCategoryById(id);
        category.setName(categoryRequestDto.getName());
        return categoryMapper.categoryToCategoryResponseDto(categoryRepository.save(category));
    }

    @Override
    public void deleteCategory(Long id) {
        categoryRepository.delete(findCategoryById(id));
    }

}
