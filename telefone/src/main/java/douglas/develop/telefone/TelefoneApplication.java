package douglas.develop.telefone;

import douglas.develop.core.property.JwtConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan({"douglas.develop.core.model"})
@EnableJpaRepositories({"douglas.develop.core.repository"})
@EnableConfigurationProperties(value = JwtConfiguration.class)
@ComponentScan("douglas.develop")
public class TelefoneApplication {

        public static void main(String[] args) {
            SpringApplication.run(TelefoneApplication.class, args);
        }
}