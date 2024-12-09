package org.eduardomaravill.sdd_catalogo.services.cars_services;

import org.eduardomaravill.sdd_catalogo.dtos.car_dtos.MakerDto;
import org.eduardomaravill.sdd_catalogo.models.cars_models.Maker;
import org.eduardomaravill.sdd_catalogo.repositories.cars_repositories.IMakersRepository;
import org.eduardomaravill.sdd_catalogo.services.MapperService;
import org.eduardomaravill.sdd_catalogo.services.cars_services.implementation.MakerServiceImpl;
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
class IMakerServiceTest {

    @Mock
    private IMakersRepository makersRepository;

    @Mock
    private MapperService mapperService;

    @InjectMocks
    private MakerServiceImpl makerService;

    private Maker maker;
    private MakerDto makerDto;

    @BeforeEach
    void setUp() {
        maker = new Maker(1L, "Fiat");
        makerDto = new MakerDto(1L, "Fiat");
    }

    @Test
    @DisplayName("Test service get Maker")
    void getMaker() {
        when(makersRepository.findById(anyLong())).thenReturn(Optional.of(maker));
        when(mapperService.convertToDto(any(Maker.class))).thenReturn(makerDto);

        MakerDto result = makerService.getMaker(1L);

        assertNotNull(result);
        verify(makersRepository).findById(1L);
        verify(mapperService).convertToDto(maker);
    }

    @Test
    @DisplayName("Test service create Maker")
    void createMaker() {
        when(makersRepository.existsByName(anyString())).thenReturn(false);
        when(makersRepository.save(any(Maker.class))).thenReturn(maker);
        when(mapperService.convertToEntity(any(MakerDto.class))).thenReturn(maker);

        makerService.createMaker(makerDto);

        verify(makersRepository).existsByName(makerDto.getName());
        verify(makersRepository).save(any(Maker.class));
        verify(mapperService).convertToEntity(makerDto);
    }

    @Test
    @DisplayName("Test service update Maker")
    void updateMaker() {
        when(makersRepository.existsById(anyLong())).thenReturn(true);
        when(makersRepository.save(any(Maker.class))).thenReturn(maker);
        when(mapperService.convertToEntity(any(MakerDto.class))).thenReturn(maker);

        makerService.updateMaker(1L, makerDto);

        verify(makersRepository).existsById(1L);
        verify(makersRepository).save(any(Maker.class));
        verify(mapperService).convertToEntity(makerDto);
    }

    @Test
    @DisplayName("Test service delete Maker")
    void deleteMaker() {
        when(makersRepository.existsById(anyLong())).thenReturn(true);

        makerService.deleteMaker(1L);

        verify(makersRepository).existsById(1L);
        verify(makersRepository).deleteById(1L);
    }

    @Test
    @DisplayName("Test service getAllMakers")
    void getAllMakers() {
        when(makersRepository.findAll()).thenReturn(List.of(maker));
        when(mapperService.convertToDto(any(Maker.class))).thenReturn(makerDto);

        List<MakerDto> result = makerService.getAllMakers();

        assertNotNull(result);
        verify(makersRepository).findAll();
        verify(mapperService).convertToDto(maker);
    }
}