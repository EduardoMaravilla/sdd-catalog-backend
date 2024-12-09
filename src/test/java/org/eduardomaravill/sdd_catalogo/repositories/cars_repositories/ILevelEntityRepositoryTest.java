package org.eduardomaravill.sdd_catalogo.repositories.cars_repositories;

import org.eduardomaravill.sdd_catalogo.models.cars_models.LevelEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest(properties = {"spring.profiles.active=test"})
class ILevelEntityRepositoryTest {

    @Autowired
    private ILevelRepository levelRepository;

    private LevelEntity levelEntity;

    @BeforeEach
    void setUp() {
        levelEntity = new LevelEntity();
        levelEntity.setLevel("Super");
    }

    @Test
    @DisplayName("Test save Level")
    void testSaveLevel() {
        levelRepository.save(levelEntity);
        assertNotNull(levelEntity.getId());
        Optional<LevelEntity> levelSave = levelRepository.findById(levelEntity.getId());
        assertTrue(levelSave.isPresent());
        assertEquals("Super", levelSave.get().getLevel());
    }

    @Test
    @DisplayName("Test update Level")
    void testUpdateLevel() {
        levelRepository.save(levelEntity);
        levelEntity.setLevel("Pro");
        levelRepository.save(levelEntity);
        Optional<LevelEntity> levelSave = levelRepository.findById(levelEntity.getId());
        assertTrue(levelSave.isPresent());
        assertEquals("Pro", levelSave.get().getLevel());
    }

    @Test
    @DisplayName("Test delete Level")
    void testDeleteLevel() {
        levelRepository.save(levelEntity);
        assertNotNull(levelEntity.getId());
        levelRepository.deleteById(levelEntity.getId());
        Optional<LevelEntity> levelSave = levelRepository.findById(levelEntity.getId());
        assertFalse(levelSave.isPresent());
    }
}