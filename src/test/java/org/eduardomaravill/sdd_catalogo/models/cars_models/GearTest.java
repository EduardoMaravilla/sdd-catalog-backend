package org.eduardomaravill.sdd_catalogo.models.cars_models;

import jakarta.validation.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class GearTest {
    private Gear gear;
    private Validator validator;

    @BeforeEach
    void setUp() {
        gear = new Gear(1L, 1, new LevelEntity(5L,"Elite"));
        try(ValidatorFactory factory = Validation.buildDefaultValidatorFactory()){
            validator = factory.getValidator();
        }
    }

    @Test
    @DisplayName("Test get ID")
    void getId() {
        assertEquals(1L, gear.getId());
    }

    @Test
    @DisplayName("Test get Gear")
    void getGear() {
        assertEquals(1, gear.getGearValue());
    }

    @Test
    @DisplayName("Test get Level")
    void getLevel() {
        assertEquals(new LevelEntity(5L,"Elite"), gear.getLevelEntity());
    }

    @Test
    @DisplayName("Test change of ID")
    void setId() {
        gear.setId(2L);
        assertEquals(2L, gear.getId());
    }

    @Test
    @DisplayName("Test change of Gear")
    void setGear() {
        gear.setGearValue(2);
        assertEquals(2, gear.getGearValue());
    }

    @Test
    @DisplayName("Test not null and Max 10 of Gear")
    void testValidGearInGear(){
        gear.setGearValue(null);
        assertThrows(ConstraintViolationException.class,()-> validateGear(gear));
        gear.setGearValue(11);
        assertThrows(ConstraintViolationException.class,()-> validateGear(gear));
    }

    @Test
    @DisplayName("Test change of Level")
    void setLevel() {
        gear.setLevelEntity(new LevelEntity(6L,"Premium"));
        assertEquals(new LevelEntity(6L,"Premium"), gear.getLevelEntity());
    }

    @Test
    @DisplayName("Test not null of Level")
    void testValidLevelInGear(){
        gear.setLevelEntity(null);
        assertThrows(ConstraintViolationException.class,()-> validateGear(gear));
    }

    @Test
    @DisplayName("Test equals")
    void testEquals() {
        Gear gear2 = new Gear(1L, 1, new LevelEntity(5L,"Elite"));
        assertEquals(gear, gear2);
    }

    @Test
    @DisplayName("Test count fields and methods")
    void testFieldsAndMethods(){
        assertEquals(3, Gear.class.getDeclaredFields().length);
        assertEquals(10, Gear.class.getDeclaredMethods().length);
    }

    private void validateGear(Gear gear){
        Set<ConstraintViolation<Gear>> violations = validator.validate(gear);
        if (!violations.isEmpty()){
            throw new ConstraintViolationException(violations);
        }
    }
}