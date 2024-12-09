package org.eduardomaravill.sdd_catalogo.repositories.cars_repositories;

import org.eduardomaravill.sdd_catalogo.models.cars_models.TurboType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest(properties = {"spring.profiles.active=test"})
class ITurboTypeRepositoryTest {

    @Autowired
    private ITurboTypeRepository turboTypeRepository;

    private TurboType turboType;

    @BeforeEach
    void setUp() {
        turboType = new TurboType();
        turboType.setType("TurboCompresor");
    }

    @Test
    @DisplayName("Test save Turbo Type")
    void testSaveTurboType(){
        turboTypeRepository.save(turboType);
        assertNotNull(turboType.getId());
        Optional<TurboType> savedTurboType =turboTypeRepository.findById(turboType.getId());
        assertTrue(savedTurboType.isPresent());
        assertEquals(turboType.getType(), savedTurboType.get().getType());
    }

    @Test
    @DisplayName("Test update Turbo Type")
    void testUpdateTurboType(){
        turboTypeRepository.save(turboType);
        turboType.setType("DobleTurboCompresor");
        turboTypeRepository.save(turboType);
        Optional<TurboType> savedTurboType = turboTypeRepository.findById(turboType.getId());
        assertTrue(savedTurboType.isPresent());
        assertEquals(turboType.getType(), savedTurboType.get().getType());
    }

    @Test
    @DisplayName("Test delete Turbo Type")
    void testDeleteTurboType(){
        turboTypeRepository.save(turboType);
        turboTypeRepository.deleteById(turboType.getId());
        Optional<TurboType> deletedTurboType = turboTypeRepository.findById(turboType.getId());
        assertFalse(deletedTurboType.isPresent());
    }
}