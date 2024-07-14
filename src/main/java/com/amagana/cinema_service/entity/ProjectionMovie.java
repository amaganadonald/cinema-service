package com.amagana.cinema_service.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Builder;
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
public class ProjectionMovie {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "projectionMovie_seq")
    @SequenceGenerator(name = "projectionMovie_seq", initialValue = 1, allocationSize = 1)
    private Long id;
    private LocalDateTime projectionDate;
    private double price;

    @ManyToOne
    @JsonBackReference
    private Film film;

    @ManyToOne
    @JsonBackReference
    private Salle salle;

    @Builder.Default
    @OneToMany(mappedBy = "projectionMovie")
    @JsonManagedReference
    private List<Ticket> tickets = new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "sessionId")
    private Session sessions;
}
