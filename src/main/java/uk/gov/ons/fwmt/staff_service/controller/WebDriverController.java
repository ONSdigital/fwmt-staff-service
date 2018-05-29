package uk.gov.ons.fwmt.staff_service.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import uk.gov.ons.fwmt.staff_service.data.UserForm;
import uk.gov.ons.fwmt.staff_service.service.TMWebDriverService;

import java.io.IOException;
import java.util.List;

@Slf4j
@RestController
public class WebDriverController {
  private TMWebDriverService tmWebDriverService;

  @Autowired
  public WebDriverController(TMWebDriverService tmWebDriverService) {
    this.tmWebDriverService = tmWebDriverService;
  }

  @RequestMapping(value = "/createUser", method = RequestMethod.POST, produces = "application/json")
  public void createUser(UserForm userForm) throws IOException {
    tmWebDriverService.makeNewUser(userForm);
  }

  @RequestMapping(value = "/createUsers", method = RequestMethod.POST, produces = "application/json")
  public void createUser(List<UserForm> userForms) throws IOException {
    tmWebDriverService.makeNewUsers(userForms);
  }
}
