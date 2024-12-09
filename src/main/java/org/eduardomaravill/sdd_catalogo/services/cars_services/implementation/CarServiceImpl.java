package org.eduardomaravill.sdd_catalogo.services.cars_services.implementation;

import jakarta.transaction.Transactional;
import org.eduardomaravill.sdd_catalogo.dtos.car_dtos.CarDto;
import org.eduardomaravill.sdd_catalogo.dtos.car_dtos.MakerDto;
import org.eduardomaravill.sdd_catalogo.exceptions.DataNotFoundException;
import org.eduardomaravill.sdd_catalogo.exceptions.ResourceDuplicateException;
import org.eduardomaravill.sdd_catalogo.exceptions.ResourceNotFoundException;
import org.eduardomaravill.sdd_catalogo.models.cars_models.Car;
import org.eduardomaravill.sdd_catalogo.repositories.cars_repositories.ICarRepository;
import org.eduardomaravill.sdd_catalogo.services.MapperService;
import org.eduardomaravill.sdd_catalogo.services.cars_services.ICarService;
import org.eduardomaravill.sdd_catalogo.services.cars_services.IMakerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarServiceImpl implements ICarService {

    private static final String CAR_MESSAGE = "Car";

    private final ICarRepository carRepository;

    private final IMakerService makerService;

    private final MapperService mapperService;

    @Autowired
    public CarServiceImpl(ICarRepository carRepository, IMakerService makerService, MapperService mapperService) {
        this.carRepository = carRepository;
        this.makerService = makerService;
        this.mapperService = mapperService;
    }

    @Override
    @Cacheable(value = "cars", key = "#id")
    public CarDto getCar(Long id) {
        Car car = carRepository.findByIdSpecific(id).orElseThrow(() -> new ResourceNotFoundException(CAR_MESSAGE, id));
        return mapperService.convertToDto(car);
    }

    @Override
    @Transactional
    @CacheEvict(value = "allCars", allEntries = true)
    public void createCar(CarDto carDto) {
        verifyDataExists(carDto);
        if (carRepository.existsByName(carDto.getModel())) {
            throw new ResourceDuplicateException(CAR_MESSAGE, carDto.getModel());
        }
        Car car = mapperService.convertToEntity(carDto);
        carRepository.save(car);
    }

    @Override
    @Transactional
    @CacheEvict(value = {"allCars", "cars"}, allEntries = true)
    public void updateCar(Long id, CarDto carDto) {
        verifyDataExists(carDto);
        verifyExists(id);
        carDto.setId(id);
        Car car = mapperService.convertToEntity(carDto);
        carRepository.save(car);
    }

    @Override
    @Transactional
    @CacheEvict(value = {"allCars", "cars"}, allEntries = true)
    public void deleteCar(Long id) {
        verifyExists(id);
        carRepository.deleteById(id);
    }

    @Override
    @Cacheable(value = "allCars")
    public List<CarDto> getAllCars() {
        return carRepository.findAll().stream().map(mapperService::convertToDto).toList();
    }

    private void verifyDataExists(CarDto carDto) {
        MakerDto makerDto = makerService.getMaker(carDto.getMakerDto().getId());
        if (!makerDto.equals(carDto.getMakerDto())) {
            throw new DataNotFoundException(carDto.getMakerDto().getName());
        }
    }

    private void verifyExists(Long id) {
        if (!carRepository.existsById(id)) {
            throw new ResourceNotFoundException(CAR_MESSAGE, id);
        }
    }
}
