package com.tt.plat.component.form.datatables.column;

import org.json.JSONException;
import org.json.JSONWriter;

/**
 * Created by tangtao on 2014/8/26.
 * Desc:
 */
public class ColumnDefsSetting {

    private ColumnDefs[] columnDefs;

    public ColumnDefs[] getColumnDefs() {
        return columnDefs;
    }

    public void setColumnDefs(ColumnDefs[] columnDefs) {
        this.columnDefs = columnDefs;
    }

    public void toJson(JSONWriter writer) throws JSONException {

        if(columnDefs!=null){
            writer.array();
            for(ColumnDefs columnDef:columnDefs){
               columnDef.toJson(writer);
            }
            writer.endArray();
        }

    }
}
