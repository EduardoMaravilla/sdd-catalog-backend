package org.eduardomaravill.sdd_catalogo.controllers.cars_controllers;

import org.eduardomaravill.sdd_catalogo.dtos.car_dtos.DriverDto;
import org.eduardomaravill.sdd_catalogo.dtos.car_dtos.InitSkidDto;
import org.eduardomaravill.sdd_catalogo.repositories.users_respositories.IJwtTokenRepository;
import org.eduardomaravill.sdd_catalogo.services.auth.IJwtService;
import org.eduardomaravill.sdd_catalogo.services.cars_services.IDriverService;
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

@WebMvcTest(DriverController.class)
@AutoConfigureMockMvc(addFilters = false)
class DriverControllerTest {

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
    private IDriverService driverService;

    private DriverDto driverDto;

    @BeforeEach
    void setUp() {
        driverDto = new DriverDto(2L, 150, 8, 4, true, new InitSkidDto(3L, "NO"));
    }

    @Test
    @DisplayName("Test controller get Driver")
    void getDriver() throws Exception {
        when(driverService.getDriver(anyLong())).thenReturn(driverDto);

        mockMvc.perform(get("/drivers/{id}",2L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(driverDto.getId()))
                .andExpect(jsonPath("$.drive").value(driverDto.getDrive()))
                .andExpect(jsonPath("$.initSkidDto.skidType").value(driverDto.getInitSkidDto().getSkidType()));

        verify(driverService, times(1)).getDriver(anyLong());
    }

    @Test
    @DisplayName("Test controller getAllDrivers")
    void getAllDrivers() throws Exception {
        List<DriverDto> driverDtoList = List.of(driverDto, driverDto,driverDto);
        when(driverService.getAllDrivers()).thenReturn(driverDtoList);

        mockMvc.perform(get("/drivers"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(driverDto.getId()))
                .andExpect(jsonPath("$[1].drive").value(driverDto.getDrive()))
                .andExpect(jsonPath("$[2].direction").value(driverDto.getDirection()))
                .andExpect(jsonPath("$[0].initSkidDto.skidType").value(driverDto.getInitSkidDto().getSkidType()))
                .andExpect(jsonPath("$.size()").value(driverDtoList.size()));

        verify(driverService, times(1)).getAllDrivers();
    }

    @Test
    @DisplayName("Test controller create Driver")
    void createDriver() throws Exception {
        doNothing().when(driverService).createDriver(any(DriverDto.class));

        mockMvc.perform(post("/drivers")
               .contentType("application/json")
               .content("{\"drive\": 150, \"direction\": 8, \"downForce\": 4, \"controlTraction\": true, \"initSkidDto\": {\"id\": 3, \"skidType\": \"NO\"}}"))
               .andExpect(status().isCreated());

        verify(driverService, times(1)).createDriver(any(DriverDto.class));
    }

    @Test
    @DisplayName("Test controller update Driver")
    void updateDriver() throws Exception {
        doNothing().when(driverService).updateDriver(anyLong(), any(DriverDto.class));

        mockMvc.perform(put("/drivers/{id}",2L)
               .contentType("application/json")
               .content("{\"drive\": 150, \"direction\": 8, \"downForce\": 4, \"controlTraction\": true, \"initSkidDto\": {\"id\": 3, \"skidType\": \"NO\"}}"))
               .andExpect(status().isOk());

        verify(driverService, times(1)).updateDriver(anyLong(), any(DriverDto.class));
    }

    @Test
    @DisplayName("Test controller delete Driver")
    void deleteDriver() throws Exception {
        doNothing().when(driverService).deleteDriver(anyLong());

        mockMvc.perform(delete("/drivers/{id}", 2L))
               .andExpect(status().isOk());

        verify(driverService, times(1)).deleteDriver(anyLong());
    }
}