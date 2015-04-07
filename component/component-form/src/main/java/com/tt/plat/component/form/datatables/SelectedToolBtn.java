package com.tt.plat.component.form.datatables;

import com.tt.plat.component.form.json.Json;
import org.json.JSONException;
import org.json.JSONWriter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by tangtao on 2014/8/19.
 * Desc:
 */
public class SelectedToolBtn extends PToolBtn{

    private List<ISelectedBtnCallBack> selectedBtnCallBacks = new ArrayList<>();

    private String success;

    private String error;

    private static String uid;

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

    public SelectedToolBtn(String sExtends, String sButtonText, List<ISelectedBtnCallBack> selectedBtnCallBacks) {
        super(sExtends, sButtonText);
        this.selectedBtnCallBacks = selectedBtnCallBacks;
    }

    public SelectedToolBtn(String sExtends, String sButtonText) {
        super(sExtends, sButtonText);
    }

    public SelectedToolBtn(String sExtends) {
        super(sExtends);
    }




    public void addCallBack(ISelectedBtnCallBack selectedBtnCallBack){

        selectedBtnCallBacks.add(selectedBtnCallBack);
    }

    public List<ISelectedBtnCallBack> getSelectedBtnCallBacks(){

        return selectedBtnCallBacks;
    }

    public String getUid() {
        if(uid==null){
            uid = UUID.randomUUID().toString();
        }
        return uid;
    }

    @Override
    public void toJson(JSONWriter writer) throws JSONException {
        writer.object();
        Json.writeObject(writer, "sExtends", getsExtends());
        Json.writeObject(writer, "sButtonText", getsButtonText());
        Json.writeObject(writer,"sAjaxUrl",getsAjaxUrl());
        Json.writeObject(writer,"sFieldSeperator",getsFieldSeperator());
        Json.writeObject(writer, "data", getAjaxData());
        Json.writeObject(writer,"tableMarkId",tableMarkId);
        Json.writeObject(writer,"mainProp",getMainProp());
        Json.writeObject(writer,"uid",getUid());
        if(getFnClick()!=null){
            Json.writeFunction(writer,"fnClick",getFnClick());
        }

        Json.writeFunction(writer,"success",success);
        Json.writeFunction(writer,"error",error);



         int[] mColumns = getmColumns();
        if(mColumns!=null){
            writer.key("mColumns");
            writer.array();
            for(int i=0;i<mColumns.length;i++){
                writer.value(mColumns[i]);
            }
            writer.endArray();
        }
        writer.endObject();
    }
}
