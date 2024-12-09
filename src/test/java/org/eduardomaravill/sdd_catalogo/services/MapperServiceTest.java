package org.eduardomaravill.sdd_catalogo.services;


import org.eduardomaravill.sdd_catalogo.dtos.car_dtos.*;
import org.eduardomaravill.sdd_catalogo.models.cars_models.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(properties = {"spring.profiles.active=test"})
class MapperServiceTest {

    @Autowired
    private MapperService mapperService;

    @Test
    @DisplayName("Test Mapper Auxiliar to AuxiliarDto")
    void testMapAuxiliarToAuxiliarDto() {
        Auxiliary auxiliary =  new Auxiliary(2L,"PILOTO A LA FUGA", new LevelEntity(3L,"Pro"));
        Object object = mapperService.convertToDto(auxiliary);
        assertNotNull(object);
        assertInstanceOf(AuxiliarDto.class, object);
        AuxiliarDto auxiliarDto = mapperService.convertToDto(auxiliary);
        assertEquals(auxiliarDto.getId(), auxiliary.getId());
        assertEquals(auxiliarDto.getAuxiliar(), auxiliary.getAuxiliar());
        assertEquals(auxiliarDto.getLevelDto().getLevel(), auxiliary.getLevelEntity().getLevel());
    }

    @Test
    @DisplayName("Test Mapper AuxiliarDto to Auxiliar")
    void testMapAuxiliarDtoToAuxiliar() {
        AuxiliarDto auxiliarDto = new AuxiliarDto(2L,"PILOTO A LA FUGA", new LevelDto(3L,"Pro"));
        Object object = mapperService.convertToEntity(auxiliarDto);
        assertNotNull(object);
        assertInstanceOf(Auxiliary.class, object);
        Auxiliary auxiliary = mapperService.convertToEntity(auxiliarDto);
        assertEquals(auxiliary.getId(), auxiliarDto.getId());
        assertEquals(auxiliary.getAuxiliar(), auxiliarDto.getAuxiliar());
        assertEquals(auxiliary.getLevelEntity().getLevel(), auxiliarDto.getLevelDto().getLevel());
    }

    @Test
    @DisplayName("Test Mapper CarConfiguration to CarConfigurationDto")
    void testMapCarConfigurationToCarConfigurationDto() {
        CarConfiguration carConfiguration = getCarConfiguration();
        Object object = mapperService.convertToDto(carConfiguration);
        assertNotNull(object);
        assertInstanceOf(CarConfigurationDto.class, object);
        CarConfigurationDto carConfigurationDto = mapperService.convertToDto(carConfiguration);
        assertEquals(carConfigurationDto.getId(),carConfiguration.getId());
        assertEquals(carConfigurationDto.getCarDto().getModel(), carConfiguration.getCar().getModel());
        assertEquals(carConfigurationDto.getEngineDto().getModel(),carConfiguration.getEngine().getModel());
        assertEquals(carConfigurationDto.getInductionLevelDto().getLevel(),carConfiguration.getInductionLevelEntity().getLevel());
        assertEquals(carConfigurationDto.getEcuLevelDto().getLevel(),carConfiguration.getEcuLevelEntity().getLevel());
        assertEquals(carConfigurationDto.getInjectionLevelDto().getLevel(),carConfiguration.getInjectionLevelEntity().getLevel());
        assertEquals(carConfigurationDto.getEscapeLevelDto().getLevel(),carConfiguration.getEscapeLevelEntity().getLevel());
        assertEquals(carConfigurationDto.getTurboDto().getTurboTypeDto().getType(), carConfiguration.getTurbo().getTurboType().getType());
        assertEquals(carConfigurationDto.getNitroLevelDto().getLevel(),carConfiguration.getNitroLevelEntity().getLevel());
        assertEquals(carConfigurationDto.getSuspensionDto().getStreetTypeDto().getStreetTypeVal(), carConfiguration.getSuspension().getStreetType().getStreetTypeVal());
        assertEquals(carConfigurationDto.getBrakeLevelDto().getLevel(),carConfiguration.getBrakeLevelEntity().getLevel());
        assertEquals(carConfigurationDto.getTireDto().getStreetTypeDto().getStreetTypeVal(), carConfiguration.getTire().getStreetType().getStreetTypeVal());
        assertEquals(carConfigurationDto.getEmbragueLevelDto().getLevel(), carConfiguration.getEmbragueLevelEntity().getLevel());
        assertEquals(carConfigurationDto.getGearDto().getGearValue(),carConfiguration.getGear().getGearValue());
        assertEquals(carConfigurationDto.getDifferentialLevelDto().getLevel(),carConfiguration.getDifferentialLevelEntity().getLevel());
        assertEquals(carConfigurationDto.getTopSpeed(),carConfiguration.getTopSpeed());
        assertEquals(carConfigurationDto.getOneHundred(),carConfiguration.getOneHundred());
        assertEquals(carConfigurationDto.getFourHundred(),carConfiguration.getFourHundred());
        assertEquals(carConfigurationDto.getPower(),carConfiguration.getPower());
        assertEquals(carConfigurationDto.getPar(),carConfiguration.getPar());
        assertEquals(carConfigurationDto.getDriverDto().getDrive(),carConfiguration.getDriver().getDrive());
        assertEquals(carConfigurationDto.getAuxiliarOneDto().getAuxiliar(),carConfiguration.getAuxiliaryOne().getAuxiliar());
        assertEquals(carConfigurationDto.getAuxiliarTwoDto().getAuxiliar(),carConfiguration.getAuxiliaryTwo().getAuxiliar());

    }

