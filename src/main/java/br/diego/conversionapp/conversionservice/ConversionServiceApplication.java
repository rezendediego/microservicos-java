package br.diego.conversionapp.conversionservice;

import info.schnatterer.mobynamesgenerator.MobyNamesGenerator;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.reactive.config.EnableWebFlux;


import java.util.Properties;

@SpringBootApplication
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "spring.application")
@EnableDiscoveryClient
@OpenAPIDefinition(
		info = @Info(
				title = "Conversion Service API",
				description = "Service to calculate money transfers",
				version = "1.0.1",
				contact = @Contact(
						name = "Conversion API Support",
						url = "http://conversionapp.com/contact",
						email = "techsupport@example.com"),
				license = @License(
						name = "Apache 2.0",
						url = "https://www.apache.org/licenses/LICENSE-2.0.html"))
)
public class ConversionServiceApplication {
	public static void main(String[] args) {
		SpringApplication application = new SpringApplication(ConversionServiceApplication.class);
		Properties properties = new Properties();
		String appNickName = MobyNamesGenerator.getRandomName();
		properties.put("spring.application.serverNick",appNickName);
		application.setDefaultProperties(properties);
		application.run(args);
	}

}
