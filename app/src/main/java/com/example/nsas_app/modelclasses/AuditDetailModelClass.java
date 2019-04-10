package com.example.nsas_app.modelclasses;

import android.os.Parcel;
import android.os.Parcelable;

public class AuditDetailModelClass implements Parcelable {
    private String name;
    private String planId;
    private String id;
    private String auditId;
    private String result;
    private String uploadFile;
    private String scoring;
    private String resultId;
    private boolean implimentation;
    private boolean documentation;

    public AuditDetailModelClass() {
    }

    protected AuditDetailModelClass(Parcel in) {
        name = in.readString();
        planId = in.readString();
        id = in.readString();
        auditId = in.readString();
        result = in.readString();
        uploadFile = in.readString();
        scoring = in.readString();
        resultId = in.readString();
        implimentation = in.readByte() != 0;
        documentation = in.readByte() != 0;
    }

    public static final Creator<AuditDetailModelClass> CREATOR = new Creator<AuditDetailModelClass>() {
        @Override
        public AuditDetailModelClass createFromParcel(Parcel in) {
            return new AuditDetailModelClass(in);
        }

        @Override
        public AuditDetailModelClass[] newArray(int size) {
            return new AuditDetailModelClass[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPlanId() {
        return planId;
    }

    public void setPlanId(String planId) {
        this.planId = planId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAuditId() {
        return auditId;
    }

    public void setAuditId(String auditId) {
        this.auditId = auditId;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getUploadFile() {
        return uploadFile;
    }

    public void setUploadFile(String uploadFile) {
        this.uploadFile = uploadFile;
    }

    public String getScoring() {
        return scoring;
    }

    public void setScoring(String scoring) {
        this.scoring = scoring;
    }

    public String getResultId() {
        return resultId;
    }

    public void setResultId(String resultId) {
        this.resultId = resultId;
    }

    public boolean isImplimentation() {
        return implimentation;
    }

    public void setImplimentation(boolean implimentation) {
        this.implimentation = implimentation;
    }

    public boolean isDocumentation() {
        return documentation;
    }

    public void setDocumentation(boolean documentation) {
        this.documentation = documentation;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(planId);
        dest.writeString(id);
        dest.writeString(auditId);
        dest.writeString(result);
        dest.writeString(uploadFile);
        dest.writeString(scoring);
        dest.writeString(resultId);
        dest.writeByte((byte) (implimentation ? 1 : 0));
        dest.writeByte((byte) (documentation ? 1 : 0));
    }
}
