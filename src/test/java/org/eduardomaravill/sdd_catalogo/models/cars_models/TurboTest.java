package org.eduardomaravill.sdd_catalogo.models.cars_models;

import jakarta.validation.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class TurboTest {
    private Turbo turbo;
    private Validator validator;

    @BeforeEach
    void setUp() {
        turbo = new Turbo(5L, new TurboType(5L, "TurboCompresor"), new LevelEntity(5L, "Elite"));
        try (ValidatorFactory factory = Validation.buildDefaultValidatorFactory()) {
            validator = factory.getValidator();
        }
    }

    @Test
    @DisplayName("Test get ID")
    void getId() {
        assertEquals(5L, turbo.getId());
    }

    @Test
    @DisplayName("Test get Turbo Type")
    void getTurboType() {
        assertEquals("TurboCompresor", turbo.getTurboType().getType());
    }

    @Test
    @DisplayName("Test get Level")
    void getLevel() {
        assertEquals("Elite", turbo.getLevelEntity().getLevel());
    }

    @Test
    @DisplayName("Test change of ID")
    void setId() {
        turbo.setId(10L);
        assertEquals(10L, turbo.getId());
    }

    @Test
    @DisplayName("Test change of Turbo Type")
    void setTurboType() {
        turbo.setTurboType(new TurboType(10L, "TurboEco"));
        assertEquals("TurboEco", turbo.getTurboType().getType());
    }

    @Test
    @DisplayName("Test not null of Turbo Type")
    void testValidTurboTypeInTurbo() {
        turbo.setTurboType(null);
        assertThrows(ConstraintViolationException.class, () -> validateTurbo(turbo));
    }

    @Test
    @DisplayName("Test change of Level")
    void setLevel() {
        turbo.setLevelEntity(new LevelEntity(10L, "Premium"));
        assertEquals(new LevelEntity(10L, "Premium"), turbo.getLevelEntity());
    }

    @Test
    @DisplayName("Test not null of Level")
    void testValidLevelInTurbo() {
        turbo.setLevelEntity(null);
        assertThrows(ConstraintViolationException.class, () -> validateTurbo(turbo));
    }

    @Test
    @DisplayName("Test equals")
    void testEquals() {
        Turbo turbo2 = new Turbo(5L, new TurboType(5L, "TurboCompresor"), new LevelEntity(5L, "Elite"));
        assertEquals(turbo, turbo2);
    }

    @Test
    @DisplayName("Test count fields and methods")
    void testFieldsAndMethods() {
        assertEquals(3, Turbo.class.getDeclaredFields().length);
        assertEquals(10, Turbo.class.getDeclaredMethods().length);
    }

    private void validateTurbo(Turbo turbo) {
        Set<ConstraintViolation<Turbo>> violations = validator.validate(turbo);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }
    }
}