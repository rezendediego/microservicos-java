package br.com.acme.apigateway;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ApiGatewayApplication {
	private static final Logger LOGGER = LoggerFactory.getLogger(ApiGatewayApplication.class);
	public static void main(String[] args) {SpringApplication.run(ApiGatewayApplication.class, args);}

	@Bean
	public RouteLocator myRoutes(RouteLocatorBuilder builder){
		LOGGER.info("API-GATEWAY está sendo executada");
		return builder.routes()
				.route(p -> p //ROTA GET API QUOTES
						.path("/conversionapp/api/v1/quotes/**")
						.filters(f -> f.stripPrefix(3)
								.retry(3)
								.addRequestHeader("Request","Header")
								.addResponseHeader("Allow-Origins","*"))
						//.uri("lb://QUOTE-SERVICE")
						.uri("http://localhost:8081")
				)
				.route(p->p
						.path("/conversionapp/api/v1/conversions/**")
						.filters(f -> f.stripPrefix(3)
								.retry(3))
						//.uri("lb://CONVERSION-SERVICE")
						.uri("http://localhost:8080")
				)
				.build();
	}

}
