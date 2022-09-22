package com.tsystems.mms.demoapp.dto;

import com.tsystems.mms.demoapp.domain.OrganisationalUnit;

public class OrganisationalUnitListItem {

    private String name;

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
