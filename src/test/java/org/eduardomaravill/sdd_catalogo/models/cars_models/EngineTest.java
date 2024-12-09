package org.eduardomaravill.sdd_catalogo.models.cars_models;

import jakarta.validation.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class EngineTest {
    private Engine engine;
    private Validator validator;

    @BeforeEach
    void setUp() {
        engine = new Engine(5L, 450, 5.3, "V6", new LevelEntity(1L, "Básico"));
        try (ValidatorFactory factory = Validation.buildDefaultValidatorFactory()) {
            validator = factory.getValidator();
        }
    }

    @Test
    @DisplayName("Test get ID")
    void getId() {
        assertEquals(5L, engine.getId());
    }

    @Test
    @DisplayName("Test get Bhp")
    void getBhp() {
        assertEquals(450, engine.getBhp());
    }

    @Test
    @DisplayName("Test get Liters")
    void getLiters() {
        assertEquals(5.3, engine.getLiters(), 0.01);
    }

    @Test
    @DisplayName("Test get Model")
    void getModel() {
        assertEquals("V6", engine.getModel());
    }

    @Test
    @DisplayName("Test get Level")
    void getLevel() {
        assertEquals(new LevelEntity(1L, "Básico"), engine.getLevelEntity());
    }

    @Test
    @DisplayName("Test change of ID")
    void setId() {
        engine.setId(12L);
        assertEquals(12L, engine.getId());
    }

    @Test
    @DisplayName("Test change of Bhp")
    void setBhp() {
        engine.setBhp(300);
        assertEquals(300, engine.getBhp());
    }

    @Test
    @DisplayName("Test not null and Max 3000 of Bhp")
    void testValidBhpInEngine() {
        engine.setBhp(null);
        assertThrows(ConstraintViolationException.class, () -> validateEngine(engine));
        engine.setBhp(3001);
        assertThrows(ConstraintViolationException.class, () -> validateEngine(engine));
    }

    @Test
    @DisplayName("Test change of Liters")
    void setLiters() {
        engine.setLiters(5.6);
        assertEquals(5.6, engine.getLiters(), 0.01);
    }

    @Test
    @DisplayName("Test not null and Max 20 of Liters")
    void testValidLitersInEngine() {
        engine.setLiters(null);
        assertThrows(ConstraintViolationException.class, () -> validateEngine(engine));
        engine.setLiters(21.0);
        assertThrows(ConstraintViolationException.class, () -> validateEngine(engine));
    }

    @Test
    @DisplayName("Test change of Model")
    void setModel() {
        engine.setModel("V10");
        assertEquals("V10", engine.getModel());
    }

    @Test
    @DisplayName("Test not null, not empty and size 20 of Model")
    void testValidModelInEngine() {
        engine.setModel("");
        assertThrows(ConstraintViolationException.class, () -> validateEngine(engine));
        engine.setModel(null);
        assertThrows(ConstraintViolationException.class, () -> validateEngine(engine));
        engine.setModel("V101VVF564V6F5V4F56V4F56V4F564V56FV46F54VF65V45F6");
        assertThrows(ConstraintViolationException.class, () -> validateEngine(engine));
    }

    @Test
    @DisplayName("Test change of level")
    void setLevel() {
        engine.setLevelEntity(new LevelEntity(2L, "Deportivo"));
        assertEquals(new LevelEntity(2L, "Deportivo"), engine.getLevelEntity());
    }

    @Test
    @DisplayName("Test not null Level")
    void testValidLevelInEngine() {
        assertNotNull(engine.getLevelEntity());
        engine.setLevelEntity(null);
        assertThrows(ConstraintViolationException.class, () -> validateEngine(engine));
    }

    @Test
    @DisplayName("Test equals")
    void testEquals() {
        Engine engine2 = new Engine(5L, 450, 5.3, "V6", new LevelEntity(1L, "Básico"));
        assertEquals(engine, engine2);
    }

    @Test
    @DisplayName("Test count fields and methods")
    void testCountFieldsAndMethods() {
        assertEquals(5, Engine.class.getDeclaredFields().length);
        assertEquals(14, Engine.class.getDeclaredMethods().length);
    }

    private void validateEngine(Engine engine) {
        Set<ConstraintViolation<Engine>> violations = validator.validate(engine);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }
    }
}