    private static CarConfiguration getCarConfiguration() {
        CarConfiguration carConfiguration = new CarConfiguration();
        carConfiguration.setId(30L);
        carConfiguration.setCar(new Car(20L, new Maker(4L, "Ford"), "Mustang", "1964"));
        carConfiguration.setEngine(new Engine(10L, 1000, 8.5, "V8 híbrido", new LevelEntity(5L, "Elite")));
        carConfiguration.setInductionLevelEntity(new LevelEntity(1L, "Básico"));
        carConfiguration.setEcuLevelEntity(new LevelEntity(2L, "Deportivo"));
        carConfiguration.setInjectionLevelEntity(new LevelEntity(3L, "Pro"));
        carConfiguration.setEscapeLevelEntity(new LevelEntity(4L, "Super"));
        carConfiguration.setTurbo(new Turbo(5L, new TurboType(2L, "DobleTurboCompresor"), new LevelEntity(1L, "Básico")));
        carConfiguration.setNitroLevelEntity(new LevelEntity(5L, "Elite"));
        carConfiguration.setSuspension(new Suspension(6L, new StreetType(2L, "TodoTerreno"), new LevelEntity(2L, "Deportivo")));
        carConfiguration.setBrakeLevelEntity(new LevelEntity(3L, "Pro"));
        carConfiguration.setTire(new Tire(7L, new StreetType(4L, "Derrape"), new LevelEntity(3L, "Pro")));
        carConfiguration.setEmbragueLevelEntity(new LevelEntity(1L, "Básico"));
        carConfiguration.setGear(new Gear(3L, 3, new LevelEntity(3L, "Deportivo")));
        carConfiguration.setDifferentialLevelEntity(new LevelEntity(2L, "Deportivo"));
        carConfiguration.setTopSpeed(350);
        carConfiguration.setOneHundred(3.5);
        carConfiguration.setFourHundred(10.5);
        carConfiguration.setPower(1500);
        carConfiguration.setPar(2000);
        carConfiguration.setDriver(new Driver(2L, 100, 5, 5, true, new InitSkid(4L, "POR DEFECTO")));
        carConfiguration.setAuxiliaryOne(new Auxiliary(2L,"PROTECCIÓN DE IMPACTOS", new LevelEntity(2L,"PRO")));
        carConfiguration.setAuxiliaryTwo(new Auxiliary(2L,"PROTECCIÓN DE IMPACTOS", new LevelEntity(2L,"PRO")));
        carConfiguration.setClasses(new Classes(1L,"B"));
        return carConfiguration;
    }

