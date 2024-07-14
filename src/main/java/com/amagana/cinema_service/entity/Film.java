package com.amagana.cinema_service.entity;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
@EqualsAndHashCode
public class Film {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "film_seq")
    @SequenceGenerator(name = "film_seq", initialValue = 1, allocationSize = 1)
    private Long id;
    private String title;
    private double duration;
    private String realisator;
    private String description;
    private String picture;
    private LocalDate outDate;

    @ManyToOne
    @JsonManagedReference
    private Category category;

    @Builder.Default
    @OneToMany(mappedBy = "film")
    @JsonManagedReference
    private Set<ProjectionMovie> projectionMovies = new HashSet<>();
}
