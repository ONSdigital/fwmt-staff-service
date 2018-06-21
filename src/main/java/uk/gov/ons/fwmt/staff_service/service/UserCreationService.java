package uk.gov.ons.fwmt.staff_service.service;

import uk.gov.ons.fwmt.staff_service.data.dto.UserDTO;

public interface UserCreationService {

    boolean makeNewUser (UserDTO userDTO);
}
