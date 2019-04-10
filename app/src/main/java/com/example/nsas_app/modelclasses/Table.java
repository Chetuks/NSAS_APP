
package com.example.nsas_app.modelclasses;

import java.io.Serializable;
import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Table implements Serializable
{

    @SerializedName("cellValues")
    @Expose
    private List<CellValue> cellValues = null;
    @SerializedName("tableId")
    @Expose
    private String tableId;
    @SerializedName("rows")
    @Expose
    private Integer rows;
    @SerializedName("cols")
    @Expose
    private Integer cols;
    @SerializedName("tableName")
    @Expose
    private String tableName;
   /* public final static Creator<Table> CREATOR = new Creator<Table>() {


      *//*  @SuppressWarnings({
            "unchecked"
        })
        public Table createFromParcel(Parcel in) {
            return new Table(in);
        }*//*

        public Table[] newArray(int size) {
            return (new Table[size]);
        }

    };*/
    private final static long serialVersionUID = 4556995272838238600L;

    /*protected Table(Parcel in) {
        in.readList(this.cellValues, (CellValue.class.getClassLoader()));
        this.tableId = ((String) in.readValue((String.class.getClassLoader())));
        this.rows = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.cols = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.tableName = ((String) in.readValue((String.class.getClassLoader())));
    }*/

    public Table() {
    }

    public List<CellValue> getCellValues() {
        return cellValues;
    }

    public void setCellValues(List<CellValue> cellValues) {
        this.cellValues = cellValues;
    }

    public String getTableId() {
        return tableId;
    }

    public void setTableId(String tableId) {
        this.tableId = tableId;
    }

    public Integer getRows() {
        return rows;
    }

    public void setRows(Integer rows) {
        this.rows = rows;
    }

    public Integer getCols() {
        return cols;
    }

    public void setCols(Integer cols) {
        this.cols = cols;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(cellValues);
        dest.writeValue(tableId);
        dest.writeValue(rows);
        dest.writeValue(cols);
        dest.writeValue(tableName);
    }

    public int describeContents() {
        return  0;
    }

}
