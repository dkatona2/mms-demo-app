package com.tsystems.mms.demoapp.dto;

import java.util.List;

public class AllData {

    private List<OrganisationalUnitListItem> orgListItems;

    public AllData(List<OrganisationalUnitListItem> orgListItems) {
        this.orgListItems = orgListItems;
    }

    public List<OrganisationalUnitListItem> getOrgListItems() {
        return orgListItems;
    }

    public void setOrgListItems(List<OrganisationalUnitListItem> orgListItems) {
        this.orgListItems = orgListItems;
    }
}
