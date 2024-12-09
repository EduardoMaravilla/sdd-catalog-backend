package org.eduardomaravill.sdd_catalogo.controllers.cars_controllers;

import org.eduardomaravill.sdd_catalogo.dtos.car_dtos.TurboTypeDto;
import org.eduardomaravill.sdd_catalogo.repositories.users_respositories.IJwtTokenRepository;
import org.eduardomaravill.sdd_catalogo.services.auth.IJwtService;
import org.eduardomaravill.sdd_catalogo.services.cars_services.IStreetTypeService;
import org.eduardomaravill.sdd_catalogo.services.cars_services.ITurboTypeService;
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

@WebMvcTest(TurboTypeController.class)
@AutoConfigureMockMvc(addFilters = false)
class TurboTypeControllerTest {

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
    private ITurboTypeService turboTypeService;

    private TurboTypeDto turboTypeDto;

    @BeforeEach
    void setUp() {
        turboTypeDto = new TurboTypeDto(2L,"SobreAlimentadorCentrifugo");
    }

    @Test
    @DisplayName("Test controller get TurboType")
    void getTurboTypeDto() throws Exception {
        when(turboTypeService.getTurboType(anyLong())).thenReturn(turboTypeDto);

        mockMvc.perform(get("/turbo-types/{id}",2L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(turboTypeDto.getId()))
                .andExpect(jsonPath("$.type").value(turboTypeDto.getType()));

        verify(turboTypeService,times(1)).getTurboType(anyLong());
    }

    @Test
    @DisplayName("Test controller getAllTurboTypes")
    void getAllTurboTypes() throws Exception{
        List<TurboTypeDto> turboTypeDtoList = List.of(turboTypeDto,turboTypeDto);
        when(turboTypeService.getAllTurboTypes()).thenReturn(turboTypeDtoList);

        mockMvc.perform(get("/turbo-types"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[1].id").value(turboTypeDto.getId()))
                .andExpect(jsonPath("$[1].type").value(turboTypeDto.getType()))
                .andExpect(jsonPath("$.size()").value(turboTypeDtoList.size()));

        verify(turboTypeService,times(1)).getAllTurboTypes();
    }

    @Test
    @DisplayName("Test controller create TurboType")
    void createTurboType() throws Exception {
        doNothing().when(turboTypeService).createTurboType(any(TurboTypeDto.class));

        mockMvc.perform(post("/turbo-types")
               .contentType(MediaType.APPLICATION_JSON)
               .content("{\"type\":\"DobleTurboCompresor\"}"))
               .andExpect(status().isCreated());

        verify(turboTypeService,times(1)).createTurboType(any(TurboTypeDto.class));
    }

    @Test
    @DisplayName("Test controller update TurboType")
    void updateTurboType() throws Exception {
        doNothing().when(turboTypeService).updateTurboType(anyLong(), any(TurboTypeDto.class));

        mockMvc.perform(put("/turbo-types/{id}",2L)
               .contentType(MediaType.APPLICATION_JSON)
               .content("{\"type\":\"DobleTurboCompresorCentrifugo\"}"))
               .andExpect(status().isOk());

        verify(turboTypeService, times(1)).updateTurboType(anyLong(), any(TurboTypeDto.class));
    }

    @Test
    @DisplayName("Test controller delete TurboType")
    void deleteTurboType() throws Exception {
        doNothing().when(turboTypeService).deleteTurboType(anyLong());

        mockMvc.perform(delete("/turbo-types/{id}",2L))
               .andExpect(status().isOk());

        verify(turboTypeService, times(1)).deleteTurboType(anyLong());
    }
}