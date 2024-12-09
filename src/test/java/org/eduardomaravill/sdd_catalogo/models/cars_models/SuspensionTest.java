package org.eduardomaravill.sdd_catalogo.models.cars_models;

import jakarta.validation.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class SuspensionTest {
    private Suspension suspension;
    private Validator validator;

    @BeforeEach
    void setUp() {
        suspension = new Suspension(5L,new StreetType(4L,"Normal"),new LevelEntity(2L,"Deportivo"));
        try (ValidatorFactory factory = Validation.buildDefaultValidatorFactory()){
            validator = factory.getValidator();
        }
    }

    @Test
    @DisplayName("Test get ID")
    void getId() {
        assertEquals(5L, suspension.getId());
    }

    @Test
    @DisplayName("Test get Street type")
    void getStreetType() {
        assertEquals("Normal", suspension.getStreetType().getStreetTypeVal());
    }

    @Test
    @DisplayName("Test get Level")
    void getLevel() {
        assertEquals(new LevelEntity(2L,"Deportivo"), suspension.getLevelEntity());
    }

    @Test
    @DisplayName("Test change of ID")
    void setId() {
        suspension.setId(10L);
        assertEquals(10L, suspension.getId());
    }

    @Test
    @DisplayName("Test change of Street Type")
    void setStreetType() {
        suspension.setStreetType(new StreetType(6L,"Normal"));
        assertEquals(new StreetType(6L,"Normal"), suspension.getStreetType());
    }

    @Test
    @DisplayName("Test not null of Street Type")
    void testValidStreetTypeInSuspension(){
        suspension.setStreetType(null);
        assertThrows(ConstraintViolationException.class,()-> validateSuspension(suspension));
    }

    @Test
    @DisplayName("Test change of Level")
    void setLevel() {
        suspension.setLevelEntity(new LevelEntity(3L,"Premium"));
        assertEquals(new LevelEntity(3L,"Premium"), suspension.getLevelEntity());
    }

    @Test
    @DisplayName("Test not null of Level")
    void testValidLevelInSuspension(){
        suspension.setLevelEntity(null);
        assertThrows(ConstraintViolationException.class,()-> validateSuspension(suspension));
    }

    @Test
    @DisplayName("Test equals")
    void testEquals() {
        Suspension suspension2 = new Suspension(5L,new StreetType(4L,"Normal"),new LevelEntity(2L,"Deportivo"));
        assertEquals(suspension, suspension2);
    }

    @Test
    @DisplayName("Test count fields and methods")
    void testCountFieldsAndMethods() {
        assertEquals(3, Suspension.class.getDeclaredFields().length);
        assertEquals(10, Suspension.class.getDeclaredMethods().length);
    }

    private void validateSuspension(Suspension suspension){
        Set<ConstraintViolation<Suspension>> violations = validator.validate(suspension);
        if (!violations.isEmpty()){
            throw new ConstraintViolationException(violations);
        }
    }
}