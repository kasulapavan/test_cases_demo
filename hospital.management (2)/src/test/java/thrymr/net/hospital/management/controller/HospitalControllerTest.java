package thrymr.net.hospital.management.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import thrymr.net.hospital.management.custom.exception.ApiResponse;
import thrymr.net.hospital.management.dto.HospitalDto;
import thrymr.net.hospital.management.service.HospitalService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static thrymr.net.hospital.management.util.ControllerHelper.jsonToString;

@WebMvcTest(HospitalController.class)
//@Run(MockitoJUnitRunner.class)
class HospitalControllerTest {

    private MockMvc mockMvc;
    @MockBean
    private HospitalService hospitalService;
    @InjectMocks
    private HospitalController hospitalController;

    List<HospitalDto> hospitalDtoList = new ArrayList<HospitalDto>();

    ApiResponse apiResponse;

    final String saveAndUpdateApi = "/api/v1/hospital/update/1";



    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(hospitalController).build();

    }


    @Test
    void saveAndUpdate() throws Exception {
        Long id = 1l;

        apiResponse = new ApiResponse(HttpStatus.OK.value(), "updated Successfully");
        when(hospitalService.saveAndUpdate(anyLong(), any())).thenReturn(apiResponse);
        mockMvc.perform(put(saveAndUpdateApi)
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonToString(hospitalDtoList)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value(apiResponse.getMessage()));

        verify(hospitalService,times(1)).saveAndUpdate(id, hospitalDtoList);

    }

    @Test
    void save() {
        System.out.println("hello");

    }

    @Test
    void update() {
    }

    @Test
    void deleteById() {
    }

    @Test
    void getAll() {
    }
}