package com.tsystems.mms.demoapp.service;


import com.tsystems.mms.demoapp.domain.OrganisationalUnit;
import com.tsystems.mms.demoapp.dto.OrgDetails;
import com.tsystems.mms.demoapp.repository.UnitRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import javax.xml.bind.ValidationException;

@Service
@Transactional
public class UnitService {

    private UnitRepository unitRepository;

    @Autowired
    public UnitService(UnitRepository unitRepository) {
        this.unitRepository = unitRepository;
    }

    /**
     * Returns the org by id.
     */
    public OrganisationalUnit getUnitById(Long id) {
        return unitRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Org not found with id: " + id));
    }

    /**
     * Mapping the entity to the DTO.
     */
    private void mapOrgData(OrganisationalUnit unit, OrgDetails orgDetails) throws ValidationException {
        BeanUtils.copyProperties(orgDetails, unit);

    }
}


//    /**
//     * Delete a user from the database.
//     *
//     * @return a boolean whether the deleting was successful or not.
//     */
//    public boolean deleteOrg(Long id) {
//        boolean success = false;
//        OrganisationalUnit unit = getUserById(id);
//        if (user != null) {
//            userRepository.delete(user);
//            success = true;
//        } else {
//            throw new EntityNotFoundException("User not found with id: " + id);
//        }
//        return success;
//    }
//
//    /**
//     * Update an already saved user.
//     *
//     * @return a boolean whether the save was successful or not.
//     */
//    public boolean updateUser(Long id, UserDetails userDetails) throws ValidationException {
//        boolean success = false;
//        User user = getUserById(id);
//        if (user != null) {
//            mapUserData(user, userDetails);
//            user.setPassword(passwordEncoder.encode(user.getPassword()));
//            success = true;
//        } else {
//            throw new EntityNotFoundException("User not found with id: " + id);
//        }
//        return success;
//    }
//
//    /**
//     * Save user with an appropiate DTO
//     *
//     * @return the saved user's id.
//     */
//    public Long saveUser(UserDetails userDetails) throws ValidationException {
//        User newUser;
//        User savedUser;
//        if (checkIfEmailValid(userDetails.getEmail())) {
//            newUser = new User(userDetails);
//            newUser.setPassword(passwordEncoder.encode(userDetails.getPassword()));
//            savedUser = userRepository.save(newUser);
//            return savedUser.getId();
//        } else {
//            throw new ValidationException("Email not valid");
//        }
//
//    }
//
//    /**
//     * Find all orgs from the database.
//     *
//     * @return List of orgs.
//     */
//    public List<OrganisationalUnit> getAll() {
//        return unitRepository.findAll();
//
//    }
//
//    public OrgDetails getOrgDto(Long id) {
//        OrgDetails orgDetails = null;
//        Optional<OrgDetails> orgOptional = unitRepository.findById(id);
//        if (orgOptional.isPresent()) {
//            orgOptional = new UserDetails(orgOptional.get());
//        }
//        return userDetails;
//    }
//
//
//}
