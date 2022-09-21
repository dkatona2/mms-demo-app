package com.tsystems.mms.demoapp.user;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

/**
 * This service manages all user.
 */
@Service
@Transactional
public class UserService {


    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("User not found with id: " + id));
    }

    private void mapUserData(User originalUser, UserDTO userDTO) {
        BeanUtils.copyProperties(userDTO, originalUser);

    }

    public boolean deleteUser(Long id) {
        boolean success = false;
        User user = getUserById(id);
        if (user != null) {
            userRepository.delete(user);
            success = true;
        } else {
            throw new EntityNotFoundException("User not found with id: " + id);
        }
        return success;
    }

    public boolean updateUser(Long id, UserDTO userDTO) {
        boolean success = false;
        User user = getUserById(id);
        if (user != null) {
            mapUserData(user, userDTO);
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            success = true;
        } else {
            throw new EntityNotFoundException("User not found with id: " + id);
        }
        return success;
    }


    public Long saveUser(UserDTO userDTO) {
        User newUser = new User(userDTO);
        newUser.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        User savedUser = userRepository.save(newUser);
        return savedUser.getId();
    }

    /**
     * Find all users from the database.
     *
     * @return List of users.
     */
    public List<User> getAll() {
        return userRepository.findAll();
    }


}
