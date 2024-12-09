package org.eduardomaravill.sdd_catalogo.repositories.cars_repositories;

import org.eduardomaravill.sdd_catalogo.models.cars_models.LevelEntity;
import org.eduardomaravill.sdd_catalogo.models.cars_models.Turbo;
import org.eduardomaravill.sdd_catalogo.models.cars_models.TurboType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest(properties = {"spring.profiles.active=test"})
class ITurboRepositoryTest {

    @Autowired
    private ITurboRepository turboRepository;

    @Autowired
    private ILevelRepository levelRepository;

    @Autowired
    private ITurboTypeRepository turboTypeRepository;

    private Turbo turbo;

    private LevelEntity levelEntity;

    private TurboType turboType;

    @BeforeEach
    void setUp() {
        turbo = new Turbo();
        levelEntity = new LevelEntity();
        levelEntity.setLevel("Elite");
        turboType = new TurboType();
        turboType.setType("SobreAlimentadorRoots");
    }

    @Test
    @DisplayName("Test save Turbo")
    void testSaveTurbo() {
        levelRepository.save(levelEntity);
        turboTypeRepository.save(turboType);
        turbo.setLevelEntity(levelEntity);
        turbo.setTurboType(turboType);
        turboRepository.save(turbo);
        assertNotNull(turbo.getId());
        Optional<Turbo> savedTurbo = turboRepository.findById(turbo.getId());
        assertTrue(savedTurbo.isPresent());
        assertEquals(turbo.getLevelEntity().getLevel(), savedTurbo.get().getLevelEntity().getLevel());
        assertEquals("SobreAlimentadorRoots", savedTurbo.get().getTurboType().getType());
    }

    @Test
    @DisplayName("Test update Turbo")
    void testUpdateTurbo() {
        levelRepository.save(levelEntity);
        turboTypeRepository.save(turboType);
        turbo.setLevelEntity(levelEntity);
        turbo.setTurboType(turboType);
        turboRepository.save(turbo);
        LevelEntity levelEntity1 = new LevelEntity();
        levelEntity1.setLevel("Básico");
        TurboType turboType1 = new TurboType();
        turboType1.setType("AlimentaciónNatural");
        levelRepository.save(levelEntity1);
        turboTypeRepository.save(turboType1);
        turbo.setLevelEntity(levelEntity1);
        turbo.setTurboType(turboType1);
        turboRepository.save(turbo);
        Optional<Turbo> updatedTurbo = turboRepository.findById(turbo.getId());
        assertTrue(updatedTurbo.isPresent());
        assertEquals("Básico", updatedTurbo.get().getLevelEntity().getLevel());
        assertEquals("AlimentaciónNatural", updatedTurbo.get().getTurboType().getType());
    }

    @Test
    @DisplayName("Test delete Turbo")
    void testDeleteTurbo() {
        levelRepository.save(levelEntity);
        turboTypeRepository.save(turboType);
        turbo.setLevelEntity(levelEntity);
        turbo.setTurboType(turboType);
        turboRepository.save(turbo);
        turboRepository.deleteById(turbo.getId());
        Optional<Turbo> deletedTurbo = turboRepository.findById(turbo.getId());
        assertFalse(deletedTurbo.isPresent());
    }
}