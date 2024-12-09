package org.eduardomaravill.sdd_catalogo.models.cars_models;

import jakarta.validation.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class StreetTypeTest {
    private StreetType streetType;
    private Validator validator;

    @BeforeEach
    void setUp() {
        streetType = new StreetType(5L,"Asfalto");
        try (ValidatorFactory factory = Validation.buildDefaultValidatorFactory()){
            validator = factory.getValidator();
        }
    }

    @Test
    @DisplayName("Test get ID")
    void getId() {
        assertEquals(5L, streetType.getId());
    }

    @Test
    @DisplayName("Test get Street Type")
    void getStreetType() {
        assertEquals("Asfalto", streetType.getStreetTypeVal());
    }

    @Test
    @DisplayName("Test change of ID")
    void setId() {
        streetType.setId(15L);
        assertEquals(15L,streetType.getId());
    }

    @Test
    @DisplayName("Test change of StreetType")
    void setStreetType() {
        streetType.setStreetTypeVal("Agarre");
        assertEquals("Agarre", streetType.getStreetTypeVal());
    }

    @Test
    @DisplayName("Test not null, not empty and size max 20 of Street Type")
    void testValidStreetTypeInStreetType(){
        streetType.setStreetTypeVal("");
        assertThrows(ConstraintViolationException.class, () -> validateStreetType(streetType));
        streetType.setStreetTypeVal(null);
        assertThrows(ConstraintViolationException.class, () -> validateStreetType(streetType));
        streetType.setStreetTypeVal("A".repeat(21));
        assertThrows(ConstraintViolationException.class, () -> validateStreetType(streetType));
    }

    @Test
    @DisplayName("Test equals")
    void testEquals() {
        StreetType streetType2 = new StreetType(5L,"Asfalto");
        assertEquals(streetType, streetType2);
    }

    @Test
    @DisplayName("Test count fields and methods")
    void testCountFieldsAndMethods(){
        assertEquals(2, StreetType.class.getDeclaredFields().length);
        assertEquals(8, StreetType.class.getDeclaredMethods().length);
    }

    private void validateStreetType(StreetType streetType){
        Set<ConstraintViolation<StreetType>> violations = validator.validate(streetType);
        if (!violations.isEmpty()){
            throw new ConstraintViolationException(violations);
        }
    }
}