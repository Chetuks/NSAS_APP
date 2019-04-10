
package com.example.nsas_app.modelclasses;

import java.io.Serializable;
import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DocumentModelClas implements Serializable, Parcelable
{

    @SerializedName("DocId")
    @Expose
    private String docId;
    @SerializedName("external")
    @Expose
    private Boolean external;
    @SerializedName("CreatedBy")
    @Expose
    private String createdBy;
    @SerializedName("CreatedDate")
    @Expose
    private String createdDate;
    @SerializedName("DocName")
    @Expose
    private String docName;
    @SerializedName("DocFullName")
    @Expose
    private String docFullName;
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("Detail")
    @Expose
    private List<Detail> detail = null;
    public final static Creator<DocumentModelClas> CREATOR = new Creator<DocumentModelClas>() {


        @SuppressWarnings({
            "unchecked"
        })
        public DocumentModelClas createFromParcel(Parcel in) {
            return new DocumentModelClas(in);
        }

        public DocumentModelClas[] newArray(int size) {
            return (new DocumentModelClas[size]);
        }

    }
    ;
    private final static long serialVersionUID = 3255593620331691698L;

    public DocumentModelClas(Parcel in) {
        this.docId = ((String) in.readValue((String.class.getClassLoader())));
        this.external = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
        this.createdBy = ((String) in.readValue((String.class.getClassLoader())));
        this.createdDate = ((String) in.readValue((String.class.getClassLoader())));
        this.docName = ((String) in.readValue((String.class.getClassLoader())));
        this.docFullName = ((String) in.readValue((String.class.getClassLoader())));
        this.url = ((String) in.readValue((String.class.getClassLoader())));
        in.readList(this.detail, (Detail.class.getClassLoader()));
    }

    public DocumentModelClas() {
    }

    public String getDocId() {
        return docId;
    }

    public void setDocId(String docId) {
        this.docId = docId;
    }

    public Boolean getExternal() {
        return external;
    }

    public void setExternal(Boolean external) {
        this.external = external;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getDocName() {
        return docName;
    }

    public void setDocName(String docName) {
        this.docName = docName;
    }

    public String getDocFullName() {
        return docFullName;
    }

    public void setDocFullName(String docFullName) {
        this.docFullName = docFullName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<Detail> getDetail() {
        return detail;
    }

    public void setDetail(List<Detail> detail) {
        this.detail = detail;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(docId);
        dest.writeValue(external);
        dest.writeValue(createdBy);
        dest.writeValue(createdDate);
        dest.writeValue(docName);
        dest.writeValue(docFullName);
        dest.writeValue(url);
        dest.writeList(detail);
    }

    public int describeContents() {
        return  0;
    }

}
