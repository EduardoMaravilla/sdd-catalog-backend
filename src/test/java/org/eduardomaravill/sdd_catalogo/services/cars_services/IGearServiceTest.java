package org.eduardomaravill.sdd_catalogo.services.cars_services;

import org.eduardomaravill.sdd_catalogo.dtos.car_dtos.GearDto;
import org.eduardomaravill.sdd_catalogo.dtos.car_dtos.LevelDto;
import org.eduardomaravill.sdd_catalogo.models.cars_models.Gear;
import org.eduardomaravill.sdd_catalogo.models.cars_models.LevelEntity;
import org.eduardomaravill.sdd_catalogo.repositories.cars_repositories.IGearRepository;
import org.eduardomaravill.sdd_catalogo.services.MapperService;
import org.eduardomaravill.sdd_catalogo.services.cars_services.implementation.GearServiceImpl;
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
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class IGearServiceTest {

    @Mock
    private IGearRepository gearRepository;

    @Mock
    private ILevelService levelService;

    @Mock
    private MapperService mapperService;

    @InjectMocks
    private GearServiceImpl gearService;

    private Gear gear;
    private GearDto gearDto;

    @BeforeEach
    void setUp() {
        gear = new Gear(1L, 5,new LevelEntity(2L,"Deportivo"));
        gearDto = new GearDto(1L, 5,new LevelDto(2L,"Deportivo"));
    }

    @Test
    @DisplayName("Test service get Gear")
    void getGear() {
        when(gearRepository.findByIdSpecific(anyLong())).thenReturn(Optional.of(gear));
        when(mapperService.convertToDto(any(Gear.class))).thenReturn(gearDto);

        GearDto result = gearService.getGear(1L);

        assertNotNull(result);

        verify(gearRepository).findByIdSpecific(1L);
        verify(mapperService).convertToDto(gear);
    }

    @Test
    @DisplayName("Test service create Gear")
    void createGear() {
        when(levelService.getLevel(anyLong())).thenReturn(new LevelDto(2L,"Deportivo"));
        when(gearRepository.existsByGear(anyInt())).thenReturn(false);
        when(mapperService.convertToEntity(any(GearDto.class))).thenReturn(gear);

        gearService.createGear(gearDto);

        verify(gearRepository).save(gear);
        verify(levelService).getLevel(2L);
        verify(gearRepository).existsByGear(5);
        verify(mapperService).convertToEntity(gearDto);
    }

    @Test
    @DisplayName("Test service update Gear")
    void updateGear() {
        when(levelService.getLevel(anyLong())).thenReturn(new LevelDto(2L,"Deportivo"));
        when(gearRepository.existsById(anyLong())).thenReturn(true);
        when(mapperService.convertToEntity(any(GearDto.class))).thenReturn(gear);

        gearService.updateGear(1L, gearDto);

        verify(gearRepository).save(gear);
        verify(levelService).getLevel(2L);
        verify(gearRepository).existsById(1L);
        verify(mapperService).convertToEntity(gearDto);
    }

    @Test
    @DisplayName("Test service delete Gear")
    void deleteGear() {
        when(gearRepository.existsById(anyLong())).thenReturn(true);

        gearService.deleteGear(1L);

        verify(gearRepository).existsById(1L);
        verify(gearRepository).deleteById(1L);
    }

    @Test
    @DisplayName("Test service getAllGears")
    void getAllGears() {
        when(gearRepository.findAll()).thenReturn(List.of(gear));
        when(mapperService.convertToDto(any(Gear.class))).thenReturn(gearDto);

        List<GearDto> result = gearService.getAllGears();

        assertNotNull(result);

        verify(gearRepository).findAll();
        verify(mapperService).convertToDto(gear);
    }
}