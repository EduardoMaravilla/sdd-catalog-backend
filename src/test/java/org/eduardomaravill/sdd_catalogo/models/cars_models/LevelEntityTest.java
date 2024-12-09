package org.eduardomaravill.sdd_catalogo.models.cars_models;

import jakarta.validation.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class LevelEntityTest {
    private LevelEntity levelEntity;
    private Validator validator;

    @BeforeEach
    void setUp() {
        levelEntity = new LevelEntity(5L,"Promedio");
        try (ValidatorFactory factory = Validation.buildDefaultValidatorFactory()){
            validator = factory.getValidator();
        }
    }

    @Test
    @DisplayName("Test get ID")
    void getId() {
        assertEquals(5L, levelEntity.getId());
    }

    @Test
    @DisplayName("Test get Level")
    void getLevel() {
        assertEquals("Promedio", levelEntity.getLevel());
    }

    @Test
    @DisplayName("Test change of ID")
    void setId() {
        levelEntity.setId(10L);
        assertEquals(10L, levelEntity.getId());
    }

    @Test
    @DisplayName("Test change of Level")
    void setLevel() {
        levelEntity.setLevel("Avanzado");
        assertEquals("Avanzado", levelEntity.getLevel());
    }

    @Test
    @DisplayName("Test not null,not empty and size max 10 of Level")
    void testNumbersOfMethodsAndFields(){
        levelEntity.setLevel("");
        assertThrows(ConstraintViolationException.class, () -> validateLevel(levelEntity));
        levelEntity.setLevel(null);
        assertThrows(ConstraintViolationException.class, () -> validateLevel(levelEntity));
        levelEntity.setLevel("D".repeat(11));
        assertThrows(ConstraintViolationException.class, () -> validateLevel(levelEntity));
    }

    @Test
    @DisplayName("Test equals")
    void testEquals() {
        LevelEntity levelEntity2 = new LevelEntity(5L, "Promedio");
        assertEquals(levelEntity, levelEntity2);
    }

    @Test
    @DisplayName("Test count fields and Methods")
    void testNumberOfMethodsAndFields() {
        assertEquals(2, LevelEntity.class.getDeclaredFields().length);
        assertEquals(8, LevelEntity.class.getDeclaredMethods().length);
    }

    private void validateLevel(LevelEntity levelEntity){
        Set<ConstraintViolation<LevelEntity>> violations = validator.validate(levelEntity);
        if (!violations.isEmpty()){
            throw new ConstraintViolationException(violations);
        }
    }
}