    @Test
    @DisplayName("Test Mapper CarConfigurationDto to CarConfiguration")
    void testMapCarConfigurationDtoToCarConfiguration() {
        CarConfigurationDto carConfigurationDto = getCarConfigurationDto();
        Object object = mapperService.convertToEntity(carConfigurationDto);
        assertNotNull(object);
        assertInstanceOf(CarConfiguration.class, object);
        CarConfiguration carConfiguration = mapperService.convertToEntity(carConfigurationDto);
        assertEquals(carConfiguration.getId(), carConfigurationDto.getId());
        assertEquals(carConfiguration.getCar().getId(), carConfigurationDto.getCarDto().getId());
        assertEquals(carConfiguration.getEngine().getId(), carConfigurationDto.getEngineDto().getId());
        assertEquals(carConfiguration.getInductionLevelEntity().getLevel(), carConfigurationDto.getInductionLevelDto().getLevel());
        assertEquals(carConfiguration.getEcuLevelEntity().getLevel(),carConfigurationDto.getEcuLevelDto().getLevel());
        assertEquals(carConfiguration.getInjectionLevelEntity().getLevel(), carConfigurationDto.getInjectionLevelDto().getLevel());
        assertEquals(carConfiguration.getEscapeLevelEntity().getLevel(), carConfigurationDto.getEscapeLevelDto().getLevel());
        assertEquals(carConfiguration.getTurbo().getTurboType().getId(), carConfigurationDto.getTurboDto().getTurboTypeDto().getId());
        assertEquals(carConfiguration.getNitroLevelEntity().getLevel(), carConfigurationDto.getNitroLevelDto().getLevel());
        assertEquals(carConfiguration.getSuspension().getStreetType().getId(), carConfigurationDto.getSuspensionDto().getStreetTypeDto().getId());
        assertEquals(carConfiguration.getBrakeLevelEntity().getLevel(), carConfigurationDto.getBrakeLevelDto().getLevel());
        assertEquals(carConfiguration.getTire().getStreetType().getId(), carConfigurationDto.getTireDto().getStreetTypeDto().getId());
        assertEquals(carConfiguration.getEmbragueLevelEntity().getLevel(), carConfigurationDto.getEmbragueLevelDto().getLevel());
        assertEquals(carConfiguration.getGear().getId(), carConfigurationDto.getGearDto().getId());
        assertEquals(carConfiguration.getDifferentialLevelEntity().getLevel(), carConfigurationDto.getDifferentialLevelDto().getLevel());
        assertEquals(carConfiguration.getTopSpeed(), carConfigurationDto.getTopSpeed());
        assertEquals(carConfiguration.getOneHundred(), carConfigurationDto.getOneHundred());
        assertEquals(carConfiguration.getFourHundred(), carConfigurationDto.getFourHundred());
        assertEquals(carConfiguration.getPower(), carConfigurationDto.getPower());
        assertEquals(carConfiguration.getPar(), carConfigurationDto.getPar());
        assertEquals(carConfiguration.getDriver().getId(), carConfigurationDto.getDriverDto().getId());
        assertEquals(carConfiguration.getDriver().getInitSkid().getId(), carConfigurationDto.getDriverDto().getInitSkidDto().getId());
        assertEquals(carConfiguration.getAuxiliaryOne().getId(), carConfigurationDto.getAuxiliarOneDto().getId());
    }

