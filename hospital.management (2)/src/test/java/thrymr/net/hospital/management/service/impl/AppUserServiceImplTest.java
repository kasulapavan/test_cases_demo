package thrymr.net.hospital.management.service.impl;

import com.nimbusds.jose.JOSEException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import thrymr.net.hospital.management.configuration.JwtTokenUtils;
import thrymr.net.hospital.management.custom.exception.ApiResponse;
import thrymr.net.hospital.management.dto.AppUserDto;
import thrymr.net.hospital.management.dto.HospitalDto;
import thrymr.net.hospital.management.dto.SearchDto;
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
public class AppUserServiceImplTest {


  @Mock
    private AppUserRepo appUserRepo;

  @Mock
  private HospitalRepo hospitalRepo;

  @Mock
  private BCryptPasswordEncoder passwordConversion;

  @Mock
    private JwtTokenUtils jwtTokenUtils;

    @InjectMocks
    private AppUserImplementation appUserService;

    AppUser appUser = new AppUser();

    List<AppUserDto> appUserList = new ArrayList<AppUserDto>();

    List<AppUser> appUsers = new ArrayList<AppUser>();

    AppUserDto appUserDto = new AppUserDto();

    Hospital hospital = new Hospital();
    HospitalDto hospitalDto = new HospitalDto();

    SearchDto searchDto = new SearchDto();

    @BeforeEach
    void setUp() {
        hospital.setHospitalId(1L);
        hospital.setName("pavan");
        hospital.setAddress("nandyal");
        hospital.setContactNumber("949654552");

        hospitalDto.setHospitalId(1L);
        hospitalDto.setName("pavan");
        hospitalDto.setAddress("nandyal");
        hospitalDto.setContactNumber("9496514552");

        appUser.setId(1L);
        appUser.setName("sneha");
        appUser.setEmail("sneha@gmail.com");
        appUser.setSpecialization("special");
        appUser.setEducation("education");
//        appUser.setPassword("12345");
        appUser.setRoleType(RoleType.ADMIN);

        appUserDto.setId(1L);
        appUserDto.setName("sneha");
        appUserDto.setEmail("sneha@gmail.com");

//        appUser.setPassword("12345");
        appUserDto.setRoleType(RoleType.ADMIN);

        appUserList.add(appUserDto);

        appUsers.add(appUser);

        searchDto.setName("wersdf");
        searchDto.setSpecialization("special");
        searchDto.setEducation("education");
        searchDto.setHospitalName("hospital1");


    }
@Test
@Order(1)
    public void testCaseForSave(){
        when(appUserRepo.save(appUser)).thenReturn(appUser);
        ApiResponse apiResponse =appUserService.save(appUserDto);
        assertEquals(HttpStatus.OK.value(), apiResponse.getCode());
        assertEquals("Registration is done", apiResponse.getMessage());
        assertEquals("SUCCESS", apiResponse.getPayLoad());
    }


    @Test
    @Order(2)

    public  void  testSignInWithCorrectCredentials() throws JOSEException {
      AppUser appUser = new AppUser();
      appUser.setEmail("kasula@gmail.com");
      appUser.setPassword("$2a$10$nOqm2MRydS/io.//SzqHLu4tSH/HAkpWlXJ7UH56Sq3TWKA2CeqA2");

      when(appUserRepo.findByEmail("kasula@gmail.com")).thenReturn(appUser);

      when(passwordConversion.matches("pavan", "$2a$10$nOqm2MRydS/io.//SzqHLu4tSH/HAkpWlXJ7UH56Sq3TWKA2CeqA2")).thenReturn(true);

      when(jwtTokenUtils.getToken(appUser)).thenReturn("mocked_token");


      AppUserDto loginDto = new AppUserDto();
      loginDto.setEmail("kasula@gmail.com");
      loginDto.setPassword("pavan");

      ApiResponse response = appUserService.signIn(loginDto);

      assertEquals(HttpStatus.OK.value() , response.getCode());
      assertNotNull(response.getPayLoad());
//      assertEquals("mocked_token", response.getPayLoad());


  }

@Test
@Order(3)
public void  testCaseForInvalidEmail() throws JOSEException {

    when(appUserRepo.findByEmail("kasula@gmail.com")).thenReturn(null);

    AppUserDto loginDto = new AppUserDto();
    loginDto.setEmail("kasula@gmail.com");
    loginDto.setPassword("pavan");

    ApiResponse response = appUserService.signIn(loginDto);

    assertEquals(HttpStatus.UNAUTHORIZED.value() , response.getCode());
    assertEquals("Email id is wrong", response.getMessage());


}

