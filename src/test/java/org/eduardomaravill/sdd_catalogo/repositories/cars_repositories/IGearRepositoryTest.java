package org.eduardomaravill.sdd_catalogo.repositories.cars_repositories;

import org.eduardomaravill.sdd_catalogo.models.cars_models.Gear;
import org.eduardomaravill.sdd_catalogo.models.cars_models.LevelEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest(properties = {"spring.profiles.active=test"})
class IGearRepositoryTest {

    @Autowired
    private IGearRepository gearRepository;

    @Autowired
    private ILevelRepository levelRepository;

    private Gear gear;
    private LevelEntity levelEntity;

    @BeforeEach
    void setUp() {
        levelEntity = new LevelEntity();
        levelEntity.setLevel("Pro");
        gear = new Gear();
        gear.setGearValue(5);
    }

    @Test
    @DisplayName("Test save Gear")
    void testSaveGear(){
        levelRepository.save(levelEntity);
        gear.setLevelEntity(levelEntity);
        gearRepository.save(gear);
        assertNotNull(gear.getId());
        Optional<Gear> gearSave = gearRepository.findById(gear.getId());
        assertTrue(gearSave.isPresent());
        assertEquals(5, gearSave.get().getGearValue());
    }

    @Test
    @DisplayName("Test update Gear")
    void testUpdateGear(){
        levelRepository.save(levelEntity);
        gear.setLevelEntity(levelEntity);
        gearRepository.save(gear);
        gear.setGearValue(6);
        gearRepository.save(gear);
        Optional<Gear> gearSave = gearRepository.findById(gear.getId());
        assertTrue(gearSave.isPresent());
        assertEquals(6, gearSave.get().getGearValue());
    }

    @Test
    @DisplayName("Test delete Gear")
    void testDeleteGear(){
        levelRepository.save(levelEntity);
        gear.setLevelEntity(levelEntity);
        gearRepository.save(gear);
        gearRepository.deleteById(gear.getId());
        Optional<Gear> gearSave = gearRepository.findById(gear.getId());
        assertFalse(gearSave.isPresent());
    }
}