    private static CarConfigurationDto getCarConfigurationDto() {
        CarConfigurationDto carConfigurationDto = new CarConfigurationDto();
        carConfigurationDto.setId(30L);
        carConfigurationDto.setCarDto(new CarDto(20L, new MakerDto(4L, "Ford"), "Mustang", "1964"));
        carConfigurationDto.setEngineDto(new EngineDto(10L, 1000, 8.5, "V8 híbrido", new LevelDto(5L, "Elite")));
        carConfigurationDto.setInductionLevelDto(new LevelDto(1L, "Básico"));
        carConfigurationDto.setEcuLevelDto(new LevelDto(2L, "Deportivo"));
        carConfigurationDto.setInjectionLevelDto(new LevelDto(3L, "Pro"));
        carConfigurationDto.setEscapeLevelDto(new LevelDto(4L, "Super"));
        carConfigurationDto.setTurboDto(new TurboDto(5L, new TurboTypeDto(2L, "DobleTurboCompresor"), new LevelDto(1L, "Básico")));
        carConfigurationDto.setNitroLevelDto(new LevelDto(5L, "Elite"));
        carConfigurationDto.setSuspensionDto(new SuspensionDto(6L, new StreetTypeDto(2L, "TodoTerreno"), new LevelDto(2L, "Deportivo")));
        carConfigurationDto.setBrakeLevelDto(new LevelDto(3L, "Pro"));
        carConfigurationDto.setTireDto(new TireDto(7L, new StreetTypeDto(4L, "Derrape"), new LevelDto(3L, "Pro")));
        carConfigurationDto.setEmbragueLevelDto(new LevelDto(1L, "Básico"));
        carConfigurationDto.setGearDto(new GearDto(3L, 3, new LevelDto(3L, "Deportivo")));
        carConfigurationDto.setDifferentialLevelDto(new LevelDto(2L, "Deportivo"));
        carConfigurationDto.setTopSpeed(350);
        carConfigurationDto.setOneHundred(3.5);
        carConfigurationDto.setFourHundred(10.5);
        carConfigurationDto.setPower(1500);
        carConfigurationDto.setPar(2000);
        carConfigurationDto.setDriverDto(new DriverDto(2L, 100, 5, 5, true, new InitSkidDto(4L, "POR DEFECTO")));
        carConfigurationDto.setAuxiliarOneDto(new AuxiliarDto(3L, "NITRO POR SALTOS", new LevelDto(2L,"Pro")));
        carConfigurationDto.setAuxiliarTwoDto(new AuxiliarDto(4L, "AUMENTO DE DAÑOS", new LevelDto(1L,"Básico")));
        carConfigurationDto.setClassesDto(new ClassesDto(1L,"B"));
        return carConfigurationDto;
    }

    @Test
    @DisplayName("Test Mapper Car to CarDto")
    void testMapCarToCarDto() {
        Car car = new Car(20L, new Maker(4L, "Ford"), "Mustang", "1964");
        Object object = mapperService.convertToDto(car);
        assertNotNull(object);
        assertInstanceOf(CarDto.class, object);
        CarDto carDto = mapperService.convertToDto(car);
        assertEquals(carDto.getId(), car.getId());
        assertEquals(carDto.getMakerDto().getName(), car.getMaker().getName());
        assertEquals(carDto.getModel(), car.getModel());
        assertEquals(carDto.getYear(), car.getCarYear());
    }

    @Test
    @DisplayName("Test Mapper CarDto to Car")
    void testMapCarDtoToCar() {
        CarDto carDto = new CarDto(20L, new MakerDto(4L, "Ford"), "Mustang", "1964");
        Object object = mapperService.convertToEntity(carDto);
        assertNotNull(object);
        assertInstanceOf(Car.class, object);
        Car car = mapperService.convertToEntity(carDto);
        assertEquals(car.getId(), carDto.getId());
        assertEquals(car.getMaker().getName(), carDto.getMakerDto().getName());
        assertEquals(car.getModel(), carDto.getModel());
        assertEquals(car.getCarYear(), carDto.getYear());
    }

