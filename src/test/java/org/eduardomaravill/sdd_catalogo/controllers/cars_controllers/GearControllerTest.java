package org.eduardomaravill.sdd_catalogo.controllers.cars_controllers;

import org.eduardomaravill.sdd_catalogo.dtos.car_dtos.GearDto;
import org.eduardomaravill.sdd_catalogo.dtos.car_dtos.LevelDto;
import org.eduardomaravill.sdd_catalogo.repositories.users_respositories.IJwtTokenRepository;
import org.eduardomaravill.sdd_catalogo.services.auth.IJwtService;
import org.eduardomaravill.sdd_catalogo.services.cars_services.IGearService;
import org.eduardomaravill.sdd_catalogo.services.cars_services.IStreetTypeService;
import org.eduardomaravill.sdd_catalogo.services.users_services.IUserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(GearController.class)
@AutoConfigureMockMvc(addFilters = false)
class GearControllerTest {

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
    private IGearService gearService;

    private GearDto gearDto;

    @BeforeEach
    void setUp() {
        gearDto = new GearDto(6L, 6, new LevelDto(4L, "Super"));
    }

    @Test
    @DisplayName("Test controller get Gear")
    void getGear() throws Exception {
        when(gearService.getGear(anyLong())).thenReturn(gearDto);

        mockMvc.perform(get("/gears/{id}", 6))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(6))
                .andExpect(jsonPath("$.gearValue").value(6))
                .andExpect(jsonPath("$.levelDto.level").value("Super"));

        verify(gearService).getGear(6L);
    }

    @Test
    @DisplayName("Test controller getAllGears")
    void getAllGears() throws Exception {
        List<GearDto> gearDtoList = List.of(gearDto, gearDto, gearDto);
        when(gearService.getAllGears()).thenReturn(gearDtoList);

        mockMvc.perform(get("/gears"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("[0].id").value(6L))
                .andExpect(jsonPath("[1].gearValue").value(6))
                .andExpect(jsonPath("$[2].levelDto.level").value("Super"))
                .andExpect(jsonPath("$.size()").value(gearDtoList.size()));

        verify(gearService, times(1)).getAllGears();
    }

    @Test
    @DisplayName("Test controller create Gear")
    void createGear() throws Exception {
        doNothing().when(gearService).createGear(any(GearDto.class));

        mockMvc.perform(post("/gears")
                        .contentType("application/json")
                        .content("{\"gearValue\": 6,\"levelDto\":{\"id\":4,\"level\":\"Super\"}}"))
                .andExpect(status().isCreated());

        verify(gearService, times(1)).createGear(any(GearDto.class));
    }

    @Test
    @DisplayName("Test controller update Gear")
    void updateGear() throws Exception {
        doNothing().when(gearService).updateGear(anyLong(), any(GearDto.class));

        mockMvc.perform(put("/gears/{id}", 2L)
                        .contentType("application/json")
                        .content("{\"gearValue\": 6,\"levelDto\":{\"id\":4,\"level\":\"Super\"}}"))
                .andExpect(status().isOk());

        verify(gearService, times(1)).updateGear(anyLong(), any(GearDto.class));
    }

    @Test
    @DisplayName("Test controller delete Gear")
    void deleteGear() throws Exception {
        doNothing().when(gearService).deleteGear(anyLong());

        mockMvc.perform(delete("/gears/{id}", 3L))
               .andExpect(status().isOk());

        verify(gearService, times(1)).deleteGear(anyLong());
    }
}