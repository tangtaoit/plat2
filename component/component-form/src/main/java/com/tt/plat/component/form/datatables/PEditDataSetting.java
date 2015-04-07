package com.tt.plat.component.form.datatables;

import com.tt.plat.component.form.json.Json;
import org.json.JSONException;
import org.json.JSONStringer;
import org.json.JSONWriter;

import java.util.List;

/**
 * Created by tangtao on 2014/8/1.
 * Desc:
 */
public class PEditDataSetting {

    private AjaxSettings ajax;

    private String table;

    private String display;


    private FieldSettings fieldSettings;

    public CharSequence toJson() {
        JSONStringer writer = new JSONStringer();
        try {
            writer.object();
            if (ajax != null) {
                writer.key("ajax");
                ajax.toJson(writer);
            }
            Json.writeObject(writer, "table", table);
            Json.writeObject(writer, "display", display);

            if (fieldSettings != null) {
                writer.key("fields");
                fieldSettings.toJson(writer);
            }
            writer.endObject();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return writer.toString();
    }

    public AjaxSettings getAjax(boolean createIfNotSet) {
        if (createIfNotSet && ajax == null) {
            ajax = new AjaxSettings();
        }
        return ajax;
    }

    public AjaxSettings getAjax() {
        return ajax;
    }

    public void setAjax(AjaxSettings ajax) {
        this.ajax = ajax;
    }

    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }

    public String getDisplay() {
        return display;
    }

    public void setDisplay(String display) {
        this.display = display;
    }

    public FieldSettings getFieldSettings(boolean createIfNotSet) {

        if (createIfNotSet && fieldSettings == null) {
            fieldSettings = new FieldSettings();
        }
        return fieldSettings;
    }

    public FieldSettings getFieldSettings() {
        return fieldSettings;
    }

    public void setFieldSettings(FieldSettings fieldSettings) {
        this.fieldSettings = fieldSettings;
    }
}


