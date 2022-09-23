package com.tsystems.mms.demoapp.service;

import com.tsystems.mms.demoapp.domain.OrganisationalUnit;
import com.tsystems.mms.demoapp.domain.User;
import com.tsystems.mms.demoapp.dto.UserDetails;
import com.tsystems.mms.demoapp.repository.UnitRepository;
import com.tsystems.mms.demoapp.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import javax.xml.bind.ValidationException;
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
    private final UnitRepository unitRepository;


    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, UnitRepository unitRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.unitRepository = unitRepository;
    }

    public UserDetails getUserDetails(Long id) {
        UserDetails userDetails = null;
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {
            userDetails = new UserDetails(userOptional.get());
        }
        return userDetails;
    }

    /**
     * Returns the user by id.
     */
    public User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("User not found with id: " + id));
    }

    /**
     * Mapping the entity to the DTO.
     */
    private void mapUserData(User originalUser, UserDetails userDetails) throws ValidationException {
        if (checkIfEmailValid(userDetails.getEmail())) {
            BeanUtils.copyProperties(userDetails, originalUser);
        } else {
            throw new ValidationException("email is not valid");
        }
    }

    public boolean checkIfEmailValid(String email) {
        boolean isValid = false;
        if (email.matches("^(.+)@(.+)$")) {
            isValid = true;
        }
        return isValid;
    }

    /**
     * Delete a user from the database.
     *
     * @return a boolean whether the deleting was successful or not.
     */
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

    /**
     * Update an already saved user.
     *
     * @return a boolean whether the save was successful or not.
     */
    public boolean updateUser(Long id, UserDetails userDetails) throws ValidationException {
        boolean success = false;
        User user = getUserById(id);
        if (user != null) {
            mapUserData(user, userDetails);
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            success = true;
        } else {
            throw new EntityNotFoundException("User not found with id: " + id);
        }
        return success;
    }

    /**
     * Save user with an appropiate DTO
     *
     * @return the saved user's id.
     */
    public Long saveUser(UserDetails userDetails) throws ValidationException {
        User newUser;
        User savedUser;
        if (checkIfEmailValid(userDetails.getEmail())) {
            newUser = new User(userDetails);
            newUser.setPassword(passwordEncoder.encode(userDetails.getPassword()));
            savedUser = userRepository.save(newUser);
            return savedUser.getId();
        } else {
            throw new ValidationException("Email not valid");
        }

    }

    /**
     * Find all users from the database.
     *
     * @return List of users.
     */
    public List<User> getAll() {
      return userRepository.findAll();

    }

    public void assignOrganisationalUnit(Long userId, Long organisationalUnitId) {
        User user = getUserById(userId);
        OrganisationalUnit organisationalUnit = unitRepository.findById(organisationalUnitId)
                .orElseThrow(() -> new EntityNotFoundException("Org unit not found with id:" + organisationalUnitId));

        user.setOrganisationalUnit(organisationalUnit);

    }


}
