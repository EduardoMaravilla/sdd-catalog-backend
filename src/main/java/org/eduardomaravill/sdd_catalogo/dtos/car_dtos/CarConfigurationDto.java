package org.eduardomaravill.sdd_catalogo.dtos.car_dtos;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarConfigurationDto {
    private Long id;

    @NotNull
    private CarDto carDto;

    @NotNull
    private EngineDto engineDto;

    @NotNull
    private LevelDto inductionLevelDto;

    @NotNull
    private LevelDto ecuLevelDto;

    @NotNull
    private LevelDto injectionLevelDto;

    @NotNull
    private LevelDto escapeLevelDto;

    @NotNull
    private TurboDto turboDto;

    @NotNull
    private LevelDto nitroLevelDto;

    @NotNull
    private SuspensionDto suspensionDto;

    @NotNull
    private LevelDto brakeLevelDto;

    @NotNull
    private TireDto tireDto;

    @NotNull
    private LevelDto embragueLevelDto;

    @NotNull
    private GearDto gearDto;

    @NotNull
    private LevelDto differentialLevelDto;

    @Max(450)
    @NotNull
    private Integer topSpeed;

    @Max(10)
    @NotNull
    private Double oneHundred;

    @Max(3000)
    @NotNull
    private Integer power;

    @Max(5000)
    @NotNull
    private Integer par;

    @Max(30)
    @NotNull
    private Double fourHundred;

    @NotNull
    private DriverDto driverDto;

    @NotNull
    private AuxiliarDto auxiliarOneDto;

    @NotNull
    private AuxiliarDto auxiliarTwoDto;

    @NotNull
    private ClassesDto classesDto;
}
