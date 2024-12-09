package org.eduardomaravill.sdd_catalogo.dtos.car_dtos;

import jakarta.validation.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class DriverDtoTest {

    private DriverDto driverDto;
    private Validator validator;

    @BeforeEach
    void setUp() {
        driverDto = new DriverDto(36L,100,5,6,true,new InitSkidDto(2L,"Freno"));
        try(ValidatorFactory factory = Validation.buildDefaultValidatorFactory()) {
            validator = factory.getValidator();
        }
    }


    @Test
    @DisplayName("Test get ID")
    void getId() {
        assertEquals(36L, driverDto.getId());
    }

    @Test
    @DisplayName("Test get Drive")
    void getDrive() {
        assertEquals(100, driverDto.getDrive());
    }

    @Test
    @DisplayName("Test get Direction")
    void getDirection() {
        assertEquals(5, driverDto.getDirection());
    }

    @Test
    @DisplayName("Test get Down Force")
    void getDownForce() {
        assertEquals(6, driverDto.getDownForce());
    }

    @Test
    @DisplayName("Test get Control Traction")
    void getControlTraction() {
        assertTrue(driverDto.getControlTraction());
    }

    @Test
    @DisplayName("Test get Initial Skid")
    void getInitSkid() {
        assertEquals(new InitSkidDto(2L,"Freno"), driverDto.getInitSkidDto());
    }

    @Test
    @DisplayName("Test change of ID")
    void setId() {
        driverDto.setId(37L);
        assertEquals(37L, driverDto.getId());
    }

    @Test
    @DisplayName("Test change of drive")
    void setDrive() {
        driverDto.setDrive(150);
        assertEquals(150, driverDto.getDrive());
    }

    @Test
    @DisplayName("Test not null and Max 200 of drive")
    void testValidDriveInDriver() {
        driverDto.setDrive(null);
        assertThrows(ConstraintViolationException.class, () -> validatedDriverDto(driverDto));
        driverDto.setDrive(201);
        assertThrows(ConstraintViolationException.class, () -> validatedDriverDto(driverDto));
    }

    @Test
    @DisplayName("Test change of direction")
    void setDirection() {
        driverDto.setDirection(7);
        assertEquals(7, driverDto.getDirection());
    }

    @Test
    @DisplayName("Test not null and Max 10 of Direction")
    void testValidDirectionInDriver() {
        driverDto.setDirection(null);
        assertThrows(ConstraintViolationException.class, () -> validatedDriverDto(driverDto));
        driverDto.setDirection(11);
        assertThrows(ConstraintViolationException.class, () -> validatedDriverDto(driverDto));
    }

    @Test
    @DisplayName("Test change of down Force")
    void setDownForce() {
        driverDto.setDownForce(8);
        assertEquals(8, driverDto.getDownForce());
    }

    @Test
    @DisplayName("Test not null an Max 10 of Down Force")
    void testValidDownForceInDriver() {
        driverDto.setDownForce(null);
        assertThrows(ConstraintViolationException.class, () -> validatedDriverDto(driverDto));
        driverDto.setDownForce(11);
        assertThrows(ConstraintViolationException.class, () -> validatedDriverDto(driverDto));
    }

    @Test
    @DisplayName("Test change of Control Traction")
    void setControlTraction() {
        driverDto.setControlTraction(false);
        assertFalse(driverDto.getControlTraction());
    }

    @Test
    @DisplayName("Test not null of Control Traction")
    void testValidControlTractionInDriver() {
        assertNotNull(driverDto.getControlTraction());
        driverDto.setControlTraction(null);
        assertThrows(ConstraintViolationException.class, () -> validatedDriverDto(driverDto));
    }

    @Test
    @DisplayName("Test change of Initial skid")
    void setInitSkid() {
        driverDto.setInitSkidDto(new InitSkidDto(3L,"Pistón"));
        assertEquals(new InitSkidDto(3L,"Pistón"), driverDto.getInitSkidDto());
    }

    @Test
    @DisplayName("Test not null of Initial skid")
    void testValidInitSkidInDriver() {
        assertNotNull(driverDto.getInitSkidDto());
        driverDto.setInitSkidDto(null);
        assertThrows(ConstraintViolationException.class, () -> validatedDriverDto(driverDto));
    }

    @Test
    @DisplayName("Test equals")
    void testEquals() {
        DriverDto driverDto2 = new DriverDto(36L,100,5,6,true,new InitSkidDto(2L,"Freno"));
        assertEquals(driverDto, driverDto2);
    }

    @Test
    @DisplayName("Test count fields and methods")
    void testNumberOfMethodsAndFields(){
        assertEquals(6, DriverDto.class.getDeclaredFields().length);
        assertEquals(16, DriverDto.class.getDeclaredMethods().length);
    }

    private void validatedDriverDto(DriverDto driverDto){
        Set<ConstraintViolation<DriverDto>> violations = validator.validate(driverDto);
        if (!violations.isEmpty()){
            throw  new ConstraintViolationException(violations);
        }
    }
}