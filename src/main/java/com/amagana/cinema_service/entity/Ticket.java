package com.amagana.cinema_service.entity;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
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
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"place_id", "projection_movie_id"})})
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ticket_seq")
    @SequenceGenerator(name = "ticket_seq", initialValue = 1, allocationSize = 1)
    private Long id;
    private UUID ticketCode;
    private String customerName;
    private double price;
    private int paymentCode;
    private boolean status;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "projection_movie_id", nullable = false)
    private ProjectionMovie projectionMovie;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "place_id", nullable = false)
    private Place place;
}
