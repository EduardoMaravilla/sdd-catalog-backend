package org.eduardomaravill.sdd_catalogo.dtos.car_dtos;

import jakarta.validation.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class GearDtoTest {

    private GearDto gearDto;
    private Validator validator;

    @BeforeEach
    void setUp() {
        gearDto = new GearDto(1L, 1, new LevelDto(5L,"Elite"));
        try(ValidatorFactory factory = Validation.buildDefaultValidatorFactory()){
            validator = factory.getValidator();
        }
    }

    @Test
    @DisplayName("Test get ID")
    void getId() {
        assertEquals(1L, gearDto.getId());
    }

    @Test
    @DisplayName("Test get Gear")
    void getGear() {
        assertEquals(1, gearDto.getGearValue());
    }

    @Test
    @DisplayName("Test get Level")
    void getLevel() {
        assertEquals(new LevelDto(5L,"Elite"), gearDto.getLevelDto());
    }

    @Test
    @DisplayName("Test change of ID")
    void setId() {
        gearDto.setId(2L);
        assertEquals(2L, gearDto.getId());
    }

    @Test
    @DisplayName("Test change of Gear")
    void setGear() {
        gearDto.setGearValue(2);
        assertEquals(2, gearDto.getGearValue());
    }

    @Test
    @DisplayName("Test not null and Max 10 of Gear")
    void testValidGearInGear(){
        gearDto.setGearValue(null);
        assertThrows(ConstraintViolationException.class,()-> validateGearDto(gearDto));
        gearDto.setGearValue(11);
        assertThrows(ConstraintViolationException.class,()-> validateGearDto(gearDto));
    }

    @Test
    @DisplayName("Test change of Level")
    void setLevel() {
        gearDto.setLevelDto(new LevelDto(6L,"Premium"));
        assertEquals(new LevelDto(6L,"Premium"), gearDto.getLevelDto());
    }

    @Test
    @DisplayName("Test not null of Level")
    void testValidLevelInGear(){
        gearDto.setLevelDto(null);
        assertThrows(ConstraintViolationException.class,()-> validateGearDto(gearDto));
    }

    @Test
    @DisplayName("Test equals")
    void testEquals() {
        GearDto gearDto2 = new GearDto(1L, 1, new LevelDto(5L,"Elite"));
        assertEquals(gearDto, gearDto2);
    }

    @Test
    @DisplayName("Test count fields and methods")
    void testFieldsAndMethods(){
        assertEquals(3, GearDto.class.getDeclaredFields().length);
        assertEquals(10, GearDto.class.getDeclaredMethods().length);
    }

    private void validateGearDto(GearDto gearDto){
        Set<ConstraintViolation<GearDto>> violations = validator.validate(gearDto);
        if (!violations.isEmpty()){
            throw new ConstraintViolationException(violations);
        }
    }
}