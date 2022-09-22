package com.tsystems.mms.demoapp.dto;

import com.tsystems.mms.demoapp.domain.User;
import com.tsystems.mms.demoapp.dto.OrganisationalUnitListItem;

import java.util.List;
import java.util.stream.Collectors;

public class UserDetails {

    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String gender;
    private List<OrganisationalUnitListItem> orgList;


    public UserDetails() {
    }

    public UserDetails(String email, String password, String firstName, String lastName, String gender) {
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;

    }
    public UserDetails(User user) {
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.gender = user.getGender().toString();
        this.orgList = user.getOrganisationalUnits().stream()
                .map(OrganisationalUnitListItem::new)
                .collect(Collectors.toList());
    }


    public List<OrganisationalUnitListItem> getOrgList() {
        return orgList;
    }

    public void setOrgList(List<OrganisationalUnitListItem> orgList) {
        this.orgList = orgList;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
