package uk.gov.ons.fwmt.staff_service.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import uk.gov.ons.fwmt.staff_service.data.dto.UserDTO;
import uk.gov.ons.fwmt.staff_service.data.UserForm;
import uk.gov.ons.fwmt.staff_service.service.TMWebDriverService;
import uk.gov.ons.fwmt.staff_service.service.UserCreationService;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/webDriver")
public class WebDriverController {
  private TMWebDriverService tmWebDriverService;

  @Autowired
  private UserCreationService userCreationService;

  @Autowired
  public WebDriverController(TMWebDriverService tmWebDriverService) {
    this.tmWebDriverService = tmWebDriverService;
  }

  @PostMapping(value = "/createUser", produces = "application/json")
  public void createUser(UserForm userForm) throws IOException {
    tmWebDriverService.makeNewUser(userForm);
  }

  @PostMapping(value = "/createUsers", produces = "application/json")
  public void createUsers(List<UserForm> userForms) throws IOException {
    tmWebDriverService.makeNewUsers(userForms);
  }

  @PostMapping(value = "/createUsersFromCSV", produces = "application/json")
  public void createUsersFromCSV(MultipartFile file) throws IOException {
    Reader reader = new InputStreamReader(file.getInputStream());

    CSVParser parser = CSVFormat.DEFAULT.withHeader().withTrim().parse(reader);

    for (CSVRecord record : parser) {
      UserForm form = new UserForm();
      form.setEmail(record.get("Email"));
      form.setForename(record.get("Firstname"));
      form.setSurname(record.get("Lastname"));
      form.setUserName(record.get("Username"));
      form.setTelNo(record.get("ContactNumber"));
      form.setPassword(record.get("Password"));
      form.setJobTitle(record.get("JobTitle"));
      form.setIsApproved(true);
      form.setPasswordNeverExpires(true);

      log.info(form.toString());

      tmWebDriverService.makeNewUser(form);
    }
  }


  @RequestMapping(method = RequestMethod.POST,value = "/addUsersFromCSV", produces = "application/json", consumes = "multipart/form-data")
  public void AddUsersFromCSV(@RequestParam("file") MultipartFile file) throws IOException {

    Reader reader = new InputStreamReader(file.getInputStream());
    CSVParser parser = CSVFormat.DEFAULT.withHeader().withTrim().parse(reader);

    for (CSVRecord record : parser) {
      UserDTO userDTO = new UserDTO();
      userDTO.setAuthNo(record.get("Authno"));
      userDTO.setTmUsername(record.get("User Name"));
      userDTO.setActiveDate(false);
      userDTO.setDeputyNo("");

      log.info("Adding user: " + userDTO.toString());

      userCreationService.makeNewUser(userDTO);

    }
  }
}
