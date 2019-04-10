package com.example.nsas_app.modelclasses;

import java.io.Serializable;

public class DashboardModelClass implements Serializable {
    String documentname;
    int docId;
    String createdUser;
    String createdDate;
    String docurl;
    boolean gettingState;
    String docFullname;

    public DashboardModelClass(String documentname, int docId, String createdUser, String createdDate, String docurl, boolean gettingState, String docFullname) {
        this.documentname = documentname;
        this.docId = docId;
        this.createdUser = createdUser;
        this.createdDate = createdDate;
        this.docurl = docurl;
        this.gettingState = gettingState;
        this.docFullname = docFullname;
    }

    public String getDocumentname() {
        return documentname;
    }

    public void setDocumentname(String documentname) {
        this.documentname = documentname;
    }

    public int getDocId() {
        return docId;
    }

    public void setDocId(int docId) {
        this.docId = docId;
    }

    public String getCreatedUser() {
        return createdUser;
    }

    public void setCreatedUser(String createdUser) {
        this.createdUser = createdUser;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getDocurl() {
        return docurl;
    }

    public void setDocurl(String docurl) {
        this.docurl = docurl;
    }

    public boolean isGettingState() {
        return gettingState;
    }

    public void setGettingState(boolean gettingState) {
        this.gettingState = gettingState;
    }

    public String getDocFullname() {
        return docFullname;
    }

    public void setDocFullname(String docFullname) {
        this.docFullname = docFullname;
    }
}
