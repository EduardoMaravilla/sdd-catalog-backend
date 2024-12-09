package org.eduardomaravill.sdd_catalogo.repositories.cars_repositories;

import org.eduardomaravill.sdd_catalogo.models.cars_models.LevelEntity;
import org.eduardomaravill.sdd_catalogo.models.cars_models.StreetType;
import org.eduardomaravill.sdd_catalogo.models.cars_models.Tire;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest(properties = {"spring.profiles.active=test"})
class ITireRepositoryTest {
    @Autowired
    private ITireRepository tireRepository;

    @Autowired
    private IStreetTypeRepository streetTypeRepository;

    @Autowired
    private ILevelRepository levelRepository;

    private Tire tire;

    private StreetType streetType;

    private LevelEntity levelEntity;

    @BeforeEach
    void setUp() {
        streetType = new StreetType();
        streetType.setStreetTypeVal("TodoTerreno");
        tire = new Tire();
        levelEntity = new LevelEntity();
        levelEntity.setLevel("Pro");
    }

    @Test
    @DisplayName("Test save Suspension")
    void testSaveSuspension() {
        streetTypeRepository.save(streetType);
        levelRepository.save(levelEntity);
        tire.setStreetType(streetType);
        tire.setLevelEntity(levelEntity);
        tireRepository.save(tire);
        assertNotNull(tire.getId());
        Optional<Tire> savedTire = tireRepository.findById(tire.getId());
        assertTrue(savedTire.isPresent());
        assertEquals(tire.getLevelEntity(), savedTire.get().getLevelEntity());
        assertEquals(tire.getStreetType(), savedTire.get().getStreetType());
    }

    @Test
    @DisplayName("Test update Suspension")
    void testUpdateSuspension() {
        streetTypeRepository.save(streetType);
        levelRepository.save(levelEntity);
        tire.setStreetType(streetType);
        tire.setLevelEntity(levelEntity);
        tireRepository.save(tire);
        StreetType streetType1 = new StreetType();
        streetType1.setStreetTypeVal("Asfalto");
        streetTypeRepository.save(streetType1);
        LevelEntity levelEntity1 = new LevelEntity();
        levelEntity1.setLevel("Premium");
        levelRepository.save(levelEntity1);
        tire.setStreetType(streetType1);
        tire.setLevelEntity(levelEntity1);
        tireRepository.save(tire);
        Optional<Tire> savedTire = tireRepository.findById(tire.getId());
        assertTrue(savedTire.isPresent());
        assertEquals("Asfalto", savedTire.get().getStreetType().getStreetTypeVal());
        assertEquals("Premium", savedTire.get().getLevelEntity().getLevel());
    }

    @Test
    @DisplayName("Test delete Suspension")
    void testDeleteSuspension() {
        streetTypeRepository.save(streetType);
        levelRepository.save(levelEntity);
        tire.setStreetType(streetType);
        tire.setLevelEntity(levelEntity);
        tireRepository.save(tire);
        tireRepository.deleteById(tire.getId());
        assertFalse(tireRepository.findById(tire.getId()).isPresent());
    }
}