package org.eduardomaravill.sdd_catalogo.repositories.cars_repositories;

import org.eduardomaravill.sdd_catalogo.models.cars_models.LevelEntity;
import org.eduardomaravill.sdd_catalogo.models.cars_models.StreetType;
import org.eduardomaravill.sdd_catalogo.models.cars_models.Suspension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest(properties = {"spring.profiles.active=test"})
class ISuspensionRepositoryTest {

    @Autowired
    private ISuspensionRepository suspensionRepository;

    @Autowired
    private IStreetTypeRepository streetTypeRepository;

    @Autowired
    private ILevelRepository levelRepository;

    private Suspension suspension;

    private StreetType streetType;

    private LevelEntity levelEntity;

    @BeforeEach
    void setUp() {
        streetType = new StreetType();
        streetType.setStreetTypeVal("TodoTerreno");
        suspension = new Suspension();
        levelEntity = new LevelEntity();
        levelEntity.setLevel("Pro");
    }

    @Test
    @DisplayName("Test save Suspension")
    void testSaveSuspension() {
        streetTypeRepository.save(streetType);
        levelRepository.save(levelEntity);
        suspension.setStreetType(streetType);
        suspension.setLevelEntity(levelEntity);
        suspensionRepository.save(suspension);
        assertNotNull(suspension.getId());
        Optional<Suspension> savedSuspension = suspensionRepository.findById(suspension.getId());
        assertTrue(savedSuspension.isPresent());
        assertEquals(suspension.getLevelEntity(),savedSuspension.get().getLevelEntity());
        assertEquals(suspension.getStreetType(), savedSuspension.get().getStreetType());
    }

    @Test
    @DisplayName("Test update Suspension")
    void testUpdateSuspension() {
        streetTypeRepository.save(streetType);
        levelRepository.save(levelEntity);
        suspension.setStreetType(streetType);
        suspension.setLevelEntity(levelEntity);
        suspensionRepository.save(suspension);
        StreetType streetType1= new StreetType();
        streetType1.setStreetTypeVal("Asfalto");
        streetTypeRepository.save(streetType1);
        LevelEntity levelEntity1 = new LevelEntity();
        levelEntity1.setLevel("Premium");
        levelRepository.save(levelEntity1);
        suspension.setStreetType(streetType1);
        suspension.setLevelEntity(levelEntity1);
        suspensionRepository.save(suspension);
        Optional<Suspension> savedSuspension = suspensionRepository.findById(suspension.getId());
        assertTrue(savedSuspension.isPresent());
        assertEquals("Asfalto", savedSuspension.get().getStreetType().getStreetTypeVal());
        assertEquals("Premium", savedSuspension.get().getLevelEntity().getLevel());
    }

    @Test
    @DisplayName("Test delete Suspension")
    void testDeleteSuspension() {
        streetTypeRepository.save(streetType);
        levelRepository.save(levelEntity);
        suspension.setStreetType(streetType);
        suspension.setLevelEntity(levelEntity);
        suspensionRepository.save(suspension);
        suspensionRepository.deleteById(suspension.getId());
        assertFalse(suspensionRepository.findById(suspension.getId()).isPresent());
    }
}