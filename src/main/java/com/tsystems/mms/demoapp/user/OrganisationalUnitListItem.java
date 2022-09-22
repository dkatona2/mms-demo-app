package com.tsystems.mms.demoapp.user;

public class OrganisationalUnitListItem {

    private String name;

    public OrganisationalUnitListItem() {
    }

    public OrganisationalUnitListItem(OrganisationalUnit organisationalUnit) {
        this.name = organisationalUnit.getName();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
