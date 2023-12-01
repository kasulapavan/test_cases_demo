package thrymr.net.hospital.management.service.impl;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import thrymr.net.hospital.management.custom.exception.ApiResponse;
import thrymr.net.hospital.management.dto.AppUserDto;
import thrymr.net.hospital.management.entity.AppUser;
import thrymr.net.hospital.management.entity.Hospital;
import thrymr.net.hospital.management.enums.RoleType;
import thrymr.net.hospital.management.repository.AppUserRepo;
import thrymr.net.hospital.management.repository.HospitalRepo;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
//import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(SpringExtension.class)

class AppUserImplementationTest {
    @Mock
    AppUserRepo appUserRepo;
    @Mock
    HospitalRepo hospitalRepo;
    @InjectMocks
    AppUserImplementation appUserImpl;
    AppUser appUser=new AppUser();
    AppUserDto appUserDto=new AppUserDto();
    Hospital  hospital=new Hospital();
    @Mock
    Authentication authentication;
    @Mock
    SecurityContext securityContext;

    @BeforeEach
    void setUp() {
        appUser.setId(1L);
        appUser.setName("sneha");
        appUser.setEmail("sneha@gmail.com");
//        appUser.setPassword("12345");
        appUser.setRoleType(RoleType.ADMIN);
        appUserDto.setId(1L);
        appUserDto.setName("sneha");
        appUserDto.setEmail("sneha@gmail.com");
//        appUserDto.setPassword("12345");
        appUserDto.setRoleType(String.valueOf(RoleType.ADMIN));
    }
    @Test
    public void testCreateAppUser() {
//        when(authentication.getPrincipal()).thenReturn(appUser);
//        when(securityContext.getAuthentication()).thenReturn(authentication);
//        SecurityContextHolder.setContext(securityContext);
        when(appUserRepo.save(appUser)).thenReturn(appUser);
        ApiResponse apiResponse=appUserImpl.save(appUserDto);
        Assertions.assertThat(apiResponse.getPayLoad()!=null).isTrue();
        Assertions.assertThat(apiResponse.getCode()).isEqualTo(HttpStatus.OK.value());
        assertEquals(apiResponse.getCode(), HttpStatus.OK.value());
        assertEquals(apiResponse.getMessage(),"Registration is done");
    }

    @Test
    void associate() {
        Long id=1L;

        when(hospitalRepo.findById(id)).thenReturn(Optional.of(hospital));
        when(appUserRepo.findByEmail(appUser.getEmail())).thenReturn(appUser);
        when(appUserRepo.save(appUser)).thenReturn(appUser);
//        List<AppUser> appUserList= appUserImpl.associate(id,);
    }
//    public void getAll(){
//        Exception exception=assertThrows(Exception.class,()->appUserImpl.g)
//    }
}