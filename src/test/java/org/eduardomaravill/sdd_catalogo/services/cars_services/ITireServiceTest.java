package org.eduardomaravill.sdd_catalogo.services.cars_services;

import org.eduardomaravill.sdd_catalogo.dtos.car_dtos.LevelDto;
import org.eduardomaravill.sdd_catalogo.dtos.car_dtos.StreetTypeDto;
import org.eduardomaravill.sdd_catalogo.dtos.car_dtos.TireDto;
import org.eduardomaravill.sdd_catalogo.models.cars_models.LevelEntity;
import org.eduardomaravill.sdd_catalogo.models.cars_models.StreetType;
import org.eduardomaravill.sdd_catalogo.models.cars_models.Tire;
import org.eduardomaravill.sdd_catalogo.repositories.cars_repositories.ITireRepository;
import org.eduardomaravill.sdd_catalogo.services.MapperService;
import org.eduardomaravill.sdd_catalogo.services.cars_services.implementation.TireServiceImpl;
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
class ITireServiceTest {

    @Mock
    private ITireRepository tireRepository;

    @Mock
    private IStreetTypeService streetTypeService;

    @Mock
    private ILevelService levelService;

    @Mock
    private MapperService mapperService;

    @InjectMocks
    private TireServiceImpl tireService;

    private Tire tire;
    private TireDto tireDto;

    @BeforeEach
    void setUp() {
        tire = new Tire(4L,new StreetType(2L,"Normal"),new LevelEntity(3L,"Pro"));
        tireDto = new TireDto(4L,new StreetTypeDto(2L,"Normal"),new LevelDto(3L,"Pro"));
    }

    @Test
    @DisplayName("Test service get Tire")
    void getTire() {
        when(tireRepository.findByIdSpecific(anyLong())).thenReturn(Optional.of(tire));
        when(mapperService.convertToDto(any(Tire.class))).thenReturn(tireDto);

        TireDto result = tireService.getTire(4L);

        assertNotNull(result);
        verify(tireRepository).findByIdSpecific(4L);
        verify(mapperService).convertToDto(tire);
    }

    @Test
    @DisplayName("Test service create Tire")
    void createTire() {
        when(levelService.getLevel(anyLong())).thenReturn(new LevelDto(3L,"Pro"));
        when(streetTypeService.getStreetType(anyLong())).thenReturn(new StreetTypeDto(2L,"Normal"));
        when(tireRepository.existsTire(anyLong(),anyLong())).thenReturn(false);
        when(mapperService.convertToEntity(any(TireDto.class))).thenReturn(tire);

        tireService.createTire(tireDto);

        verify(levelService).getLevel(3L);
        verify(streetTypeService).getStreetType(2L);
        verify(tireRepository).existsTire(anyLong(),anyLong());
        verify(mapperService).convertToEntity(tireDto);
    }

    @Test
    @DisplayName("Test service update Tire")
    void updateTire() {
        when(levelService.getLevel(anyLong())).thenReturn(new LevelDto(3L,"Pro"));
        when(streetTypeService.getStreetType(anyLong())).thenReturn(new StreetTypeDto(2L,"Normal"));
        when(tireRepository.existsById(anyLong())).thenReturn(true);
        when(mapperService.convertToEntity(any(TireDto.class))).thenReturn(tire);

        tireService.updateTire(4L, tireDto);

        verify(levelService).getLevel(3L);
        verify(streetTypeService).getStreetType(2L);
        verify(tireRepository).existsById(4L);
        verify(mapperService).convertToEntity(tireDto);
    }

    @Test
    @DisplayName("Test service delete Tire")
    void deleteTire() {
        when(tireRepository.existsById(anyLong())).thenReturn(true);

        tireService.deleteTire(4L);

        verify(tireRepository).existsById(4L);
        verify(tireRepository).deleteById(4L);
    }

    @Test
    @DisplayName("Test service getAllTires")
    void getAllTires() {
        when(tireRepository.findAll()).thenReturn(List.of(tire));
        when(mapperService.convertToDto(any(Tire.class))).thenReturn(tireDto);

        List<TireDto> result = tireService.getAllTires();

        assertNotNull(result);
        verify(tireRepository).findAll();
        verify(mapperService).convertToDto(tire);
    }
}