    @Test
    @Order(4)

    public void  testCaseForInvalidPassword() throws JOSEException {

    AppUser appUser = new AppUser();
    appUser.setEmail("pavan@gmail.com");
    appUser.setPassword("$2a$10$nOqm2MRydS/io.//SzqHLu4tSH/HAkpWlXJ7UH56Sq3TWKA2CeqA2");

        when(appUserRepo.findByEmail("kasula@gmail.com")).thenReturn(appUser);

        AppUserDto loginDto = new AppUserDto();
        loginDto.setEmail("kasula@gmail.com");
        loginDto.setPassword("pavankumar");

        ApiResponse response = appUserService.signIn(loginDto);

        assertEquals(HttpStatus.UNAUTHORIZED.value() , response.getCode());
        assertEquals("Password is wrong", response.getMessage());


    }


    @Test
    @Order(5)

    public void testForAssociate(){
        Long id = 1l;
        when(hospitalRepo.findById(id)).thenReturn(Optional.of(hospital));

        when(appUserRepo.findByEmail(appUserDto.getEmail())).thenReturn(appUser);

        List<AppUserDto> appUserDtoList = appUserService.associate(id,appUserList);

        assertNotNull(appUserDtoList);


    }

    @Test
    @Order(6)

    public void testForDisassociate(){
        Long id = 1l;
        when(hospitalRepo.findById(id)).thenReturn(Optional.of(hospital));

        when(appUserRepo.findByEmail(appUserDto.getEmail())).thenReturn(appUser);

        AppUserDto appUserDto1 = appUserService.disassociate(id, appUserDto);



    }
@Test
@Order(7)

    public void testForDeleteById(){
        Long id = 1l;

           doNothing().when(appUserRepo).deleteById(id);

            boolean result = appUserService.deleteById(id);

           verify(appUserRepo,times(1)).deleteById(id);

            assertTrue(result);


    }
    @Test
    @Order(8)

    public void testForInvalidId(){

        Long id = 10l;
       doThrow(new RuntimeException("Unable to delete user.")).when(appUserRepo).deleteById(id);

       boolean result = appUserService.deleteById(id);

        verify(appUserRepo,times(1)).deleteById(id);

        assertFalse(result);

    }
       @Test
       @Order(9)

       public void testForSearchByPriority(){

        when(appUserRepo.findAllByName(searchDto.getName())).thenReturn(appUsers);
        when(appUserRepo.findAllBySpecialization(searchDto.getSpecialization())).thenReturn(appUsers);
        when(appUserRepo.findAllByEducation(searchDto.getEducation())).thenReturn(appUsers);
        when(appUserRepo.findAllByHospitalListName(searchDto.getHospitalName())).thenReturn(appUsers);

        ApiResponse apiResponse = appUserService.searchByPriority(searchDto, 0,1);

        assertEquals(HttpStatus.OK.value(), apiResponse.getCode());

    }

    @Test
    @Order(10)
    public void testForSearchByPriorityForNoContent(){

        when(appUserRepo.findAllByName(searchDto.getName())).thenReturn(appUsers);
        when(appUserRepo.findAllBySpecialization(searchDto.getSpecialization())).thenReturn(appUsers);
        when(appUserRepo.findAllByEducation(searchDto.getEducation())).thenReturn(appUsers);
        when(appUserRepo.findAllByHospitalListName(searchDto.getHospitalName())).thenReturn(appUsers);

        ApiResponse apiResponse = appUserService.searchByPriority(searchDto, 2,3);

        assertEquals(HttpStatus.NO_CONTENT.value(), apiResponse.getCode());

    }


}
