package uk.gov.ons.fwmt.staff_service.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uk.gov.ons.fwmt.staff_service.data.User;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/")
public class UserController {

  public UserController() {

  }

  @PostMapping(produces = "application/json")
  public ResponseEntity<?> createUser(User user) {
    // TODO implement
    return null;
  }

  @PatchMapping(produces = "application/json")
  public ResponseEntity<?> updateUser(Long userId, User user) {
    // TODO implement
    return null;
  }

  @GetMapping(produces = "application/json")
  public List<User> listUsers() {
    // TODO implement
    return null;
  }

  @DeleteMapping(produces = "application/json")
  public void deleteUsers(Long userId) {
    // TODO implement
  }
}
