package com.tsystems.mms.demoapp.user;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * This service manages all user.
 */
@Service
@Transactional
public class UserService {

  private final UserRepository userRepository;

  public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }


  public void saveUser(UserDTO userDTO) {
    User newUser = new User(userDTO);
    User savedUser = userRepository.save(newUser);
  }

  /**
   * Find all users from the database.
   * @return List of users.
   */
  public List<User> getAll() {
    return userRepository.findAll();
  }


}
