package com.tsystems.mms.demoapp.dto;

import com.tsystems.mms.demoapp.domain.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDetails {

    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String gender;
    private String organisationalUnit;


    public UserDetails() {
    }


    public UserDetails(User user) {
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.gender = user.getGender().toString();
        this.organisationalUnit = user.getOrganisationalUnit().getName();
    }


}
