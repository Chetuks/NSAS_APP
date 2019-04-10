
package com.example.nsas_app.modelclasses;

import java.io.Serializable;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Detail implements Serializable
{

    @SerializedName("docDetailId")
    @Expose
    private String docDetailId;
    @SerializedName("detailType")
    @Expose
    private String detailType;
    @SerializedName("heading")
    @Expose
    private String heading;
    @SerializedName("subHeading")
    @Expose
    private String subHeading;
    @SerializedName("label")
    @Expose
    private String label;
    @SerializedName("content")
    @Expose
    private String content;
    @SerializedName("coverHeading")
    @Expose
    private String coverHeading;
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("table")
    @Expose
    private Table table;
   /* public final static Creator<Detail> CREATOR = new Creator<Detail>() {


        @SuppressWarnings({
            "unchecked"
        })
        public Detail createFromParcel(Parcel in) {
            return new Detail(in);
        }

        public Detail[] newArray(int size) {
            return (new Detail[size]);
        }

    }
    ;*/
    private final static long serialVersionUID = 3689530115860462086L;

    protected Detail(Parcel in) {
        this.docDetailId = ((String) in.readValue((String.class.getClassLoader())));
        this.detailType = ((String) in.readValue((String.class.getClassLoader())));
        this.heading = ((String) in.readValue((String.class.getClassLoader())));
        this.subHeading = ((String) in.readValue((String.class.getClassLoader())));
        this.label = ((String) in.readValue((String.class.getClassLoader())));
        this.content = ((String) in.readValue((String.class.getClassLoader())));
        this.coverHeading = ((String) in.readValue((String.class.getClassLoader())));
        this.url = ((String) in.readValue((String.class.getClassLoader())));
        this.table = ((Table) in.readValue((Table.class.getClassLoader())));
    }

    public Detail() {
    }

    public String getDocDetailId() {
        return docDetailId;
    }

    public void setDocDetailId(String docDetailId) {
        this.docDetailId = docDetailId;
    }

    public String getDetailType() {
        return detailType;
    }

    public void setDetailType(String detailType) {
        this.detailType = detailType;
    }

    public String getHeading() {
        return heading;
    }

    public void setHeading(String heading) {
        this.heading = heading;
    }

    public String getSubHeading() {
        return subHeading;
    }

    public void setSubHeading(String subHeading) {
        this.subHeading = subHeading;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCoverHeading() {
        return coverHeading;
    }

    public void setCoverHeading(String coverHeading) {
        this.coverHeading = coverHeading;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Table getTable() {
        return table;
    }

    public void setTable(Table table) {
        this.table = table;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(docDetailId);
        dest.writeValue(detailType);
        dest.writeValue(heading);
        dest.writeValue(subHeading);
        dest.writeValue(label);
        dest.writeValue(content);
        dest.writeValue(coverHeading);
        dest.writeValue(url);
        dest.writeValue(table);
    }

    public int describeContents() {
        return  0;
    }

}
