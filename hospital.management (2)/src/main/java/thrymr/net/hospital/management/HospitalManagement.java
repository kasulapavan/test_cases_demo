package thrymr.net.hospital.management;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;


@SpringBootApplication
@OpenAPIDefinition(info = @Info(
		title = "Sports Swagger Test",
		version = "2.0.0",
		description = "This is for Learning Only!!",
		termsOfService = "sneha",
		contact = @Contact(
				name = "Sneha Kamisetty",
				email = "sneha@gmail.com"
		),
		license = @License(
				name = "Sneha",
				url = "www.sneha.com"
		)
)
)
public class HospitalManagement {
	@Bean
	public ModelMapper modelMapper(){
		return new ModelMapper();
	}
	public static void main(String[] args) {
		SpringApplication.run(HospitalManagement.class, args);

		Person person = new Person("John", 25);

		// Get the class of the object
		Class<?> personClass = person.getClass();

		// Get class name
		System.out.println("Class Name: " + personClass.getName());
		Map<String, Integer> fieldTypeCount = new HashMap<>();

		// Get class fields
		Field[] fields = personClass.getDeclaredFields();
		for (Field field : fields) {
			// Get the type of the field
			String fieldType = field.getType().getSimpleName();

			// Update the count in the map
			fieldTypeCount.put(fieldType, fieldTypeCount.getOrDefault(fieldType, 0) + 1);
		}

		// Print the field type count
		System.out.println("Field Type Count:");
		for (Map.Entry<String, Integer> entry : fieldTypeCount.entrySet()) {
			System.out.println(entry.getKey() + ": " + entry.getValue());
		}
	}

	static class Person {
		private String name;
		private int age;

		public Person(String name, int age) {
			this.name = name;
			this.age = age;
		}

		// Getter and setter methods

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public int getAge() {
			return age;
		}

		public void setAge(int age) {
			this.age = age;
		}

		// Additional methods
	}

//		try {
//			// Your code that may throw exceptions
//		} catch (Exception e) {
//			logger.error("An exception occurred:", e);
//			sendExceptionEmail(e);
//		}


		@Bean
		public BCryptPasswordEncoder bCryptPasswordEncoder(){
			return new BCryptPasswordEncoder();
		}
}


//import org.slf4j.Logger;


//public class ExceptionHandlingAndEmail {
//	private static final Logger logger = LoggerFactory.getLogger(ExceptionHandlingAndEmail.class);
//
//	public static void main(String[] args) {
//		try {
//			// Your code that may throw exceptions
//		} catch (Exception e) {
//			logger.error("An exception occurred:", e);
//			sendExceptionEmail(e);
//		}
//	}