    @Test
    @DisplayName("Test Mapper Classes to ClasesDto")
    void testMapClassesToClassesDto() {
        Classes classes = new Classes(2L,"S+");
        Object object = mapperService.convertToDto(classes);
        assertNotNull(object);
        assertInstanceOf(ClassesDto.class, object);
        ClassesDto classesDto = mapperService.convertToDto(classes);
        assertEquals(classesDto.getId(), classes.getId());
        assertEquals(classesDto.getName(), classes.getName());
    }

    @Test
    @DisplayName("Test Mapper ClassesDto to Classes")
    void testMapClassesDtoToClasses() {
        ClassesDto classesDto = new ClassesDto(2L,"S+");
        Object object = mapperService.convertToEntity(classesDto);
        assertNotNull(object);
        assertInstanceOf(Classes.class, object);
        Classes classes = mapperService.convertToEntity(classesDto);
        assertEquals(classes.getId(), classesDto.getId());
        assertEquals(classes.getName(), classesDto.getName());
    }

    @Test
    @DisplayName("Test Mapper Driver to DriverDto")
    void testMapDriverToDriverDto() {
        Driver driver = new Driver(5L,100,4,8,false,new InitSkid(3L,"NO"));
        Object object = mapperService.convertToDto(driver);
        assertNotNull(object);
        assertInstanceOf(DriverDto.class, object);
        DriverDto driverDto = mapperService.convertToDto(driver);
        assertEquals(driverDto.getId(), driver.getId());
        assertEquals(driverDto.getDrive(),driver.getDrive());
        assertEquals(driverDto.getDirection(),driver.getDirection());
        assertEquals(driverDto.getDownForce(),driver.getDownForce());
        assertEquals(driverDto.getControlTraction(),driver.getControlTraction());
        assertEquals(driverDto.getInitSkidDto().getSkidType(),driver.getInitSkid().getSkidType());
    }

    @Test
    @DisplayName("Test Mapper DriverDto to Driver")
    void testMapDriverDtoToDriver() {
        DriverDto driverDto = new DriverDto(5L,100,4,8,false,new InitSkidDto(3L,"NO"));
        Object object = mapperService.convertToEntity(driverDto);
        assertNotNull(object);
        assertInstanceOf(Driver.class, object);
        Driver driver = mapperService.convertToEntity(driverDto);
        assertEquals(driver.getId(), driverDto.getId());
        assertEquals(driver.getDrive(),driverDto.getDrive());
        assertEquals(driver.getDirection(),driverDto.getDirection());
        assertEquals(driver.getDownForce(),driverDto.getDownForce());
        assertEquals(driver.getControlTraction(),driverDto.getControlTraction());
        assertEquals(driver.getInitSkid().getSkidType(),driverDto.getInitSkidDto().getSkidType());
    }

    @Test
    @DisplayName("Test Mapper Engine to EngineDto")
    void testMapEngineToEngineDto() {
        Engine engine =  new Engine(5L,1500,5.0,"V8",new LevelEntity(2L,"Deportivo"));
        Object object = mapperService.convertToDto(engine);
        assertNotNull(object);
        assertInstanceOf(EngineDto.class, object);
        EngineDto engineDto = mapperService.convertToDto(engine);
        assertEquals(engineDto.getId(), engine.getId());
        assertEquals(engineDto.getBhp(),engine.getBhp());
        assertEquals(engineDto.getLiters(),engine.getLiters());
        assertEquals(engineDto.getModel(),engine.getModel());
        assertEquals(engineDto.getLevelDto().getLevel(),engine.getLevelEntity().getLevel());
    }

    @Test
    @DisplayName("Test Mapper EngineDto to Engine")
    void testMapEngineDtoToEngine() {
        EngineDto engineDto = new EngineDto(5L,1500,5.0,"V8",new LevelDto(2L,"Deportivo"));
        Object object = mapperService.convertToEntity(engineDto);
        assertNotNull(object);
        assertInstanceOf(Engine.class, object);
        Engine engine = mapperService.convertToEntity(engineDto);
        assertEquals(engine.getId(), engineDto.getId());
        assertEquals(engine.getBhp(),engineDto.getBhp());
        assertEquals(engine.getLiters(),engineDto.getLiters());
        assertEquals(engine.getModel(),engineDto.getModel());
        assertEquals(engine.getLevelEntity().getLevel(),engineDto.getLevelDto().getLevel());
    }

