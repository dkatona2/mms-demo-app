package com.tsystems.mms.demoapp.user;

import liquibase.pro.packaged.R;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
  public ResponseEntity getUsers() {

    LOGGER.info("Get all users from the database");
    return ResponseEntity.ok(userService.getAll());
  }

  @GetMapping("/user/{id}")
  public ResponseEntity getUserById(@PathVariable Long id) {
    User findUser = userService.getUserById(id);
    LOGGER.info("Get user from the database by ID.");
    return ResponseEntity.ok(findUser);
  }

  @PostMapping("/registration")
  public ResponseEntity<Void> saveUser(@RequestBody UserDTO userDTO) {
    userService.saveUser(userDTO);
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
  public ResponseEntity<Void> updateUser(@PathVariable Long id, @RequestBody UserDTO userDTO) {
    userService.updateUser(id, userDTO);
    LOGGER.warn("updating user: " + id);
    return ResponseEntity.ok().build();
  }
}