package org.eduardomaravill.sdd_catalogo.services.cars_services;

import org.eduardomaravill.sdd_catalogo.dtos.car_dtos.CarDto;
import org.eduardomaravill.sdd_catalogo.dtos.car_dtos.MakerDto;
import org.eduardomaravill.sdd_catalogo.exceptions.ResourceNotFoundException;
import org.eduardomaravill.sdd_catalogo.models.cars_models.Car;
import org.eduardomaravill.sdd_catalogo.models.cars_models.Maker;
import org.eduardomaravill.sdd_catalogo.repositories.cars_repositories.ICarRepository;
import org.eduardomaravill.sdd_catalogo.services.MapperService;
import org.eduardomaravill.sdd_catalogo.services.cars_services.implementation.CarServiceImpl;
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
class ICarServiceTest {

    @Mock
    private ICarRepository carRepository;

    @Mock
    private IMakerService makerService;

    @Mock
    private MapperService mapperService;

    @InjectMocks
    private CarServiceImpl carService;

    private Car car;

    private CarDto carDto;

    @BeforeEach
    void setUp() {
        car = new Car(2L,new Maker(2L,"Audi"),"R8","2018");
        carDto = new CarDto(2L,new MakerDto(2L,"Audi"),"R8","2018");
    }

    @Test
    @DisplayName("Test service get Car")
    void getCar() {
        when(mapperService.convertToDto(any(Car.class))).thenReturn(carDto);
        when(carRepository.findByIdSpecific(2L)).thenReturn(Optional.of(car));
        when(carRepository.findByIdSpecific(4L)).thenThrow(new ResourceNotFoundException("Car",4L));

        CarDto result = carService.getCar(2L);
        assertNotNull(result);
        assertEquals(result.getModel(), carDto.getModel());
        assertThrows(ResourceNotFoundException.class,()-> carRepository.findByIdSpecific(4L));

        verify(mapperService).convertToDto(car);
        verify(carRepository).findByIdSpecific(2L);
    }

    @Test
    @DisplayName("Test service create Car")
    void createCar() {
        when(makerService.getMaker(anyLong())).thenReturn(new MakerDto(2L,"Audi"));
        when(carRepository.existsByName(anyString())).thenReturn(false);
        when(carRepository.save(any(Car.class))).thenReturn(car);
        when(mapperService.convertToEntity(any(CarDto.class))).thenReturn(car);

        carService.createCar(carDto);

        verify(makerService).getMaker(2L);
        verify(carRepository).existsByName(anyString());
        verify(carRepository).save(any(Car.class));
        verify(mapperService).convertToEntity(carDto);
    }

    @Test
    @DisplayName("Test service update Car")
    void updateCar() {
        when(makerService.getMaker(anyLong())).thenReturn(new MakerDto(2L,"Audi"));
        when(carRepository.existsById(anyLong())).thenReturn(true);
        when(mapperService.convertToEntity(any(CarDto.class))).thenReturn(car);
        when(carRepository.save(any(Car.class))).thenReturn(car);

        carService.updateCar(2L, carDto);

        verify(makerService).getMaker(anyLong());
        verify(carRepository).existsById(anyLong());
        verify(mapperService).convertToEntity(any(CarDto.class));
        verify(carRepository).save(any(Car.class));
    }

    @Test
    @DisplayName("Test service delete Car")
    void deleteCar() {
        when(carRepository.existsById(anyLong())).thenReturn(true);

        carService.deleteCar(2L);

        verify(carRepository).existsById(anyLong());
        verify(carRepository).deleteById(anyLong());
    }

    @Test
    @DisplayName("Test service getAllCars")
    void getAllCars() {
        when(carRepository.findAll()).thenReturn(List.of(car));
        when(mapperService.convertToDto(any(Car.class))).thenReturn(carDto);

        List<CarDto> result = carService.getAllCars();
        assertNotNull(result);
        assertEquals(result.getFirst().getModel(), carDto.getModel());
        verify(carRepository).findAll();
        verify(mapperService).convertToDto(car);
    }
}