    @Test
    @DisplayName("Test Mapper Gear to GearDto")
    void testMapGearToGearDto() {
        Gear gear = new Gear(5L,6,new LevelEntity(3L,"Pro"));
        Object object = mapperService.convertToDto(gear);
        assertNotNull(object);
        assertInstanceOf(GearDto.class, object);
        GearDto gearDto = mapperService.convertToDto(gear);
        assertEquals(gearDto.getId(), gear.getId());
        assertEquals(gearDto.getGearValue(),gear.getGearValue());
        assertEquals(gearDto.getLevelDto().getLevel(), gear.getLevelEntity().getLevel());
    }

    @Test
    @DisplayName("Test Mapper GearDto to Gear")
    void testMapGearDtoToGear() {
        GearDto gearDto = new GearDto(5L,6,new LevelDto(3L,"Pro"));
        Object object = mapperService.convertToEntity(gearDto);
        assertNotNull(object);
        assertInstanceOf(Gear.class, object);
        Gear gear = mapperService.convertToEntity(gearDto);
        assertEquals(gear.getId(), gearDto.getId());
        assertEquals(gear.getGearValue(), gearDto.getGearValue());
        assertEquals(gear.getLevelEntity().getLevel(), gearDto.getLevelDto().getLevel());
    }

    @Test
    @DisplayName("Test Mapper InitSkid to InitSkidDto")
    void testMapInitSkidToInitSkidDto() {
        InitSkid initSkid = new InitSkid(5L,"NO");
        Object object = mapperService.convertToDto(initSkid);
        assertNotNull(object);
        assertInstanceOf(InitSkidDto.class, object);
        InitSkidDto initSkidDto = mapperService.convertToDto(initSkid);
        assertEquals(initSkidDto.getId(), initSkid.getId());
        assertEquals(initSkidDto.getSkidType(), initSkid.getSkidType());
    }

    @Test
    @DisplayName("Test Mapper InitSkidDto to InitSkid")
    void testMapInitSkidDtoToInitSkid() {
        InitSkidDto initSkidDto = new InitSkidDto(5L,"NO");
        Object object = mapperService.convertToEntity(initSkidDto);
        assertNotNull(object);
        assertInstanceOf(InitSkid.class, object);
        InitSkid initSkid = mapperService.convertToEntity(initSkidDto);
        assertEquals(initSkid.getId(), initSkidDto.getId());
        assertEquals(initSkid.getSkidType(), initSkidDto.getSkidType());
    }

    @Test
    @DisplayName("Test Mapper Level to LevelDto")
    void testMapLevelToLevelDto() {
        LevelEntity levelEntity = new LevelEntity(5L,"Pro");
        Object object = mapperService.convertToDto(levelEntity);
        assertNotNull(object);
        assertInstanceOf(LevelDto.class, object);
        LevelDto levelDto = mapperService.convertToDto(levelEntity);
        assertEquals(levelDto.getId(), levelEntity.getId());
        assertEquals(levelDto.getLevel(), levelEntity.getLevel());
    }

    @Test
    @DisplayName("Test Mapper LevelDto to Level")
    void testMapLevelDtoToLevel() {
        LevelDto levelDto = new LevelDto(5L,"Pro");
        Object object = mapperService.convertToEntity(levelDto);
        assertNotNull(object);
        assertInstanceOf(LevelEntity.class, object);
        LevelEntity levelEntity = mapperService.convertToEntity(levelDto);
        assertEquals(levelEntity.getId(), levelDto.getId());
        assertEquals(levelEntity.getLevel(), levelDto.getLevel());
    }

