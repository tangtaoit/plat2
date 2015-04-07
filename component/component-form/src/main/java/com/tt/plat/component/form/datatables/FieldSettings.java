package com.tt.plat.component.form.datatables;

import com.tt.plat.component.form.json.Json;
import org.json.JSONException;
import org.json.JSONWriter;

import java.util.Arrays;

/**
 * Created by tangtao on 2014/8/4.
 * Desc:
 */
public class FieldSettings {

    private IPField[] ipFields;


    void toJson(JSONWriter writer) throws JSONException {
        writer.array();
        if (ipFields != null) {
            for (IPField field : ipFields) {


                String data = field.getLabel();
                if (data != null) {
                    if (data != null && !data.trim().equals("")) {

                        writer.object();

                        writData(writer, field);

                        writer.endObject();
                    }
                }
            }
            writer.endArray();
        }


    }

    private void writData(JSONWriter jsonWriter, IPField field) {
        try {
            Json.writeObject(jsonWriter, "label", field.getLabel());
            Json.writeObject(jsonWriter, "name", field.getName());
            Json.writeObject(jsonWriter, "type", field.getType());
            Json.writeObject(jsonWriter, "default", field.getDef());
            IpOpts[] ipOptses = field.getIpOpts();
            if (ipOptses != null) {
                jsonWriter.key("ipOpts");
                jsonWriter.array();

                for (IpOpts ipOpts : ipOptses) {
                    jsonWriter.object();
                    Json.writeObject(jsonWriter, "label", ipOpts.getLabel());
                    Json.writeObject(jsonWriter, "value", ipOpts.getValue());
                    jsonWriter.endObject();
                }
                jsonWriter.endArray();
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public IPField[] getIpFields() {
        return ipFields;
    }

    public void setIpFields(IPField[] ipFields) {
        this.ipFields = ipFields;
    }
}
