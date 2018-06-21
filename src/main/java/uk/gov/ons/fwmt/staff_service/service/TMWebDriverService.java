package uk.gov.ons.fwmt.staff_service.service;

import uk.gov.ons.fwmt.staff_service.data.UserForm;

import java.io.IOException;
import java.util.List;

public interface TMWebDriverService {
  void makeNewUser(UserForm userForm) throws IOException;

  void makeNewUsers(List<UserForm> userForms) throws IOException;

}
