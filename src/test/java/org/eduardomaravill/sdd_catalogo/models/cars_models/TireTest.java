package org.eduardomaravill.sdd_catalogo.models.cars_models;

import jakarta.validation.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class TireTest {
    private Tire tire;
    private Validator validator;

    @BeforeEach
    void setUp() {
        tire = new Tire(5L,new StreetType(4L,"Asfalto"),new LevelEntity(3L,"Pro"));
        try (ValidatorFactory factory = Validation.buildDefaultValidatorFactory()){
            validator = factory.getValidator();
        }
    }

    @Test
    @DisplayName("Test get ID")
    void getId() {
        assertEquals(5L, tire.getId());
    }

    @Test
    @DisplayName("Test get Street type")
    void getStreetType() {
        assertEquals("Asfalto", tire.getStreetType().getStreetTypeVal());
    }

    @Test
    @DisplayName("Test get Level")
    void getLevel() {
        assertEquals(new LevelEntity(3L,"Pro"), tire.getLevelEntity());
    }

    @Test
    @DisplayName("Test change of ID")
    void setId() {
        tire.setId(8L);
        assertEquals(8L, tire.getId());
    }

    @Test
    @DisplayName("Test change of Street Type")
    void setStreetType() {
        tire.setStreetType(new StreetType(6L,"Normal"));
        assertEquals(new StreetType(6L,"Normal"), tire.getStreetType());
    }

    @Test
    @DisplayName("Test not null of Street Type")
    void testValidStreetTypeInSuspension(){
        tire.setStreetType(null);
        assertThrows(ConstraintViolationException.class,()-> validateTire(tire));
    }

    @Test
    @DisplayName("Test change of Level")
    void setLevel() {
        tire.setLevelEntity(new LevelEntity(3L,"Premium"));
        assertEquals(new LevelEntity(3L,"Premium"), tire.getLevelEntity());
    }

    @Test
    @DisplayName("Test not null of Level")
    void testValidLevelInSuspension(){
        tire.setLevelEntity(null);
        assertThrows(ConstraintViolationException.class,()-> validateTire(tire));
    }

    @Test
    @DisplayName("Test equals")
    void testEquals() {
        Tire tire1 = new Tire(5L,new StreetType(4L,"Asfalto"),new LevelEntity(3L,"Pro"));
        assertEquals(tire, tire1);
    }

    @Test
    @DisplayName("Test count fields and methods")
    void testCountFieldsAndMethods() {
        assertEquals(3, Tire.class.getDeclaredFields().length);
        assertEquals(10, Tire.class.getDeclaredMethods().length);
    }

    private void validateTire(Tire tire){
        Set<ConstraintViolation<Tire>> violations = validator.validate(tire);
        if (!violations.isEmpty()){
            throw new ConstraintViolationException(violations);
        }
    }
}