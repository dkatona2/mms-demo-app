package com.tsystems.mms.demoapp.dto;

public class OrgDetails {
    private String name;

    public OrgDetails() {
    }

    public OrgDetails(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "OrgDetails{" +
                "name='" + name + '\'' +
                '}';
    }
}
