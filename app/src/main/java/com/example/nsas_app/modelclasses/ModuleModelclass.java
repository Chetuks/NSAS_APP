package com.example.nsas_app.modelclasses;

import android.support.annotation.NonNull;

import java.io.Serializable;

public class ModuleModelclass implements Serializable {
    String categoryname;
    String categoryid;

    public String getCategoryname() {
        return categoryname;
    }

    public void setCategoryname(String categoryname) {
        this.categoryname = categoryname;
    }

    public String getCategoryid() {
        return categoryid;
    }

    public void setCategoryid(String categoryid) {
        this.categoryid = categoryid;
    }

    public ModuleModelclass(String categoryname, String categoryid) {
        this.categoryname = categoryname;
        this.categoryid = categoryid;
    }

    @NonNull
    public String toString(){
        return categoryname;
    }

}
