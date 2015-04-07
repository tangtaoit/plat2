package com.tt.plat.component.form.datatables;

import com.tt.plat.component.form.datatables.column.AjaxBtnColumn;
import com.tt.plat.component.form.datatables.column.ColumnDefsSetting;
import com.tt.plat.component.form.datatables.column.IPColumn;
import com.tt.plat.component.form.json.Json;
import org.json.JSONException;
import org.json.JSONStringer;
import org.json.JSONWriter;

import java.io.Serializable;

/**
 * Created by tangtao on 2014/7/30.
 * Desc:
 */
public class PDataTableSetting {

    protected String tableMarkId;

    private AjaxSettings ajax;

    private ColumnsSettings columns;

    private ColumnDefsSetting columnDefsSetting;

    private PTableTools tableTools;

    private boolean processing;

    private boolean serverSide;

    private String dom;

    private Integer displayLength;

    private boolean responsive=true;

    private String createdRow;

    public ColumnDefsSetting getColumnDefsSetting() {
        if(columnDefsSetting==null){
            columnDefsSetting = new ColumnDefsSetting();
        }
        return columnDefsSetting;
    }

    public void setColumnDefsSetting(ColumnDefsSetting columnDefsSetting) {
        this.columnDefsSetting = columnDefsSetting;
    }

    public Integer getDisplayLength() {
        return displayLength;
    }

    public void setDisplayLength(Integer displayLength) {
        this.displayLength = displayLength;
    }

    public CharSequence toJson() {
        try {
            JSONStringer writer = new JSONStringer();
            writer.object();
            Json.writeObject(writer, "processing", processing);
            Json.writeObject(writer, "serverSide", serverSide);
            Json.writeObject(writer,"dom",dom);
            Json.writeObject(writer,"responsive",responsive);

            Json.writeFunction(writer,"createdRow",createdRow);

            Json.writeObject(writer,"displayLength",displayLength);

            if(columnDefsSetting!=null){
                writer.key("columnDefs");
                columnDefsSetting.toJson(writer);
            }
            if (ajax != null) {
                writer.key("ajax");
                ajax.toJson(writer);
            }
            if (columns != null) {
                writer.key("columns");
                columns.toJson(writer);
            }

            if(tableTools!=null){
                tableTools.tableMarkId=tableMarkId;
                writer.key("tableTools");
                tableTools.toJson(writer);

            }
            writer.endObject();

            return writer.toString();
        } catch (JSONException e) {
            throw new RuntimeException("Could not convert dataTables settings object to Json", e);
        }


    }

    public String getDom() {
        return dom;
    }

    public void setDom(String dom) {
        this.dom = dom;
    }

    public boolean isProcessing() {
        return processing;
    }

    public void setProcessing(boolean processing) {
        this.processing = processing;
    }

    public boolean isServerSide() {
        return serverSide;
    }

    public void setServerSide(boolean serverSide) {
        this.serverSide = serverSide;
    }

    public AjaxSettings getAjax(boolean createIfNotSet) {
        if (createIfNotSet && ajax == null) {
            ajax = new AjaxSettings();
        }
        return ajax;
    }

    public PTableTools getTableTools() {
        return tableTools;
    }


    public String getCreatedRow() {
        return createdRow;
    }

    public void setCreatedRow(String createdRow) {
        this.createdRow = createdRow;
    }

    public void setTableTools(PTableTools tableTools) {
        this.tableTools = tableTools;
    }

    public AjaxSettings getAjax() {
        return ajax;
    }

    public void setAjax(AjaxSettings ajax) {
        this.ajax = ajax;
    }

    public ColumnsSettings getColumns(boolean createIfNotSet) {
        if (createIfNotSet && columns == null) {
            columns = new ColumnsSettings();
        }
        return columns;
    }

    public ColumnsSettings getColumns() {
        return columns;
    }

    public void setColumns(ColumnsSettings columns) {
        this.columns = columns;
    }
}



class ColumnsSettings {

    private IPColumn[] columns;

    private boolean showPrimary;

    void toJson(JSONWriter writer) throws JSONException {

        writer.array();
        if (columns != null) {
            for (IPColumn column : columns) {

                String data = column.getData();
                if (data != null && !data.trim().equals("")) {
                    if (data.equals("id")) {
                        if(showPrimary){
                           writeData(writer,column);
                        }
                    }else{
                        writeData(writer,column);
                    }
                }else {
                    writeData(writer,column);
                }

            }
        }
        writer.endArray();
    }

    private void writeData(JSONWriter writer,IPColumn column) throws JSONException {
        writer.object();
        Json.writeObject(writer, "data", column.getData());
        if (!column.isOrderable()) {
            Json.writeObject(writer, "orderable", false);
        }
        Json.writeObject(writer, "defaultContent", column.getDefaultContent());
        if(!(column instanceof AjaxBtnColumn)){
            Json.writeObject(writer, "className", column.getClassName());
        }

        writer.endObject();
    }


    public IPColumn[] getColumns() {
        return columns;
    }

    public void setColumns(IPColumn[] columns) {
        this.columns = columns;
    }

    public boolean isShowPrimary() {
        return showPrimary;
    }

    public void setShowPrimary(boolean showPrimary) {
        this.showPrimary = showPrimary;
    }
}
