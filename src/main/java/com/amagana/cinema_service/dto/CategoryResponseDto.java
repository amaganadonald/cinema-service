package com.amagana.cinema_service.dto;

import java.util.ArrayList;
import java.util.List;

import com.amagana.cinema_service.entity.Film;

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
public class CategoryResponseDto {

    private Long id;
    private String name;
    @Builder.Default
    private List<Film> films = new ArrayList<>();
}
