package uk.gov.ons.fwmt.staff_service.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import uk.gov.ons.fwmt.staff_service.data.dto.UserDTO;
import uk.gov.ons.fwmt.staff_service.service.UserCreationService;

@Slf4j
@Service
public class UserCreationServiceImpl implements UserCreationService {

  private RestTemplate restTemplate;
  private String userCreateUrl;

  @Autowired
  public UserCreationServiceImpl(
      RestTemplate restTemplate,
      @Value("${service.resource.baseUrl}") String baseUrl,
      @Value("${service.resource.operation.users.find.path}") String createUserPath) {
    this.restTemplate = restTemplate;
    this.userCreateUrl = baseUrl + createUserPath;
  }

  public boolean makeNewUser(UserDTO userDTO) {
    try {
      final HttpEntity<UserDTO> request = new HttpEntity<>(userDTO);
      final ResponseEntity responseEntity = restTemplate.postForEntity(userCreateUrl, request, UserDTO.class, userDTO);
      return responseEntity.getStatusCode().equals(HttpStatus.CREATED);
    } catch (HttpClientErrorException httpClientErrorException) {
      log.error("An error occurred while communicating with the resource service", httpClientErrorException);
    }
    return false;
  }
}
