package org.eduardomaravill.sdd_catalogo.controllers.cars_controllers;

import org.eduardomaravill.sdd_catalogo.dtos.car_dtos.CarDto;
import org.eduardomaravill.sdd_catalogo.dtos.car_dtos.MakerDto;
import org.eduardomaravill.sdd_catalogo.repositories.users_respositories.IJwtTokenRepository;
import org.eduardomaravill.sdd_catalogo.services.auth.IJwtService;
import org.eduardomaravill.sdd_catalogo.services.cars_services.ICarService;
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

@WebMvcTest(CarController.class)
@AutoConfigureMockMvc(addFilters = false)
class CarControllerTest {

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
    private ICarService carService;

    private CarDto carDto;

    @BeforeEach
    void setUp() {
        carDto = new CarDto(5L,new MakerDto(45L,"Audi"),"R8","2020");
    }

    @Test
    @DisplayName("Test controller get Car")
    void getCar() throws Exception {
        when(carService.getCar(anyLong())).thenReturn(carDto);

        mockMvc.perform(get("/cars/{id}", 5L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(carDto.getId()))
                .andExpect(jsonPath("$.makerDto.name").value(carDto.getMakerDto().getName()))
                .andExpect(jsonPath("$.model").value(carDto.getModel()))
                .andExpect(jsonPath("$.year").value(carDto.getYear()));

        verify(carService,times(1)).getCar(anyLong());
    }

    @Test
    @DisplayName("Test controller getAllCars")
    void getAllCars() throws Exception {
        List<CarDto> carDtoList = List.of(carDto,carDto,carDto);
        when(carService.getAllCars()).thenReturn(carDtoList);

        mockMvc.perform(get("/cars"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(carDtoList.getFirst().getId()))
                .andExpect(jsonPath("$[1].makerDto.name").value(carDtoList.get(1).getMakerDto().getName()))
                .andExpect(jsonPath("$[2].model").value(carDtoList.get(2).getModel()))
                .andExpect(jsonPath("$.size()").value(carDtoList.size()));

        verify(carService, times(1)).getAllCars();
    }

    @Test
    @DisplayName("Test controller create Car")
    void createCar() throws Exception{
        doNothing().when(carService).createCar(any(CarDto.class));

        mockMvc.perform(post("/cars")
               .contentType("application/json")
               .content("{\"makerDto\":{\"id\":45,\"name\":\"Audi\"},\"model\":\"R8\",\"year\":\"2020\"}"))
               .andExpect(status().isCreated());

        verify(carService, times(1)).createCar(any(CarDto.class));
    }

    @Test
    @DisplayName("Test controller update Car")
    void updateCar() throws Exception {
        doNothing().when(carService).updateCar(anyLong(), any(CarDto.class));

        mockMvc.perform(put("/cars/{id}", 5L)
               .contentType("application/json")
               .content("{\"makerDto\":{\"id\":45,\"name\":\"Audi\"},\"model\":\"R8\",\"year\":\"2020\"}"))
               .andExpect(status().isOk());

        verify(carService, times(1)).updateCar(anyLong(), any(CarDto.class));
    }

    @Test
    @DisplayName("Test controller delete Car")
    void deleteCar() throws Exception {
        doNothing().when(carService).deleteCar(anyLong());

        mockMvc.perform(delete("/cars/{id}", 5L))
               .andExpect(status().isOk());

        verify(carService, times(1)).deleteCar(anyLong());
    }
}