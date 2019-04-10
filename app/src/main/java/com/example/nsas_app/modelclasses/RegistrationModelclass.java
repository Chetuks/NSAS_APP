package com.example.nsas_app.modelclasses;

import android.support.annotation.NonNull;

import java.io.Serializable;

public class RegistrationModelclass implements Serializable {
    private String deptid;
    private String deptname;

    public RegistrationModelclass(String deptid, String deptname) {
        this.deptid = deptid;
        this.deptname = deptname;
    }

    public String getDeptid() {
        return deptid;
    }

    public void setDeptid(String deptid) {
        this.deptid = deptid;
    }

    public String getDeptname() {
        return deptname;
    }

    public void setDeptname(String deptname) {
        this.deptname = deptname;
    }

    @NonNull
    public String toString() {
        return deptname;
    }
}
