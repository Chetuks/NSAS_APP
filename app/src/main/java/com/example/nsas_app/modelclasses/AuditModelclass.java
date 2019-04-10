package com.example.nsas_app.modelclasses;

import android.os.Parcel;
import android.os.Parcelable;

public class AuditModelclass implements Parcelable {

    private String duration;
    private  String planId;
    private  String name;
    private  String id;
    private  String status;

    public AuditModelclass(String duration, String planId, String name, String id, String status) {
        this.duration = duration;
        this.planId = planId;
        this.name = name;
        this.id = id;
        this.status = status;
    }
    public  AuditModelclass(){

    }

    protected AuditModelclass(Parcel in) {
        duration = in.readString();
        planId = in.readString();
        name = in.readString();
        id = in.readString();
        status = in.readString();
    }

    public static final Creator<AuditModelclass> CREATOR = new Creator<AuditModelclass>() {
        @Override
        public AuditModelclass createFromParcel(Parcel in) {
            return new AuditModelclass(in);
        }

        @Override
        public AuditModelclass[] newArray(int size) {
            return new AuditModelclass[size];
        }
    };

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getPlanId() {
        return planId;
    }

    public void setPlanId(String planId) {
        this.planId = planId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(duration);
        dest.writeString(planId);
        dest.writeString(name);
        dest.writeString(id);
        dest.writeString(status);
    }
}
