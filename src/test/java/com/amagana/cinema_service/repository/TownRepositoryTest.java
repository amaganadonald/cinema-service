package com.amagana.cinema_service.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.amagana.cinema_service.entity.Town;

@DataJpaTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class TownRepositoryTest {

    @Autowired
    private TownRepository townRepository;

    @Test
    @Order(1)
    @Rollback(false)
    void givenTown_whenSave_ThenReturnSavedTown() {
        Town town = Town.builder()
                        .altitude(10)
                        .latitude(123)
                        .longitude(18)
                        .name("Hamilus")
                        .build();
        Town townSaved = townRepository.save(town);
        Assertions.assertThat(townSaved.getAltitude()).isEqualTo(10);
        Assertions.assertThat(townSaved.getId()).isEqualTo(1);
        Assertions.assertThat(townSaved.getName()).isEqualToIgnoringCase("Hamilus");
    }

    @Test
    @Order(2)
    void givenTownId_whenFindById_ThenTown() {
        Town town = townRepository.findById(1L).orElseThrow(()-> new RuntimeException("entity not found"));
        Assertions.assertThatNoException();
        Assertions.assertThat(town).isNotNull();
        Assertions.assertThat(town.getLongitude()).isEqualTo(18);
    }

    @Test
    @Order(3)
    @Rollback(false)
    void givenTownAndTownId_whenFindByIdAndUpdate_ReturnUpadatedTown() {
        Town town = townRepository.findById(1L).orElseThrow(()-> new RuntimeException("entity not found"));
        town.setLongitude(60);
        town.setName("Lux");
        townRepository.save(town);
        Assertions.assertThatNoException();
        Assertions.assertThat(town).isNotNull();
        Assertions.assertThat(town.getLongitude()).isEqualTo(60);
        Assertions.assertThat(town.getName()).isEqualTo("Lux");
    }
}
