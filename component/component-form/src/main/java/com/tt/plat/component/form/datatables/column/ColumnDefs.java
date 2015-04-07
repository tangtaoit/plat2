package com.tt.plat.component.form.datatables.column;

import com.tt.plat.component.form.json.Json;
import org.json.JSONException;
import org.json.JSONWriter;

import java.util.Arrays;

/**
 * Created by tangtao on 2014/8/26.
 * Desc:
 */
public class ColumnDefs {


    //是否可见
    private Boolean visible;

    //渲染
    private String render;

    //目标列
    private int[] targets;

    public ColumnDefs(String render, int[] targets) {
        this.render = render;
        this.targets = targets;
    }

    public ColumnDefs(String render) {
        this.render = render;
    }

    public ColumnDefs(){

    }


    public Boolean getVisible() {
        return visible;
    }

    public void setVisible(Boolean visible) {
        this.visible = visible;
    }

    public String getRender() {
        return render;
    }

    public void setRender(String render) {
        this.render = render;
    }

    public int[] getTargets() {
        return targets;
    }

    public void setTargets(int[] targets) {
        this.targets = targets;
    }

    public void toJson(JSONWriter writer) throws JSONException {
        writer.object();
        Json.writeObject(writer, "visible", getVisible());
        Json.writeFunction(writer,"render",getRender());
        if(targets!=null){
            Json.writeFunction(writer,"targets", Arrays.toString(targets));
        }

        writer.endObject();
    }
}
