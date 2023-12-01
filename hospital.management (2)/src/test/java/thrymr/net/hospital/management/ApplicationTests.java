package thrymr.net.hospital.management;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import thrymr.net.hospital.management.entity.Hospital;

@SpringBootTest
class ApplicationTests {

	@Test
	void contextLoads() {
		Hospital hospital = new Hospital();
			hospital.setHospitalId(null);
		hospital.setName("pavan");
		hospital.setAddress("nandyal");
		hospital.setContactNumber("798789641");
	}

}