    @Test
    @DisplayName("Test Mapper Maker to MakerDto")
    void testMapMakerToMakerDto() {
        Maker maker = new Maker(6L,"Audi");
        Object object=mapperService.convertToDto(maker);
        assertNotNull(object);
        assertInstanceOf(MakerDto.class,object);
        MakerDto makerDto = mapperService.convertToDto(maker);
        assertEquals(makerDto.getId(), maker.getId());
        assertEquals(makerDto.getName(), maker.getName());
    }

    @Test
    @DisplayName("Test Mapper MakerDto to Maker")
    void testMapMakerDtoToMaker() {
        MakerDto makerDto = new MakerDto(6L,"Audi");
        Object object = mapperService.convertToEntity(makerDto);
        assertNotNull(object);
        assertInstanceOf(Maker.class, object);
        Maker maker = mapperService.convertToEntity(makerDto);
        assertEquals(maker.getId(), makerDto.getId());
        assertEquals(maker.getName(), makerDto.getName());
    }

    @Test
    @DisplayName("Test Mapper StreetType to StreetTypeDto")
    void testMapStreetTypeToStreetTypeDto() {
        StreetType streetType = new StreetType(7L,"Carrera");
        Object object = mapperService.convertToDto(streetType);
        assertNotNull(object);
        assertInstanceOf(StreetTypeDto.class, object);
        StreetTypeDto streetTypeDto = mapperService.convertToDto(streetType);
        assertEquals(streetTypeDto.getId(), streetType.getId());
        assertEquals(streetTypeDto.getStreetTypeVal(), streetType.getStreetTypeVal());
    }

    @Test
    @DisplayName("Test Mapper StreetTypeDto to StreetType")
    void testMapStreetTypeDtoToStreetType() {
        StreetTypeDto streetTypeDto = new StreetTypeDto(7L,"Carrera");
        Object object = mapperService.convertToEntity(streetTypeDto);
        assertNotNull(object);
        assertInstanceOf(StreetType.class, object);
        StreetType streetType = mapperService.convertToEntity(streetTypeDto);
        assertEquals(streetType.getId(), streetTypeDto.getId());
        assertEquals(streetType.getStreetTypeVal(), streetTypeDto.getStreetTypeVal());
    }

    @Test
    @DisplayName("Test Mapper Suspension to SuspensionDto")
    void testMapSuspensionToSuspensionDto() {
        Suspension suspension = new Suspension(8L,new StreetType(3L,"Normal"),new LevelEntity(2L,"Deportivo"));
        Object object = mapperService.convertToDto(suspension);
        assertNotNull(object);
        assertInstanceOf(SuspensionDto.class, object);
        SuspensionDto suspensionDto = mapperService.convertToDto(suspension);
        assertEquals(suspensionDto.getId(), suspension.getId());
        assertEquals(suspensionDto.getStreetTypeDto().getStreetTypeVal(), suspension.getStreetType().getStreetTypeVal());
        assertEquals(suspensionDto.getLevelDto().getLevel(), suspension.getLevelEntity().getLevel());
    }

    @Test
    @DisplayName("Test Mapper SuspensionDto to Suspension")
    void testMapSuspensionDtoToSuspension() {
        SuspensionDto suspensionDto = new SuspensionDto(8L,new StreetTypeDto(3L,"Normal"),new LevelDto(2L,"Deportivo"));
        Object object = mapperService.convertToEntity(suspensionDto);
        assertNotNull(object);
        assertInstanceOf(Suspension.class, object);
        Suspension suspension = mapperService.convertToEntity(suspensionDto);
        assertEquals(suspension.getId(), suspensionDto.getId());
        assertEquals(suspension.getStreetType().getStreetTypeVal(), suspensionDto.getStreetTypeDto().getStreetTypeVal());
        assertEquals(suspension.getLevelEntity().getLevel(), suspensionDto.getLevelDto().getLevel());
    }

