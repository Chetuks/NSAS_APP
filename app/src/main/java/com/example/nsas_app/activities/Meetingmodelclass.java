package com.example.nsas_app.activities;

import android.os.Parcel;
import android.os.Parcelable;

public class Meetingmodelclass implements Parcelable {
    private String meetingtime;
    private String duration;
    private String meetingname;
    private String meetingdate;
    private String description;
    private String schedulestatus;
    private String meetingId;
    private String meetingtype;
    private String meetingStatus;

    public Meetingmodelclass() {
    }

    protected Meetingmodelclass(Parcel in) {
        meetingtime = in.readString();
        duration = in.readString();
        meetingname = in.readString();
        meetingdate = in.readString();
        description = in.readString();
        schedulestatus = in.readString();
        meetingId = in.readString();
        meetingtype = in.readString();
        meetingStatus = in.readString();
    }

    public static final Creator<Meetingmodelclass> CREATOR = new Creator<Meetingmodelclass>() {
        @Override
        public Meetingmodelclass createFromParcel(Parcel in) {
            return new Meetingmodelclass(in);
        }

        @Override
        public Meetingmodelclass[] newArray(int size) {
            return new Meetingmodelclass[size];
        }
    };

    public String getMeetingtime() {
        return meetingtime;
    }

    public void setMeetingtime(String meetingtime) {
        this.meetingtime = meetingtime;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getMeetingname() {
        return meetingname;
    }

    public void setMeetingname(String meetingname) {
        this.meetingname = meetingname;
    }

    public String getMeetingdate() {
        return meetingdate;
    }

    public void setMeetingdate(String meetingdate) {
        this.meetingdate = meetingdate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSchedulestatus() {
        return schedulestatus;
    }

    public void setSchedulestatus(String schedulestatus) {
        this.schedulestatus = schedulestatus;
    }

    public String getMeetingId() {
        return meetingId;
    }

    public void setMeetingId(String meetingId) {
        this.meetingId = meetingId;
    }

    public String getMeetingtype() {
        return meetingtype;
    }

    public void setMeetingtype(String meetingtype) {
        this.meetingtype = meetingtype;
    }

    public String getMeetingStatus() {
        return meetingStatus;
    }

    public void setMeetingStatus(String meetingStatus) {
        this.meetingStatus = meetingStatus;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(meetingtime);
        dest.writeString(duration);
        dest.writeString(meetingname);
        dest.writeString(meetingdate);
        dest.writeString(description);
        dest.writeString(schedulestatus);
        dest.writeString(meetingId);
        dest.writeString(meetingtype);
        dest.writeString(meetingStatus);
    }
}
