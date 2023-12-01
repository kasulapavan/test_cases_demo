package thrymr.net.hospital.management.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

@Configuration
@PropertySources(value=@PropertySource("classpath:resource-message.properties"))
public class AppConfig {

}
