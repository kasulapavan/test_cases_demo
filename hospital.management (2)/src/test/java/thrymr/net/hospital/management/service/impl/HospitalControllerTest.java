package thrymr.net.hospital.management.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import thrymr.net.hospital.management.controller.HospitalController;
import thrymr.net.hospital.management.service.HospitalService;

@WebMvcTest(HospitalController.class)
public class HospitalControllerTest {

    private MockMvc mockMvc;
@MockBean
    private HospitalService hospitalService;
    @InjectMocks
    private HospitalController hospitalController;
//
//    @BeforeEach
//    public void setUp(){
//        MockitoAnnotations.openMocks(this);
//        mockMvc = MockMvcBuilders.standaloneSetup(hospitalController.)
//    }

    @Test
    @WithMockUser("ADMIN")
    public void testSaveHospital(){



    }






}
