
package com.example.nsas_app.modelclasses;

import java.io.Serializable;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CellValue implements Serializable, Parcelable
{

    @SerializedName("rowNo")
    @Expose
    private Integer rowNo;
    @SerializedName("colNo")
    @Expose
    private Integer colNo;
    @SerializedName("tableDetailId")
    @Expose
    private String tableDetailId;
    @SerializedName("value")
    @Expose
    private String value;
    public final static Creator<CellValue> CREATOR = new Creator<CellValue>() {


        @SuppressWarnings({
            "unchecked"
        })
        public CellValue createFromParcel(Parcel in) {
            return new CellValue(in);
        }

        public CellValue[] newArray(int size) {
            return (new CellValue[size]);
        }

    }
    ;
    private final static long serialVersionUID = -5473498690419211873L;

    protected CellValue(Parcel in) {
        this.rowNo = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.colNo = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.tableDetailId = ((String) in.readValue((String.class.getClassLoader())));
        this.value = ((String) in.readValue((String.class.getClassLoader())));
    }

    public CellValue() {
    }

    public Integer getRowNo() {
        return rowNo;
    }

    public void setRowNo(Integer rowNo) {
        this.rowNo = rowNo;
    }

    public Integer getColNo() {
        return colNo;
    }

    public void setColNo(Integer colNo) {
        this.colNo = colNo;
    }

    public String getTableDetailId() {
        return tableDetailId;
    }

    public void setTableDetailId(String tableDetailId) {
        this.tableDetailId = tableDetailId;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(rowNo);
        dest.writeValue(colNo);
        dest.writeValue(tableDetailId);
        dest.writeValue(value);
    }

    public int describeContents() {
        return  0;
    }

}
