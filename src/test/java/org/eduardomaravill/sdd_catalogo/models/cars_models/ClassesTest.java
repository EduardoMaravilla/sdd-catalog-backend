package org.eduardomaravill.sdd_catalogo.models.cars_models;

import jakarta.validation.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;


class ClassesTest {
    private Classes classes;
    private Validator validator;

    @BeforeEach
    void setup(){
        classes = new Classes(15L, "A");
        try(ValidatorFactory factory = Validation.buildDefaultValidatorFactory()){
            validator = factory.getValidator();
        }
    }

    @Test
    @DisplayName("Test get ID")
    void getId() {
        assertEquals(15L, classes.getId());
    }

    @Test
    @DisplayName("Test get name")
    void getName() {
        assertEquals("A", classes.getName());
    }

    @Test
    @DisplayName("Test change of ID")
    void setId() {
        classes.setId(16L);
        assertEquals(16L, classes.getId());
    }

    @Test
    @DisplayName("Test change of name")
    void setName() {
        classes.setName("B");
        assertEquals("B", classes.getName());
    }

    @Test
    @DisplayName("Test not null, not empty and max size 2 of name")
    void testValidNameInClasses(){
        classes.setName("");
        assertThrows(ConstraintViolationException.class, () -> validateClasses(classes));
        classes.setName(null);
        assertThrows(ConstraintViolationException.class,() -> validateClasses(classes));
        classes.setName("AAA");
        assertThrows(ConstraintViolationException.class,() -> validateClasses(classes));
    }

    @Test
    @DisplayName("Test equals")
    void testEquals() {
        Classes classes2 = new Classes(15L, "A");
        assertEquals(classes, classes2);
        assertNotEquals(classes, new Classes(16L, "A"));
    }

    @Test
    @DisplayName("Test count fields and methods")
    void testNumbersOfMethodsAndFields(){
        assertEquals(2, classes.getClass().getDeclaredFields().length);
        assertEquals(8, classes.getClass().getDeclaredMethods().length);
    }

    private void validateClasses(Classes classes){
        Set<ConstraintViolation<Classes>> violations = validator.validate(classes);
        if (!violations.isEmpty()){
            throw new ConstraintViolationException(violations);
        }
    }
}