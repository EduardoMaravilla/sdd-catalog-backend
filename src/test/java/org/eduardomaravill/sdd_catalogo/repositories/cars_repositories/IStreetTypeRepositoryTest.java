package org.eduardomaravill.sdd_catalogo.repositories.cars_repositories;

import org.eduardomaravill.sdd_catalogo.models.cars_models.StreetType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest(properties = {"spring.profiles.active=test"})
class IStreetTypeRepositoryTest {

    @Autowired
    private IStreetTypeRepository streetTypeRepository;

    private StreetType streetType;

    @BeforeEach
    void setUp() {
        streetType = new StreetType();
        streetType.setStreetTypeVal("Asfalto");
    }

    @Test
    @DisplayName("Test save StreetType")
    void testSaveStreetType() {
        streetTypeRepository.save(streetType);
        assertNotNull(streetType.getId());
        Optional<StreetType> savedStreetType = streetTypeRepository.findById(streetType.getId());
        assertTrue(savedStreetType.isPresent());
        assertEquals(streetType.getStreetTypeVal(), savedStreetType.get().getStreetTypeVal());
    }

    @Test
    @DisplayName("Test update StreetType")
    void testUpdateStreetType() {
        streetTypeRepository.save(streetType);
        streetType.setStreetTypeVal("Derrape");
        streetTypeRepository.save(streetType);
        Optional<StreetType> savedStreetType = streetTypeRepository.findById(streetType.getId());
        assertTrue(savedStreetType.isPresent());
        assertEquals(streetType.getStreetTypeVal(), savedStreetType.get().getStreetTypeVal());
    }

    @Test
    @DisplayName("Test delete StreetType")
    void testDeleteStreetType() {
        streetTypeRepository.save(streetType);
        streetTypeRepository.deleteById(streetType.getId());
        Optional<StreetType> deletedStreetType = streetTypeRepository.findById(streetType.getId());
        assertFalse(deletedStreetType.isPresent());
    }
}