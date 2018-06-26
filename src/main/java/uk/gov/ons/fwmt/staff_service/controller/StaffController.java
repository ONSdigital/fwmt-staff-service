package uk.gov.ons.fwmt.staff_service.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import uk.gov.ons.fwmt.staff_service.data.dto.UserDTO;
import uk.gov.ons.fwmt.staff_service.service.UserCreationService;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

@Slf4j
@RestController
@RequestMapping("/staff")
public class StaffController {

  @Autowired
  private UserCreationService userCreationService;

  @Autowired
  public StaffController(UserCreationService userCreationService) {
    this.userCreationService = userCreationService;
  }

  protected static UserDTO buildUserDTO(String authNo, String username, String active, String deputyNo) {

    boolean activeStatus;

    if (active.equalsIgnoreCase("yes")) {
      activeStatus = true;
    } else {
      activeStatus = false;
    }
    UserDTO userDTO = UserDTO.builder().authNo(authNo).tmUsername(username).active(activeStatus).deputyNo(deputyNo)
        .build();
    return userDTO;
  }

  @RequestMapping(method = RequestMethod.POST, value = "/addUsersFromCSV", produces = "application/json", consumes = "multipart/form-data")
  public void addUsersFromCSV(@RequestParam("file") MultipartFile file) throws IOException {

    Reader reader = new InputStreamReader(file.getInputStream());
    CSVParser parser = CSVFormat.DEFAULT.withHeader().withTrim().parse(reader);

    for (CSVRecord record : parser) {
      UserDTO userDTO = buildUserDTO(record.get("Authno"), record.get("User Name"), record.get("Active"),
          record.get("DeputyNo"));

      log.info("Adding user: " + userDTO.toString());
      userCreationService.makeNewUser(userDTO);
    }
  }
}
