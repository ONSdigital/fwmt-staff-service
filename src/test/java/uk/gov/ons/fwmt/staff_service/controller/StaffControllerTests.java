package uk.gov.ons.fwmt.staff_service.controller;

import static org.junit.Assert.*;

import org.junit.Test;

import uk.gov.ons.fwmt.staff_service.data.dto.UserDTO;

public class StaffControllerTests {

  @Test
  public void givenValidValuesCheckAValidDTOIsCreated() {
    UserDTO expectedDTO = new UserDTO("A","UN", false, "");
    UserDTO actualDTO = StaffController.buildUserDTO("A", "UN");
    
    assertEquals(expectedDTO, actualDTO);
  }

}
