package thrymr.net.hospital.management.service.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.internal.matchers.Null;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import thrymr.net.hospital.management.custom.exception.ApiResponse;
import thrymr.net.hospital.management.dto.HospitalDto;
import thrymr.net.hospital.management.entity.AppUser;
import thrymr.net.hospital.management.entity.Hospital;
import thrymr.net.hospital.management.enums.RoleType;
import thrymr.net.hospital.management.repository.AppUserRepo;
import thrymr.net.hospital.management.repository.HospitalRepo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class HospitalServiceImplementationTest {
    @Mock
    HospitalRepo hospitalRepo;

    @Mock
     AppUserImplementation appUserImplementation;

    @Mock
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();;
    @Mock
    Authentication authentication1;

    @Mock
    SecurityContext securityContext;

    @Mock
    private ModelMapper modelMapper;

    @Mock
    AppUserRepo appUserRepo;

    @InjectMocks
    HospitalServiceImplementation hospitalService;

    AppUser appUser = new AppUser();

    AppUser appUser2 = new AppUser();

    Hospital hospital = new Hospital();

   List<Hospital> hospitalList = new ArrayList<Hospital>();

    List<HospitalDto> hospitalDtoList = new ArrayList<HospitalDto>();

    HospitalDto hospitalDto = new HospitalDto();
    List<AppUser> appUsers = new ArrayList<AppUser>();


    @BeforeEach
    void setUp() {
        hospital.setHospitalId(1L);
        hospital.setName("pavan");
        hospital.setAddress("nandyal");
        hospital.setContactNumber("949654552");

        hospitalDto.setHospitalId(2L);
        hospitalDto.setName("pavan");
        hospitalDto.setAddress("nandyal");
        hospitalDto.setContactNumber("9496514552");
        hospitalDtoList.add(hospitalDto);
        hospitalList.add(hospital);

        hospital.setHospitalId(1L);
        hospital.setName("pavan");
        hospital.setAddress("nandyal");
        hospital.setContactNumber("949654552");

        appUser.setId(1L);
        appUser.setName("sneha");
        appUser.setEmail("sneha@gmail.com");
//        appUser.setPassword("12345");
        appUser.setRoleType(RoleType.ADMIN);
        appUser.setHospitalList(hospitalList);


        appUser2.setId(2L);
        appUser2.setName("pavan");
        appUser2.setEmail("pavan@gmail.com");
//        appUser.setPassword("12345");
        appUser2.setRoleType(RoleType.DOCTOR);
        appUser2.setHospitalList(hospitalList);

        appUsers.add(appUser);
        appUsers.add(appUser2);


    }

    @Test
    @Order(1)
  public   void save() {
        when(hospitalRepo.save(hospital)).thenReturn(hospital);
        ApiResponse apiResponse = hospitalService.save(hospitalDto);
        Assertions.assertEquals(apiResponse.getPayLoad() , "SUCCESS");
        assertEquals(apiResponse.getCode(), HttpStatus.OK.value());
        assertEquals(apiResponse.getMessage(), "Registration is done");
    }
@Test
@Order(2)

  public   void testCaseForGetAll(){
        when(authentication.getPrincipal()).thenReturn(null);
      Exception  exception = assertThrows(Exception.class , ()->hospitalService.getAll(1) , "Unauthorized");
      assertEquals(exception.getMessage() , "Unauthorized");



    }
    @Test
    @Order(3)

    public    void testCase2ForGetAll() throws Exception {
        Integer pageNumber = 0;
        SecurityContextHolder.getContext().setAuthentication(authentication);
        when(authentication.getPrincipal()).thenReturn(appUser);
        Page<AppUser> mockedPage = mock(Page.class);
        when(mockedPage.toList()).thenReturn(appUsers);
        when(appUserRepo.findAll(PageRequest.of(pageNumber, 1))).thenReturn(mockedPage);

        ApiResponse apiResponse = hospitalService.getAll(pageNumber);
        assertEquals(HttpStatus.OK.value() , apiResponse.getCode());
        assertEquals(appUsers , apiResponse.getPayLoad());




    }



    @Test
    @Order(4)

    public void testCaseDoctor() throws Exception {
        Integer pageNumber = 0;
        SecurityContextHolder.getContext().setAuthentication(authentication);
        when(authentication.getPrincipal()).thenReturn(appUser2);

        when(appUserRepo.findByEmail(appUser2.getEmail())).thenReturn(appUser2);
        ApiResponse apiResponse = hospitalService.getAll(pageNumber);

        assertEquals(HttpStatus.OK.value() , apiResponse.getCode());
        assertEquals(hospitalList , apiResponse.getPayLoad());

    }
    @Test
    @Order(5)

    public void testAppUserIsNull(){
                when(authentication1.getPrincipal()).thenReturn(null);
        SecurityContextHolder.getContext().setAuthentication(authentication1);
        assertThrows(Exception.class, () -> hospitalService.getAll(0));

    }

    @Test
    @Order(8)

    public void  testDeleteById(){
        Long id = 1l;
        doNothing().when(hospitalRepo).deleteById(id);
        boolean value = hospitalService.deleteById(id);
        verify(hospitalRepo,times(1)).deleteById(id);
        assertTrue(value);
    }

    @Test
    @Order(9)

    public void  testInvalidIdDeleteById(){
        Long id = 10l;
        doThrow(new RuntimeException("Unable to delete user.")).when(hospitalRepo).deleteById(id);
        boolean value = hospitalService.deleteById(id);
        verify(hospitalRepo,times(1)).deleteById(id);
        assertFalse(value);
    }


    @Test
    @Order(6)

    public  void  testSaveAndUpdateMethod(){
        Long id = 1l;

        when(appUserRepo.findById(id)).thenReturn(Optional.of(appUser));

        Optional<AppUser> appUserOptional = appUserRepo.findById(id);

        AppUser appUser1 = appUserOptional.get();
        when(appUserRepo.save(appUser1)).thenReturn(appUser1);
         when(modelMapper.map(hospitalDto, Hospital.class)).thenReturn(hospital);
        ApiResponse apiResponse = hospitalService.saveAndUpdate(id, hospitalDtoList);

        assertEquals(HttpStatus.OK.value(), apiResponse.getCode());
        assertEquals("updated Successfully" , apiResponse.getMessage());
        assertEquals("SUCCESS", apiResponse.getPayLoad());

    }



    @Test
    @Order(7)

    public  void  testSaveAndUpdateMethodInvalidId(){
        Long id = 10l;
        when(appUserRepo.findById(id)).thenReturn(Optional.empty());
        ApiResponse apiResponse = hospitalService.saveAndUpdate(id, hospitalDtoList);

        assertEquals(HttpStatus.NOT_FOUND.value(), apiResponse.getCode());
        assertEquals("USER NOT FOUND" , apiResponse.getMessage());

    }


}