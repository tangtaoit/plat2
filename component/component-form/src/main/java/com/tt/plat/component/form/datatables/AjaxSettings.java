package com.tt.plat.component.form.datatables;

import com.tt.plat.component.form.json.Json;
import org.json.JSONException;
import org.json.JSONWriter;

import java.io.Serializable;

/**
 * Created by tangtao on 2014/8/25.
 * Desc:
 */
public class AjaxSettings implements Serializable{
    private String data;

    private String dataType;

    private CharSequence url;

    private String success;

    private String error;

    public void toJson(JSONWriter writer) throws JSONException {
        writer.object();
        Json.writeFunction(writer, "data", data);
        Json.writeObject(writer, "url", url);
        Json.writeObject(writer, "dataType", dataType);
        Json.writeFunction(writer, "success", success);
        Json.writeFunction(writer, "error", error);
        writer.endObject();
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public CharSequence getUrl() {
        return url;
    }

    public void setUrl(CharSequence url) {
        this.url = url;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
