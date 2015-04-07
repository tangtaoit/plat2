package com.tt.plat.component.form.datatables;

import com.tt.plat.component.form.json.Json;
import org.json.JSONException;
import org.json.JSONStringer;

import java.util.List;

/**
 * Created by tangtao on 2014/8/18.
 * Desc:
 */
public class PTableTools {

    public PTableTools(List<PToolBtn> aButtons) {
        this.aButtons = aButtons;
    }

    /**
     * 按钮集合
     */
    private List<PToolBtn> aButtons;

    /**
     * 单选或多选 single,multi
     */
    private String sRowSelect;

    protected String tableMarkId;





    public PTableTools(List<PToolBtn> aButtons, String sRowSelect) {
        this.aButtons = aButtons;
        this.sRowSelect = sRowSelect;
    }


    public List<PToolBtn> getaButtons() {
        return aButtons;
    }

    public void setaButtons(List<PToolBtn> aButtons) {
        this.aButtons = aButtons;
    }

    public String getsRowSelect() {
        return sRowSelect;
    }

    public void setsRowSelect(String sRowSelect) {
        this.sRowSelect = sRowSelect;
    }


    public void toJson(JSONStringer writer) throws JSONException {



        writer.object();
        Json.writeObject(writer, "sRowSelect", sRowSelect);

        writer.key("aButtons");
        writer.array();
        if(aButtons!=null){

            for(PToolBtn toolBtn:aButtons){
                toolBtn.tableMarkId=tableMarkId;
                toolBtn.toJson(writer);
            }
        }


        writer.endArray();
        writer.endObject();
    }
}
