package com.example.nsas_app.activities;

import java.io.Serializable;

public class Meetingactionmodelclass implements Serializable {
    private String assigneddate;
    private String tasktype;
    private String targetdate;
    private String taskname;
    private String taskID;
    private String assignedTo;
    private String Status;

    public String getAssigneddate() {
        return assigneddate;
    }

    public void setAssigneddate(String assigneddate) {
        this.assigneddate = assigneddate;
    }

    public String getTasktype() {
        return tasktype;
    }

    public void setTasktype(String tasktype) {
        this.tasktype = tasktype;
    }

    public String getTargetdate() {
        return targetdate;
    }

    public void setTargetdate(String targetdate) {
        this.targetdate = targetdate;
    }

    public String getTaskname() {
        return taskname;
    }

    public void setTaskname(String taskname) {
        this.taskname = taskname;
    }

    public String getTaskID() {
        return taskID;
    }

    public void setTaskID(String taskID) {
        this.taskID = taskID;
    }

    public String getAssignedTo() {
        return assignedTo;
    }

    public void setAssignedTo(String assignedTo) {
        this.assignedTo = assignedTo;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }


}
