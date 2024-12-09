package org.eduardomaravill.sdd_catalogo.models.cars_models;

import jakarta.validation.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class TurboTypeTest {
    private TurboType turboType;
    private Validator validator;

    @BeforeEach
    void setUp() {
        turboType = new TurboType(5L,"Turbo");
        try (ValidatorFactory factory = Validation.buildDefaultValidatorFactory()){
            validator = factory.getValidator();
        }
    }

    @Test
    @DisplayName("Test get ID")
    void getId() {
        assertEquals(5L, turboType.getId());
    }

    @Test
    @DisplayName("Test get Type")
    void getType() {
        assertEquals("Turbo", turboType.getType());
    }

    @Test
    @DisplayName("Test change of ID")
    void setId() {
        turboType.setId(10L);
        assertEquals(10L, turboType.getId());
    }

    @Test
    @DisplayName("Test change of Type")
    void setType() {
        turboType.setType("DobleTurboCompresor");
        assertEquals("DobleTurboCompresor", turboType.getType());
    }

    @Test
    @DisplayName("Test not null, not empty and size max 30 of Type")
    void testValidTurboType(){
        turboType.setType("");
        assertThrows(ConstraintViolationException.class, () -> validateTurboType(turboType));
        turboType.setType(null);
        assertThrows(ConstraintViolationException.class, () -> validateTurboType(turboType));
        turboType.setType("A".repeat(31));
        assertThrows(ConstraintViolationException.class, () -> validateTurboType(turboType));
    }

    @Test
    @DisplayName("Test equals")
    void testEquals() {
        TurboType turboType2 = new TurboType(5L,"Turbo");
        assertEquals(turboType, turboType2);
    }

    @Test
    @DisplayName("Test count fields and Methods")
    void testNumberOfMethodsAndFields() {
        assertEquals(2, TurboType.class.getDeclaredFields().length);
        assertEquals(8, TurboType.class.getDeclaredMethods().length);
    }

    private void validateTurboType(TurboType turboType){
        Set<ConstraintViolation<TurboType>> violations = validator.validate(turboType);
        if (!violations.isEmpty()){
            throw new ConstraintViolationException(violations);
        }
    }
}