package io.comfortchauke.drones;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenAPIConfig {

    @Value("${io.comfortchauke.drone.dev-url}")
    private String devUrl;

    @Value("${io.comfortchauke.drone.prod-url}")
    private String prodUrl;

    @Bean
    public OpenAPI myOpenAPI() {
        Server devServer = new Server();
        devServer.setUrl(devUrl);
        devServer.setDescription("Server URL in Development environment");

        Server prodServer = new Server();
        prodServer.setUrl(prodUrl);
        prodServer.setDescription("Server URL in Production environment");

        Contact contact = new Contact();
        contact.setEmail("comfortchauke.io@gmail.com");
        contact.setName("ComfortChauke");
        contact.setUrl("https://github.com/kyerise");

        License mitLicense = new License().name("Apache License 2.0").url("https://choosealicense.com/licenses/apache-2.0/");

        Info info = new Info()
                .title("Drones API")
                .version("0.0.1")
                .contact(contact)
                .description("This API exposes endpoints manage drones.").termsOfService("https://choosealicense.com/licenses/apache-2.0/")
                .license(mitLicense);

        return new OpenAPI().info(info).servers(List.of(devServer, prodServer));
    }
}