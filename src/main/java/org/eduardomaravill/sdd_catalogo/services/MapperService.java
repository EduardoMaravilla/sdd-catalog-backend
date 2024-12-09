package org.eduardomaravill.sdd_catalogo.services;

import org.eduardomaravill.sdd_catalogo.dtos.car_dtos.*;
import org.eduardomaravill.sdd_catalogo.models.cars_models.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MapperService {

    private final ModelMapper modelMapper;

    @Autowired
    public MapperService(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    //Convert Maker to MakerDto
    public MakerDto convertToDto(Maker maker) {
        return modelMapper.map(maker, MakerDto.class);
    }

    //Convert MakerDto to Maker
    public Maker convertToEntity(MakerDto makerDto) {
        return modelMapper.map(makerDto, Maker.class);
    }

    //Convert Classes to ClassesDto
    public ClassesDto convertToDto(Classes classes) {
        return modelMapper.map(classes, ClassesDto.class);
    }

    //Convert ClassesDto to Classes
    public Classes convertToEntity(ClassesDto classesDto) {
        return modelMapper.map(classesDto, Classes.class);
    }

    //Convert Level to LevelDto
    public LevelDto convertToDto(LevelEntity levelEntity) {
        return modelMapper.map(levelEntity, LevelDto.class);
    }

    //Convert LevelDto to Level
    public LevelEntity convertToEntity(LevelDto levelDto) {
        return modelMapper.map(levelDto, LevelEntity.class);
    }

    //Convert TurboType to TurboTypeDto
    public TurboTypeDto convertToDto(TurboType turboType){
        return modelMapper.map(turboType, TurboTypeDto.class);
    }

    //Convert TurboTypeDto to TurboType
    public TurboType convertToEntity(TurboTypeDto turboTypeDto){
        return modelMapper.map(turboTypeDto, TurboType.class);
    }

    //Convert StreetType to StreetTypeDto
    public StreetTypeDto convertToDto(StreetType streetType){
        return modelMapper.map(streetType, StreetTypeDto.class);
    }

    //Convert StreetTypeDto to StreetType
    public StreetType convertToEntity(StreetTypeDto streetTypeDto){
        return modelMapper.map(streetTypeDto, StreetType.class);
    }

    //Convert InitSkid to InitSkidDto
    public InitSkidDto convertToDto(InitSkid initSkid) {
        return modelMapper.map(initSkid, InitSkidDto.class);
    }

    //Convert InitSkidDto to InitSkid
    public InitSkid convertToEntity(InitSkidDto initSkidDto) {
        return modelMapper.map(initSkidDto, InitSkid.class);
    }

    //Convert Car to CarDto
    public CarDto convertToDto(Car car) {
        CarDto carDto = modelMapper.map(car, CarDto.class);
        carDto.setMakerDto(convertToDto(car.getMaker()));
        return carDto;
    }

    //Convert CarDto to Car
    public Car convertToEntity(CarDto carDto) {
        Car car = modelMapper.map(carDto, Car.class);
        car.setMaker(convertToEntity(carDto.getMakerDto()));
        return car;
    }

    //Convert Gear to GearDto
    public GearDto convertToDto(Gear gear) {
        GearDto gearDto = modelMapper.map(gear,GearDto.class);
        gearDto.setLevelDto(convertToDto(gear.getLevelEntity()));
        return gearDto;
    }

    //Convert GearDto to Gear
    public Gear convertToEntity(GearDto gearDto) {
        Gear gear = modelMapper.map(gearDto, Gear.class);
        gear.setLevelEntity(convertToEntity(gearDto.getLevelDto()));
        return gear;
    }

    //Convert Engine to EngineDto
    public EngineDto convertToDto(Engine engine) {
        EngineDto engineDto = modelMapper.map(engine,EngineDto.class);
        engineDto.setLevelDto(convertToDto(engine.getLevelEntity()));
        return engineDto;
    }

    //Convert EngineDto to Engine
    public Engine convertToEntity(EngineDto engineDto) {
        Engine engine = modelMapper.map(engineDto, Engine.class);
        engine.setLevelEntity(convertToEntity(engineDto.getLevelDto()));
        return engine;
    }

    //Convert Driver to DriverDto
    public DriverDto convertToDto(Driver driver){
        DriverDto driverDto = modelMapper.map(driver, DriverDto.class);
        driverDto.setInitSkidDto(convertToDto(driver.getInitSkid()));
        return driverDto;
    }

    //Convert DriverDto to Driver
    public Driver convertToEntity(DriverDto driverDto){
        Driver driver = modelMapper.map(driverDto, Driver.class);
        driver.setInitSkid(convertToEntity(driverDto.getInitSkidDto()));
        return driver;
    }

    //Convert Turbo to TurboDto
    public TurboDto convertToDto(Turbo turbo){
        TurboDto turboDto = modelMapper.map(turbo,TurboDto.class);
        turboDto.setLevelDto(convertToDto(turbo.getLevelEntity()));
        turboDto.setTurboTypeDto(convertToDto(turbo.getTurboType()));
        return turboDto;
    }

    //Convert TurboDto to Turbo
    public Turbo convertToEntity(TurboDto turboDto){
        Turbo turbo = modelMapper.map(turboDto, Turbo.class);
        turbo.setLevelEntity(convertToEntity(turboDto.getLevelDto()));
        turbo.setTurboType(convertToEntity(turboDto.getTurboTypeDto()));
        return turbo;
    }

    //Convert Tire to TireDto
    public TireDto convertToDto(Tire tire){
        TireDto tireDto = modelMapper.map(tire, TireDto.class);
        tireDto.setStreetTypeDto(convertToDto(tire.getStreetType()));
        tireDto.setLevelDto(convertToDto(tire.getLevelEntity()));
        return tireDto;
    }

    //Convert TireDto to Tire
    public Tire convertToEntity(TireDto tireDto){
        Tire tire = modelMapper.map(tireDto, Tire.class);
        tire.setStreetType(convertToEntity(tireDto.getStreetTypeDto()));
        tire.setLevelEntity(convertToEntity(tireDto.getLevelDto()));
        return tire;
    }

    //Convert Suspension to SuspensionDto
    public SuspensionDto convertToDto(Suspension suspension){
        SuspensionDto suspensionDto = modelMapper.map(suspension, SuspensionDto.class);
        suspensionDto.setStreetTypeDto(convertToDto(suspension.getStreetType()));
        suspensionDto.setLevelDto(convertToDto(suspension.getLevelEntity()));
        return suspensionDto;
    }

    //Convert SuspensionDto to Suspension
    public Suspension convertToEntity(SuspensionDto suspensionDto){
        Suspension suspension = modelMapper.map(suspensionDto, Suspension.class);
        suspension.setStreetType(convertToEntity(suspensionDto.getStreetTypeDto()));
        suspension.setLevelEntity(convertToEntity(suspensionDto.getLevelDto()));
        return suspension;
    }


    //Convert CarConfiguration to CarConfigurationDto
    public CarConfigurationDto convertToDto(CarConfiguration carConfiguration){
        CarConfigurationDto carConfigurationDto = modelMapper.map(carConfiguration, CarConfigurationDto.class);

        carConfigurationDto.setCarDto(convertToDto(carConfiguration.getCar()));
        carConfigurationDto.setEngineDto(convertToDto(carConfiguration.getEngine()));

        carConfigurationDto.setInductionLevelDto(convertToDto(carConfiguration.getInductionLevelEntity()));
        carConfigurationDto.setEcuLevelDto(convertToDto(carConfiguration.getEcuLevelEntity()));
        carConfigurationDto.setInjectionLevelDto(convertToDto(carConfiguration.getInjectionLevelEntity()));
        carConfigurationDto.setEscapeLevelDto(convertToDto(carConfiguration.getEscapeLevelEntity()));
        carConfigurationDto.setTurboDto(convertToDto(carConfiguration.getTurbo()));
        carConfigurationDto.setNitroLevelDto(convertToDto(carConfiguration.getNitroLevelEntity()));

        carConfigurationDto.setSuspensionDto(convertToDto(carConfiguration.getSuspension()));
        carConfigurationDto.setBrakeLevelDto(convertToDto(carConfiguration.getBrakeLevelEntity()));
        carConfigurationDto.setTireDto(convertToDto(carConfiguration.getTire()));

        carConfigurationDto.setEmbragueLevelDto(convertToDto(carConfiguration.getEmbragueLevelEntity()));
        carConfigurationDto.setGearDto(convertToDto(carConfiguration.getGear()));
        carConfigurationDto.setDifferentialLevelDto(convertToDto(carConfiguration.getDifferentialLevelEntity()));

        carConfigurationDto.setDriverDto(convertToDto(carConfiguration.getDriver()));
        carConfigurationDto.setAuxiliarOneDto(convertToDto(carConfiguration.getAuxiliaryOne()));
        carConfigurationDto.setAuxiliarTwoDto(convertToDto(carConfiguration.getAuxiliaryTwo()));
        carConfigurationDto.setClassesDto(convertToDto(carConfiguration.getClasses()));
        return carConfigurationDto;
    }

    //Convert CarConfigurationDto to carConfiguration
    public CarConfiguration convertToEntity(CarConfigurationDto carConfigurationDto){
        CarConfiguration carConfiguration = modelMapper.map(carConfigurationDto, CarConfiguration.class);

        carConfiguration.setCar(convertToEntity(carConfigurationDto.getCarDto()));
        carConfiguration.setEngine(convertToEntity(carConfigurationDto.getEngineDto()));

        carConfiguration.setInductionLevelEntity(convertToEntity(carConfigurationDto.getInductionLevelDto()));
        carConfiguration.setEcuLevelEntity(convertToEntity(carConfigurationDto.getEcuLevelDto()));
        carConfiguration.setInjectionLevelEntity(convertToEntity(carConfigurationDto.getInjectionLevelDto()));
        carConfiguration.setEscapeLevelEntity(convertToEntity(carConfigurationDto.getEscapeLevelDto()));
        carConfiguration.setTurbo(convertToEntity(carConfigurationDto.getTurboDto()));
        carConfiguration.setNitroLevelEntity(convertToEntity(carConfigurationDto.getNitroLevelDto()));

        carConfiguration.setSuspension(convertToEntity(carConfigurationDto.getSuspensionDto()));
        carConfiguration.setBrakeLevelEntity(convertToEntity(carConfigurationDto.getBrakeLevelDto()));
        carConfiguration.setTire(convertToEntity(carConfigurationDto.getTireDto()));

        carConfiguration.setEmbragueLevelEntity(convertToEntity(carConfigurationDto.getEmbragueLevelDto()));
        carConfiguration.setGear(convertToEntity(carConfigurationDto.getGearDto()));
        carConfiguration.setDifferentialLevelEntity(convertToEntity(carConfigurationDto.getDifferentialLevelDto()));

        carConfiguration.setDriver(convertToEntity(carConfigurationDto.getDriverDto()));
        carConfiguration.setAuxiliaryOne(convertToEntity(carConfigurationDto.getAuxiliarOneDto()));
        carConfiguration.setAuxiliaryTwo(convertToEntity(carConfigurationDto.getAuxiliarTwoDto()));
        carConfiguration.setClasses(convertToEntity(carConfigurationDto.getClassesDto()));
        return carConfiguration;
    }

    //Convert Auxiliar to AuxiliarDto
    public AuxiliarDto convertToDto(Auxiliary auxiliary){
        AuxiliarDto auxiliarDto = modelMapper.map(auxiliary, AuxiliarDto.class);
        auxiliarDto.setLevelDto(convertToDto(auxiliary.getLevelEntity()));
        return auxiliarDto;
    }

    //Convert AuxiliarDto to Auxiliar
    public Auxiliary convertToEntity(AuxiliarDto auxiliarDto){
        Auxiliary auxiliary = modelMapper.map(auxiliarDto, Auxiliary.class);
        auxiliary.setLevelEntity(convertToEntity(auxiliarDto.getLevelDto()));
        return auxiliary;
    }
}