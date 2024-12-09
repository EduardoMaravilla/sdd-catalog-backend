package org.eduardomaravill.sdd_catalogo.models.cars_models;

import jakarta.validation.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class MakerTest {
    private Maker maker;
    private Validator validator;

    @BeforeEach
    void setUp() {
        maker = new Maker(5L,"Chevrolet");
        try (ValidatorFactory factory = Validation.buildDefaultValidatorFactory()){
            validator = factory.getValidator();
        }
    }

    @Test
    @DisplayName("Test get ID")
    void getId() {
        assertEquals(5L, maker.getId());
    }

    @Test
    @DisplayName("Test get Maker")
    void getLevel() {
        assertEquals("Chevrolet", maker.getName());
    }

    @Test
    @DisplayName("Test change of ID")
    void setId() {
        maker.setId(10L);
        assertEquals(10L, maker.getId());
    }

    @Test
    @DisplayName("Test change of Maker")
    void setLevel() {
        maker.setName("SRT");
        assertEquals("SRT", maker.getName());
    }

    @Test
    @DisplayName("Test not null,not empty and size max 20 of Maker")
    void testNumbersOfMethodsAndFields(){
        maker.setName("");
        assertThrows(ConstraintViolationException.class, () -> validateMaker(maker));
        maker.setName(null);
        assertThrows(ConstraintViolationException.class, () -> validateMaker(maker));
        maker.setName("D".repeat(21));
        assertThrows(ConstraintViolationException.class, () -> validateMaker(maker));
    }

    @Test
    @DisplayName("Test equals")
    void testEquals() {
        Maker maker2 = new Maker(5L, "Chevrolet");
        assertEquals(maker, maker2);
    }

    @Test
    @DisplayName("Test count fields and Methods")
    void testNumberOfMethodsAndFields() {
        assertEquals(2, Maker.class.getDeclaredFields().length);
        assertEquals(8, Maker.class.getDeclaredMethods().length);
    }

    private void validateMaker(Maker maker){
        Set<ConstraintViolation<Maker>> violations = validator.validate(maker);
        if (!violations.isEmpty()){
            throw new ConstraintViolationException(violations);
        }
    }
}