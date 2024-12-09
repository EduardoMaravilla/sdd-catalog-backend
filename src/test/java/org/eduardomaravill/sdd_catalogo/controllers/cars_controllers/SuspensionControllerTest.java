package org.eduardomaravill.sdd_catalogo.controllers.cars_controllers;

import org.eduardomaravill.sdd_catalogo.dtos.car_dtos.LevelDto;
import org.eduardomaravill.sdd_catalogo.dtos.car_dtos.StreetTypeDto;
import org.eduardomaravill.sdd_catalogo.dtos.car_dtos.SuspensionDto;
import org.eduardomaravill.sdd_catalogo.repositories.users_respositories.IJwtTokenRepository;
import org.eduardomaravill.sdd_catalogo.services.auth.IJwtService;
import org.eduardomaravill.sdd_catalogo.services.cars_services.IStreetTypeService;
import org.eduardomaravill.sdd_catalogo.services.cars_services.ISuspensionService;
import org.eduardomaravill.sdd_catalogo.services.users_services.IUserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(SuspensionController.class)
@AutoConfigureMockMvc(addFilters = false)
class SuspensionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IJwtService jwtService;

    @MockBean
    private IStreetTypeService streetTypeService;

    @MockBean
    private IJwtTokenRepository jwtTokenRepository;

    @MockBean
    private IUserService userService;

    @MockBean
    private ISuspensionService suspensionService;

    private SuspensionDto suspensionDto;

    @BeforeEach
    void setUp() {
        suspensionDto =  new SuspensionDto(2L,new StreetTypeDto(3L,"Normal"),new LevelDto(3L,"Pro"));
    }

    @Test
    @DisplayName("Test controller get Suspension")
    void getSuspension() throws Exception {
        when(suspensionService.getSuspension(anyLong())).thenReturn(suspensionDto);

        mockMvc.perform(get("/suspensions/{id}",2L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(2L))
                .andExpect(jsonPath("$.streetTypeDto.streetTypeVal").value("Normal"))
                .andExpect(jsonPath("$.levelDto.level").value("Pro"));

        verify(suspensionService, times(1)).getSuspension(anyLong());
    }

    @Test
    @DisplayName("Test controller getAllSuspension")
    void getAllSuspensions() throws Exception {
        List<SuspensionDto> suspensionDtoList =  List.of(suspensionDto,suspensionDto,suspensionDto);
        when(suspensionService.getAllSuspensions()).thenReturn(suspensionDtoList);

        mockMvc.perform(get("/suspensions"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(2L))
                .andExpect(jsonPath("$[1].streetTypeDto.streetTypeVal").value("Normal"))
                .andExpect(jsonPath("$[2].levelDto.level").value("Pro"))
                .andExpect(jsonPath("$.size()").value(suspensionDtoList.size()));

        verify(suspensionService, times(1)).getAllSuspensions();
    }

    @Test
    @DisplayName("Test controller create Suspension")
    void createSuspension() throws Exception {
        doNothing().when(suspensionService).createSuspension(any(SuspensionDto.class));

        mockMvc.perform(post("/suspensions")
               .contentType(MediaType.APPLICATION_JSON)
               .content("{\"streetTypeDto\":{\"streetTypeVal\":\"Normal\",\"id\":3},\"levelDto\":{\"level\":\"Pro\",\"id\":3}}"))
               .andExpect(status().isCreated());

        verify(suspensionService, times(1)).createSuspension(any(SuspensionDto.class));
    }

    @Test
    @DisplayName("Test controller update Suspension")
    void updateSuspension() throws Exception{
        doNothing().when(suspensionService).updateSuspension(anyLong(), any(SuspensionDto.class));

        mockMvc.perform(put("/suspensions/{id}", 2L)
               .contentType(MediaType.APPLICATION_JSON)
               .content("{\"streetTypeDto\":{\"streetTypeVal\":\"Normal\",\"id\":3},\"levelDto\":{\"level\":\"Pro\",\"id\":3}}"))
               .andExpect(status().isOk());

        verify(suspensionService, times(1)).updateSuspension(anyLong(), any(SuspensionDto.class));
    }

    @Test
    @DisplayName("Test controller delete Suspension")
    void deleteSuspension() throws Exception {
        doNothing().when(suspensionService).deleteSuspension(anyLong());

        mockMvc.perform(delete("/suspensions/{id}", 3L))
               .andExpect(status().isOk());

        verify(suspensionService, times(1)).deleteSuspension(anyLong());
    }
}