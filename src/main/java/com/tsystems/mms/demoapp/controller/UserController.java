package com.tsystems.mms.demoapp.controller;

import com.tsystems.mms.demoapp.domain.User;
import com.tsystems.mms.demoapp.dto.UserDetails;
import com.tsystems.mms.demoapp.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.xml.bind.ValidationException;
import java.util.List;

/**
 * RESTful API controller for managing users.
 */
@RestController
@RequestMapping("/api/v1.0")
public class UserController {

  private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);
  private final UserService userService;

  public UserController(UserService userService) {
    this.userService = userService;
  }

  /**
   * Finds all users.
   * @return A list of users.
   */
  @GetMapping("/user")
  public ResponseEntity<List<User>> getUsers() {
    LOGGER.info("Get all users from the database");
    return ResponseEntity.ok(userService.getAll());
  }

  @GetMapping("/user/{id}")
  public ResponseEntity<UserDetails> getUserById(@PathVariable Long id) {
    LOGGER.info("Get user from the database by ID.");
    return new ResponseEntity<>(userService.getUserDetails(id), HttpStatus.OK);
  }

  @PostMapping("/registration")
  public ResponseEntity<Void> saveUser(@RequestBody UserDetails userDetails) throws ValidationException {
    userService.saveUser(userDetails);
    LOGGER.info("Registering new user");
    return new ResponseEntity<>(HttpStatus.CREATED);
  }

  @DeleteMapping("/user/{id}")
  public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
    userService.deleteUser(id);
    LOGGER.warn("Deleting user");
    return ResponseEntity.ok().build();
  }

  @PutMapping("/user/{id}")
  public ResponseEntity<Void> updateUser(@PathVariable Long id, @RequestBody UserDetails userDetails) throws ValidationException {
    userService.updateUser(id, userDetails);
    LOGGER.warn("updating user: " + id);
    return ResponseEntity.ok().build();
  }

  @PutMapping("/user/{userId}/{organisationalUnitId}")
  public ResponseEntity<Void> updateUserById(@PathVariable Long userId, @PathVariable Long organisationalUnitId) {

    LOGGER.info("Assign user id:" + userId + " with organizational unit:" + organisationalUnitId);
    userService.assignOrganisationalUnit(userId, organisationalUnitId);
    return ResponseEntity.ok().build();
  }
}