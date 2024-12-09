package org.eduardomaravill.sdd_catalogo.dtos.car_dtos;

import jakarta.validation.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class SuspensionDtoTest {
    private SuspensionDto suspensionDto;
    private Validator validator;

    @BeforeEach
    void setUp() {
        suspensionDto = new SuspensionDto(5L,new StreetTypeDto(4L,"Normal"),new LevelDto(2L,"Deportivo"));
        try (ValidatorFactory factory = Validation.buildDefaultValidatorFactory()){
            validator = factory.getValidator();
        }
    }

    @Test
    @DisplayName("Test get ID")
    void getId() {
        assertEquals(5L, suspensionDto.getId());
    }

    @Test
    @DisplayName("Test get Street type")
    void getStreetType() {
        assertEquals("Normal", suspensionDto.getStreetTypeDto().getStreetTypeVal());
    }

    @Test
    @DisplayName("Test get Level")
    void getLevel() {
        assertEquals(new LevelDto(2L,"Deportivo"), suspensionDto.getLevelDto());
    }

    @Test
    @DisplayName("Test change of ID")
    void setId() {
        suspensionDto.setId(10L);
        assertEquals(10L, suspensionDto.getId());
    }

    @Test
    @DisplayName("Test change of Street Type")
    void setStreetType() {
        suspensionDto.setStreetTypeDto(new StreetTypeDto(6L,"Normal"));
        assertEquals(new StreetTypeDto(6L,"Normal"), suspensionDto.getStreetTypeDto());
    }

    @Test
    @DisplayName("Test not null of Street Type")
    void testValidStreetTypeInSuspension(){
        suspensionDto.setStreetTypeDto(null);
        assertThrows(ConstraintViolationException.class,()-> validateSuspensionDto(suspensionDto));
    }

    @Test
    @DisplayName("Test change of Level")
    void setLevel() {
        suspensionDto.setLevelDto(new LevelDto(3L,"Premium"));
        assertEquals(new LevelDto(3L,"Premium"), suspensionDto.getLevelDto());
    }

    @Test
    @DisplayName("Test not null of Level")
    void testValidLevelInSuspension(){
        suspensionDto.setLevelDto(null);
        assertThrows(ConstraintViolationException.class,()-> validateSuspensionDto(suspensionDto));
    }

    @Test
    @DisplayName("Test equals")
    void testEquals() {
        SuspensionDto suspensionDto2 = new SuspensionDto(5L,new StreetTypeDto(4L,"Normal"),new LevelDto(2L,"Deportivo"));
        assertEquals(suspensionDto, suspensionDto2);
    }

    @Test
    @DisplayName("Test count fields and methods")
    void testCountFieldsAndMethods() {
        assertEquals(3, SuspensionDto.class.getDeclaredFields().length);
        assertEquals(10, SuspensionDto.class.getDeclaredMethods().length);
    }

    private void validateSuspensionDto(SuspensionDto suspensionDto){
        Set<ConstraintViolation<SuspensionDto>> violations = validator.validate(suspensionDto);
        if (!violations.isEmpty()){
            throw new ConstraintViolationException(violations);
        }
    }
}