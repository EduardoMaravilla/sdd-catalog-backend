package org.eduardomaravill.sdd_catalogo.dtos.car_dtos;

import jakarta.validation.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class TireDtoTest {
    private TireDto tireDto;
    private Validator validator;

    @BeforeEach
    void setUp() {
        tireDto = new TireDto(5L,new StreetTypeDto(4L,"Asfalto"),new LevelDto(3L,"Pro"));
        try (ValidatorFactory factory = Validation.buildDefaultValidatorFactory()){
            validator = factory.getValidator();
        }
    }

    @Test
    @DisplayName("Test get ID")
    void getId() {
        assertEquals(5L, tireDto.getId());
    }

    @Test
    @DisplayName("Test get Street type")
    void getStreetType() {
        assertEquals("Asfalto", tireDto.getStreetTypeDto().getStreetTypeVal());
    }

    @Test
    @DisplayName("Test get Level")
    void getLevel() {
        assertEquals(new LevelDto(3L,"Pro"), tireDto.getLevelDto());
    }

    @Test
    @DisplayName("Test change of ID")
    void setId() {
        tireDto.setId(8L);
        assertEquals(8L, tireDto.getId());
    }

    @Test
    @DisplayName("Test change of Street Type")
    void setStreetType() {
        tireDto.setStreetTypeDto(new StreetTypeDto(6L,"Normal"));
        assertEquals(new StreetTypeDto(6L,"Normal"), tireDto.getStreetTypeDto());
    }

    @Test
    @DisplayName("Test not null of Street Type")
    void testValidStreetTypeInSuspension(){
        tireDto.setStreetTypeDto(null);
        assertThrows(ConstraintViolationException.class,()-> validateTireDto(tireDto));
    }

    @Test
    @DisplayName("Test change of Level")
    void setLevel() {
        tireDto.setLevelDto(new LevelDto(3L,"Premium"));
        assertEquals(new LevelDto(3L,"Premium"), tireDto.getLevelDto());
    }

    @Test
    @DisplayName("Test not null of Level")
    void testValidLevelInSuspension(){
        tireDto.setLevelDto(null);
        assertThrows(ConstraintViolationException.class,()-> validateTireDto(tireDto));
    }

    @Test
    @DisplayName("Test equals")
    void testEquals() {
        TireDto tire1 = new TireDto(5L,new StreetTypeDto(4L,"Asfalto"),new LevelDto(3L,"Pro"));
        assertEquals(tireDto, tire1);
    }

    @Test
    @DisplayName("Test count fields and methods")
    void testCountFieldsAndMethods() {
        assertEquals(3, TireDto.class.getDeclaredFields().length);
        assertEquals(10, TireDto.class.getDeclaredMethods().length);
    }

    private void validateTireDto(TireDto tireDto){
        Set<ConstraintViolation<TireDto>> violations = validator.validate(tireDto);
        if (!violations.isEmpty()){
            throw new ConstraintViolationException(violations);
        }
    }
}