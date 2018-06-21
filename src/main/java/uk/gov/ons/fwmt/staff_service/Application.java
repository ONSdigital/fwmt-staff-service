package uk.gov.ons.fwmt.staff_service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Slf4j
@SpringBootApplication
@EnableSwagger2
@EnableScheduling
public class Application {

  @Value("${service.resource.username}")
  private String userName;
  @Value("${service.resource.password}")
  private String password;


  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }

  @Bean
  public RestTemplate resourcesRestTemplate(RestTemplateBuilder builder) {
    return builder.basicAuthorization(userName, password).build();
  }
}
