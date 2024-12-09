package org.eduardomaravill.sdd_catalogo.controllers.cars_controllers;

import org.eduardomaravill.sdd_catalogo.dtos.car_dtos.LevelDto;
import org.eduardomaravill.sdd_catalogo.dtos.car_dtos.TurboDto;
import org.eduardomaravill.sdd_catalogo.dtos.car_dtos.TurboTypeDto;
import org.eduardomaravill.sdd_catalogo.repositories.users_respositories.IJwtTokenRepository;
import org.eduardomaravill.sdd_catalogo.services.auth.IJwtService;
import org.eduardomaravill.sdd_catalogo.services.cars_services.IStreetTypeService;
import org.eduardomaravill.sdd_catalogo.services.cars_services.ITurboService;
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

@WebMvcTest(TurboController.class)
@AutoConfigureMockMvc(addFilters = false)
class TurboControllerTest {

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
    private ITurboService turboService;

    private TurboDto turboDto;

    @BeforeEach
    void setUp() {
        turboDto = new TurboDto(3L, new TurboTypeDto(3L, "TurboCompresor"), new LevelDto(4L, "Super"));
    }

    @Test
    @DisplayName("Test controller get Turbo")
    void getTurbo() throws Exception {
        when(turboService.getTurbo(anyLong())).thenReturn(turboDto);

        mockMvc.perform(get("/turbos/{id}", 3L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(turboDto.getId()))
                .andExpect(jsonPath("$.turboTypeDto.type").value(turboDto.getTurboTypeDto().getType()))
                .andExpect(jsonPath("$.levelDto.level").value(turboDto.getLevelDto().getLevel()));

        verify(turboService, times(1)).getTurbo(anyLong());
    }

    @Test
    @DisplayName("Test controller getAllTurbos")
    void getAllTurbos() throws Exception {
        List<TurboDto> turboDtoList = List.of(turboDto, turboDto);
        when(turboService.getAllTurbos()).thenReturn(turboDtoList);

        mockMvc.perform(get("/turbos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(turboDtoList.getFirst().getId()))
                .andExpect(jsonPath("$[0].turboTypeDto.type").value(turboDtoList.getFirst().getTurboTypeDto().getType()))
                .andExpect(jsonPath("$[1].levelDto.level").value(turboDtoList.get(1).getLevelDto().getLevel()))
                .andExpect(jsonPath("$.size()").value(turboDtoList.size()));

        verify(turboService, times(1)).getAllTurbos();
    }

    @Test
    @DisplayName("Test controller create Turbo")
    void createTurbo() throws Exception {
        doNothing().when(turboService).createTurbo(any(TurboDto.class));

        mockMvc.perform(post("/turbos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"turboTypeDto\":{\"id\":3,\"type\":\"TurboCompresor\"},\"levelDto\":{\"id\":4,\"level\":\"Super\"}}"))
                .andExpect(status().isCreated());

        verify(turboService, times(1)).createTurbo(any(TurboDto.class));
    }

    @Test
    @DisplayName("Test controller update Turbo")
    void updateTurbo() throws Exception {
        doNothing().when(turboService).updateTurbo(anyLong(), any(TurboDto.class));

        mockMvc.perform(put("/turbos/{id}", 3L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"turboTypeDto\":{\"id\":3,\"type\":\"TurboCompresor\"},\"levelDto\":{\"id\":4,\"level\":\"Super\"}}"))
                .andExpect(status().isOk());

        verify(turboService, times(1)).updateTurbo(anyLong(), any(TurboDto.class));
    }

    @Test
    @DisplayName("Test controller delete Turbo")
    void deleteTurbo() throws Exception {
        doNothing().when(turboService).deleteTurbo(anyLong());

        mockMvc.perform(delete("/turbos/{id}", 3L))
                .andExpect(status().isOk());

        verify(turboService, times(1)).deleteTurbo(anyLong());
    }
}