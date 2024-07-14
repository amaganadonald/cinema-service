package com.amagana.cinema_service.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import com.amagana.cinema_service.exception.CinemaBussnessException;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.persistence.Version;
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
@Table(name = "spectators", uniqueConstraints = @UniqueConstraint(columnNames = {"lastName", "firstName"}))
public class Spectators {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "employee_seq")
    @SequenceGenerator(name = "employee_seq", initialValue = 1, allocationSize = 1)
    private Long id;
    private String firstName;
    private String lastName;
    private double salary;
    private int age;
    private LocalDate bornDate;
    @Version
    private int version;
    @CreatedDate
    private LocalDate localDate;
    @LastModifiedDate
    private LocalDateTime localDateTime;

    @PrePersist
    @PreUpdate
    public void checkDate() {
        if (this.bornDate.isAfter(this.localDate))
          throw new CinemaBussnessException("born date should be less than local date");
    }
}
