package com.example.nsas_app.modelclasses;

import android.support.annotation.NonNull;

import java.io.Serializable;

public class OrganizationModelclass implements Serializable {
    String orgname;
    String orgid;

    public OrganizationModelclass(String orgname, String orgid) {
        this.orgname = orgname;
        this.orgid = orgid;
    }

    public String getOrgname() {
        return orgname;
    }

    public void setOrgname(String orgname) {
        this.orgname = orgname;
    }

    public String getOrgid() {
        return orgid;
    }

    public void setOrgid(String orgid) {
        this.orgid = orgid;
    }
    @NonNull
    public String toString(){
        return orgname;
    }
}
