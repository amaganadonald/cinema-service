package com.amagana.cinema_service.entity;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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
@Table(name = "Town", uniqueConstraints = {
    @UniqueConstraint(name="uniqueValue", columnNames = {"longitude", "latitude"})
})
public class Town {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "town_seq")
    @SequenceGenerator(name = "town_seq", initialValue = 1, allocationSize = 1)
    private Long id;
    private String name;
    private double longitude;
    private double latitude;
    private double altitude;

    @JsonBackReference
    @Builder.Default
    @OneToMany(mappedBy = "town")
    private List<Cinema> cinema = new ArrayList<>();
}
