package org.eduardomaravill.sdd_catalogo.models.cars_models;

import jakarta.validation.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;


class DriverTest {
    private Driver driver;
    private Validator validator;

    @BeforeEach
    void setUp() {
        driver = new Driver(36L,100,5,6,true,new InitSkid(2L,"Freno"));
        try(ValidatorFactory factory = Validation.buildDefaultValidatorFactory()) {
            validator = factory.getValidator();
        }
    }


    @Test
    @DisplayName("Test get ID")
    void getId() {
        assertEquals(36L, driver.getId());
    }

    @Test
    @DisplayName("Test get Drive")
    void getDrive() {
        assertEquals(100, driver.getDrive());
    }

    @Test
    @DisplayName("Test get Direction")
    void getDirection() {
        assertEquals(5, driver.getDirection());
    }

    @Test
    @DisplayName("Test get Down Force")
    void getDownForce() {
        assertEquals(6, driver.getDownForce());
    }

    @Test
    @DisplayName("Test get Control Traction")
    void getControlTraction() {
        assertTrue(driver.getControlTraction());
    }

    @Test
    @DisplayName("Test get Initial Skid")
    void getInitSkid() {
        assertEquals(new InitSkid(2L,"Freno"), driver.getInitSkid());
    }

    @Test
    @DisplayName("Test change of ID")
    void setId() {
        driver.setId(37L);
        assertEquals(37L, driver.getId());
    }

    @Test
    @DisplayName("Test change of drive")
    void setDrive() {
        driver.setDrive(150);
        assertEquals(150,driver.getDrive());
    }

    @Test
    @DisplayName("Test not null and Max 200 of drive")
    void testValidDriveInDriver() {
        driver.setDrive(null);
        assertThrows(ConstraintViolationException.class, () -> validatedDriver(driver));
        driver.setDrive(201);
        assertThrows(ConstraintViolationException.class, () -> validatedDriver(driver));
    }

    @Test
    @DisplayName("Test change of direction")
    void setDirection() {
        driver.setDirection(7);
        assertEquals(7, driver.getDirection());
    }

    @Test
    @DisplayName("Test not null and Max 10 of Direction")
    void testValidDirectionInDriver() {
        driver.setDirection(null);
        assertThrows(ConstraintViolationException.class, () -> validatedDriver(driver));
        driver.setDirection(11);
        assertThrows(ConstraintViolationException.class, () -> validatedDriver(driver));
    }

    @Test
    @DisplayName("Test change of down Force")
    void setDownForce() {
        driver.setDownForce(8);
        assertEquals(8, driver.getDownForce());
    }

    @Test
    @DisplayName("Test not null an Max 10 of Down Force")
    void testValidDownForceInDriver() {
        driver.setDownForce(null);
        assertThrows(ConstraintViolationException.class, () -> validatedDriver(driver));
        driver.setDownForce(11);
        assertThrows(ConstraintViolationException.class, () -> validatedDriver(driver));
    }

    @Test
    @DisplayName("Test change of Control Traction")
    void setControlTraction() {
        driver.setControlTraction(false);
        assertFalse(driver.getControlTraction());
    }

    @Test
    @DisplayName("Test not null of Control Traction")
    void testValidControlTractionInDriver() {
        assertNotNull(driver.getControlTraction());
        driver.setControlTraction(null);
        assertThrows(ConstraintViolationException.class, () -> validatedDriver(driver));
    }

    @Test
    @DisplayName("Test change of Initial skid")
    void setInitSkid() {
        driver.setInitSkid(new InitSkid(3L,"Pistón"));
        assertEquals(new InitSkid(3L,"Pistón"), driver.getInitSkid());
    }

    @Test
    @DisplayName("Test not null of Initial skid")
    void testValidInitSkidInDriver() {
        assertNotNull(driver.getInitSkid());
        driver.setInitSkid(null);
        assertThrows(ConstraintViolationException.class, () -> validatedDriver(driver));
    }

    @Test
    @DisplayName("Test equals")
    void testEquals() {
        Driver driver2 = new Driver(36L,100,5,6,true,new InitSkid(2L,"Freno"));
        assertEquals(driver, driver2);
    }

    @Test
    @DisplayName("Test count fields and methods")
    void testNumberOfMethodsAndFields(){
        assertEquals(6, Driver.class.getDeclaredFields().length);
        assertEquals(16, Driver.class.getDeclaredMethods().length);
    }

    private void validatedDriver(Driver driver){
        Set<ConstraintViolation<Driver>> violations = validator.validate(driver);
        if (!violations.isEmpty()){
            throw  new ConstraintViolationException(violations);
        }
    }
}