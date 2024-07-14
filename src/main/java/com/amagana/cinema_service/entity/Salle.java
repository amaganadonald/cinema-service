package com.amagana.cinema_service.entity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OrderBy;
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
public class Salle {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "salle_seq")
    @SequenceGenerator(name = "salle_seq", initialValue = 1, allocationSize = 1)
    private Long id;
    private String name;
    private int numberSeat;

    @ManyToOne
    @JoinColumn(name = "cinema_id")
    @JsonBackReference
    private Cinema cinema;

    @Builder.Default
    @OrderBy(value = "number")
    @OneToMany(mappedBy = "salle", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Place> places = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "salle")
    @JsonManagedReference
    private Set<ProjectionMovie> projectionMovies = new HashSet<>();
}
