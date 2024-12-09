package org.eduardomaravill.sdd_catalogo.repositories.cars_repositories;

import org.eduardomaravill.sdd_catalogo.models.cars_models.Engine;
import org.eduardomaravill.sdd_catalogo.models.cars_models.LevelEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest(properties = {"spring.profiles.active=test"})
class IEngineRepositoryTest {

    @Autowired
    private IEngineRepository engineRepository;

    @Autowired
    private ILevelRepository levelRepository;

    private Engine engine;

    private LevelEntity levelEntity;
    @BeforeEach
    void setUp() {
        engine = new Engine();
        engine.setModel("V12 boxer");
        engine.setBhp(1500);
        engine.setLiters(5.2);
        levelEntity = new LevelEntity();
        levelEntity.setLevel("Deportivo");
    }

    @Test
    @DisplayName("Test save Engine")
    void saveEngine() {
        levelRepository.save(levelEntity);
        engine.setLevelEntity(levelEntity);
        engineRepository.save(engine);
        assertNotNull(engine.getId());
        Optional<Engine> saveEngine = engineRepository.findById(engine.getId());
        assertTrue(saveEngine.isPresent());
        assertEquals("V12 boxer",saveEngine.get().getModel());
    }

    @Test
    @DisplayName("Test update Engine")
    void updateEngine() {
        levelRepository.save(levelEntity);
        engine.setLevelEntity(levelEntity);
        engineRepository.save(engine);
        engine.setModel("V12 Vortex");
        engineRepository.save(engine);
        Optional<Engine> updatedEngine = engineRepository.findById(engine.getId());
        assertTrue(updatedEngine.isPresent());
        assertEquals("V12 Vortex", updatedEngine.get().getModel());
    }

    @Test
    @DisplayName("Test delete Engine")
    void deleteEngine() {
        levelRepository.save(levelEntity);
        engine.setLevelEntity(levelEntity);
        engineRepository.save(engine);
        engineRepository.deleteById(engine.getId());
        Optional<Engine> deletedEngine = engineRepository.findById(engine.getId());
        assertTrue(deletedEngine.isEmpty());
    }

}