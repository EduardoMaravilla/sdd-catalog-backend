package org.eduardomaravill.sdd_catalogo.models.cars_models;

import jakarta.validation.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class InitSkidTest {
    private InitSkid initSkid;
    private Validator validator;

    @BeforeEach
    void setUp() {
        initSkid = new InitSkid(3L,"NO");
        try (ValidatorFactory factory = Validation.buildDefaultValidatorFactory()){
            validator = factory.getValidator();
        }
    }

    @Test
    @DisplayName("Test get ID")
    void getId() {
        assertEquals(3L, initSkid.getId());
    }

    @Test
    @DisplayName("Test get Skid Type")
    void getSkidType() {
        assertEquals("NO", initSkid.getSkidType());
    }

    @Test
    @DisplayName("Test change of ID")
    void setId() {
        initSkid.setId(4L);
        assertEquals(4L, initSkid.getId());
    }

    @Test
    @DisplayName("Test change of Skid Type")
    void setSkidType() {
        initSkid.setSkidType("POR DEFECTO");
        assertEquals("POR DEFECTO", initSkid.getSkidType());
    }

    @Test
    @DisplayName("Test not null, not empty and size max 20 of Skid Type")
    void testValidSkidTypeInInitSkid(){
        initSkid.setSkidType("");
        assertThrows(ConstraintViolationException.class,()-> validateInitSkid(initSkid));
        initSkid.setSkidType(null);
        assertThrows(ConstraintViolationException.class,()-> validateInitSkid(initSkid));
        initSkid.setSkidType("A".repeat(21));
        assertThrows(ConstraintViolationException.class,()-> validateInitSkid(initSkid));
    }

    @Test
    @DisplayName("Test equals")
    void testEquals() {
        InitSkid initSkid2 = new InitSkid(3L,"NO");
        assertEquals(initSkid, initSkid2);
    }

    @Test
    @DisplayName("Test count fields and Methods")
    void testCountFieldsAndMethods() {
        assertEquals(2, InitSkid.class.getDeclaredFields().length);
        assertEquals(8, InitSkid.class.getDeclaredMethods().length);
    }

    private void validateInitSkid(InitSkid initSkid){
        Set<ConstraintViolation<InitSkid>> violations = validator.validate(initSkid);
        if (!violations.isEmpty()){
            throw new ConstraintViolationException(violations);
        }
    }
}