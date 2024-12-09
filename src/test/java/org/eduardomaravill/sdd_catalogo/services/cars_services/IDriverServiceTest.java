package org.eduardomaravill.sdd_catalogo.services.cars_services;

import org.eduardomaravill.sdd_catalogo.dtos.car_dtos.DriverDto;
import org.eduardomaravill.sdd_catalogo.dtos.car_dtos.InitSkidDto;
import org.eduardomaravill.sdd_catalogo.models.cars_models.Driver;
import org.eduardomaravill.sdd_catalogo.models.cars_models.InitSkid;
import org.eduardomaravill.sdd_catalogo.repositories.cars_repositories.IDriverRepository;
import org.eduardomaravill.sdd_catalogo.services.MapperService;
import org.eduardomaravill.sdd_catalogo.services.cars_services.implementation.DriverServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class IDriverServiceTest {

    @Mock
    private IDriverRepository driverRepository;

    @Mock
    private IInitSkidService initSkidService;

    @Mock
    private MapperService mapperService;

    @InjectMocks
    private DriverServiceImpl driverService;

    private Driver driver;
    private DriverDto driverDto;

    @BeforeEach
    void setUp() {
        driver = new Driver(4L,200,8,3,false,new InitSkid(2L,"POR DEFECTO"));
        driverDto = new DriverDto(4L,200,8,3,false,new InitSkidDto(2L,"POR DEFECTO"));
    }

    @Test
    @DisplayName("Test service get Driver")
    void getDriver() {
        when(driverRepository.findByIdSpecific(anyLong())).thenReturn(Optional.of(driver));
        when(mapperService.convertToDto(any(Driver.class))).thenReturn(driverDto);

        assertEquals(driverDto, driverService.getDriver(4L));

        verify(driverRepository).findByIdSpecific(4L);
        verify(mapperService).convertToDto(driver);
    }

    @Test
    @DisplayName("Test service create Driver")
    void createDriver() {
        when(initSkidService.getInitSkid(anyLong())).thenReturn(new InitSkidDto(2L,"POR DEFECTO"));
        when(driverRepository.existsDriver(anyInt(),anyInt(),anyInt(),anyBoolean(),anyLong())).thenReturn(false);
        when(mapperService.convertToEntity(any(DriverDto.class))).thenReturn(driver);
        when(driverRepository.save(any(Driver.class))).thenReturn(driver);

        driverService.createDriver(driverDto);

        verify(initSkidService).getInitSkid(2L);
        verify(driverRepository).existsDriver(200,8,3,false,2L);
        verify(mapperService).convertToEntity(driverDto);
        verify(driverRepository).save(driver);
    }

    @Test
    @DisplayName("Test service update Driver")
    void updateDriver() {
        when(initSkidService.getInitSkid(anyLong())).thenReturn(new InitSkidDto(2L,"POR DEFECTO"));
        when(driverRepository.existsById(anyLong())).thenReturn(true);
        when(mapperService.convertToEntity(any(DriverDto.class))).thenReturn(driver);
        when(driverRepository.save(any(Driver.class))).thenReturn(driver);

        driverService.updateDriver(4L, driverDto);

        verify(initSkidService).getInitSkid(2L);
        verify(driverRepository).existsById(4L);
        verify(mapperService).convertToEntity(driverDto);
        verify(driverRepository).save(driver);
    }

    @Test
    @DisplayName("Test service delete Driver")
    void deleteDriver() {
        when(driverRepository.existsById(anyLong())).thenReturn(true);

        driverService.deleteDriver(4L);

        verify(driverRepository).existsById(4L);
        verify(driverRepository).deleteById(4L);
    }

    @Test
    @DisplayName("Test service getAllDrivers")
    void getAllDrivers() {
        when(driverRepository.findAll()).thenReturn(List.of(driver));
        when(mapperService.convertToDto(any(Driver.class))).thenReturn(driverDto);

        assertEquals(List.of(driverDto), driverService.getAllDrivers());

        verify(driverRepository).findAll();
        verify(mapperService).convertToDto(any(Driver.class));
    }
}