    @Test
    @DisplayName("Test Mapper Tire to TireDto")
    void testMapTireToTireDto() {
        Tire tire = new Tire(9L,new StreetType(4L,"Derrape"),new LevelEntity(1L,"Elite"));
        Object object = mapperService.convertToDto(tire);
        assertNotNull(object);
        assertInstanceOf(TireDto.class, object);
        TireDto tireDto = mapperService.convertToDto(tire);
        assertEquals(tireDto.getId(), tire.getId());
        assertEquals(tireDto.getStreetTypeDto().getStreetTypeVal(),tire.getStreetType().getStreetTypeVal());
        assertEquals(tireDto.getLevelDto().getLevel(), tire.getLevelEntity().getLevel());
    }

    @Test
    @DisplayName("Test Mapper TireDto to Tire")
    void testMapTireDtoToTire() {
        TireDto tireDto = new TireDto(9L,new StreetTypeDto(4L,"Derrape"),new LevelDto(1L,"Elite"));
        Object object = mapperService.convertToEntity(tireDto);
        assertNotNull(object);
        assertInstanceOf(Tire.class, object);
        Tire tire = mapperService.convertToEntity(tireDto);
        assertEquals(tire.getId(), tireDto.getId());
        assertEquals(tire.getStreetType().getStreetTypeVal(), tireDto.getStreetTypeDto().getStreetTypeVal());
        assertEquals(tire.getLevelEntity().getLevel(), tireDto.getLevelDto().getLevel());
    }

    @Test
    @DisplayName("Test Mapper Turbo to TurboDto")
    void testMapTurboToTurboDto() {
        Turbo turbo =new Turbo(6L,new TurboType(1L,"Aspiración Natural"),new LevelEntity(5L,"Elite"));
        Object object = mapperService.convertToDto(turbo);
        assertNotNull(object);
        assertInstanceOf(TurboDto.class, object);
        TurboDto turboDto = mapperService.convertToDto(turbo);
        assertEquals(turboDto.getId(), turbo.getId());
        assertEquals(turboDto.getTurboTypeDto().getType(), turbo.getTurboType().getType());
        assertEquals(turboDto.getLevelDto().getLevel(), turbo.getLevelEntity().getLevel());
    }

    @Test
    @DisplayName("Test Mapper TurboDto to Turbo")
    void testMapTurboDtoToTurbo() {
        TurboDto turboDto = new TurboDto(6L,new TurboTypeDto(1L,"Aspiración Natural"),new LevelDto(5L,"Elite"));
        Object object = mapperService.convertToEntity(turboDto);
        assertNotNull(object);
        assertInstanceOf(Turbo.class, object);
        Turbo turbo = mapperService.convertToEntity(turboDto);
        assertEquals(turbo.getId(), turboDto.getId());
        assertEquals(turbo.getTurboType().getType(), turboDto.getTurboTypeDto().getType());
        assertEquals(turbo.getLevelEntity().getLevel(), turboDto.getLevelDto().getLevel());
    }

    @Test
    @DisplayName("Test Mapper TurboType to TurboTypeDto")
    void testMapTurboTypeToTurboTypeDto() {
        TurboType turboType = new TurboType(2L,"DobleTurboCompresor");
        Object object = mapperService.convertToDto(turboType);
        assertNotNull(object);
        assertInstanceOf(TurboTypeDto.class, object);
        TurboTypeDto turboTypeDto = mapperService.convertToDto(turboType);
        assertEquals(turboTypeDto.getId(), turboType.getId());
        assertEquals(turboTypeDto.getType(), turboType.getType());
    }

    @Test
    @DisplayName("Test Mapper TurboTypeDto to TurboType")
    void testMapTurboTypeDtoToTurboType() {
        TurboTypeDto turboTypeDto = new TurboTypeDto(2L,"DobleTurboCompresor");
        Object object = mapperService.convertToEntity(turboTypeDto);
        assertNotNull(object);
        assertInstanceOf(TurboType.class, object);
        TurboType turboType = mapperService.convertToEntity(turboTypeDto);
        assertEquals(turboType.getId(), turboTypeDto.getId());
        assertEquals(turboType.getType(), turboTypeDto.getType());
    }
}