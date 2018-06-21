package uk.gov.ons.fwmt.staff_service.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import uk.gov.ons.fwmt.staff_service.data.dto.UserDTO;
import uk.gov.ons.fwmt.staff_service.service.UserCreationService;

@Slf4j
@Service
public class UserCreationServiceImpl implements UserCreationService {

    private RestTemplate restTemplate;
    private String userCreateUrl;

    @Autowired
    public UserCreationServiceImpl( RestTemplate restTemplate,@Value("${service.resource.baseUrl}") String baseUrl,
            @Value("${service.resource.operation.users.find.path}") String UserPath){
        this.restTemplate = restTemplate;
        userCreateUrl = baseUrl + UserPath;
    }

    public void makeNewUser (UserDTO userDTO)
    {
        restTemplate.postForObject(userCreateUrl, userDTO, UserDTO.class);
    }

}
