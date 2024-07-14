package com.amagana.cinema_service.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class CategoryRequestDto {

    @NotBlank(message = "Category name cannot be blank")
    @NotEmpty(message = "Category name cannot be empty")
    @Size(min = 3, message = "Category name must have 3 words at least")
    private String name;
}
