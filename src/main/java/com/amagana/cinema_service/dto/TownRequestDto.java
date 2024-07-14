package com.amagana.cinema_service.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
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
public class TownRequestDto {

    @NotBlank(message = "Town name cannot be Blank")
    @Size(min = 3, message = "Town must have 3 letters at least")
    @Pattern(regexp = "^[a-zA-Z0-9]{3,30}$", message = "name must have only alpha letters")
    private String name;
    @DecimalMin(value = "1.0", message = "Town longitude must be up and Equal to 1")
    private double longitude;
    private double latitude;
    private double altitude;
}
