package com.tt.plat.component.form.datatables;

import com.tt.plat.component.form.json.Json;
import org.json.JSONException;
import org.json.JSONWriter;

/**
 * Created by tangtao on 2014/8/18.
 * Desc:
 */
public class PToolBtn {

    /**
     * 按钮集成于
     */
    private String sExtends;

    /**
     * 按钮文本
     */
    private String sButtonText;

   private String sAjaxUrl;

    private String ajaxData;

    /**
     * 要处理的列
     */
    private int[] mColumns;

    private String mainProp;

    /**
     *拼接线
     */
    private String sFieldSeperator;

    private String fnClick;

    protected String tableMarkId;

    public PToolBtn(){

    }
    public PToolBtn(String sExtends, String sButtonText) {
        this.sExtends = sExtends;
        this.sButtonText = sButtonText;
    }

    public String getTableMarkId() {
        return tableMarkId;
    }

    public void setTableMarkId(String tableMarkId) {
        this.tableMarkId = tableMarkId;
    }

    public int[] getmColumns() {
        return mColumns;
    }



    public void setmColumns(int[] mColumns) {
        this.mColumns = mColumns;
    }

    public PToolBtn(String sExtends) {
        this.sExtends = sExtends;
    }

    public String getAjaxData() {
        return ajaxData;
    }

    public void setAjaxData(String ajaxData) {
        this.ajaxData = ajaxData;
    }

    public String getsExtends() {
        return sExtends;
    }

    public void setsExtends(String sExtends) {
        this.sExtends = sExtends;
    }

    public String getsButtonText() {
        return sButtonText;
    }

    public void setsButtonText(String sButtonText) {
        this.sButtonText = sButtonText;
    }


    public String getsFieldSeperator() {
        return sFieldSeperator;
    }

    public String getFnClick() {
        return fnClick;
    }

    public void setFnClick(String fnClick) {
        this.fnClick = fnClick;
    }

    public void setsFieldSeperator(String sFieldSeperator) {
        this.sFieldSeperator = sFieldSeperator;
    }

    public String getsAjaxUrl() {
        return sAjaxUrl;
    }

    public void setsAjaxUrl(String sAjaxUrl) {
        this.sAjaxUrl = sAjaxUrl;
    }

    public String getMainProp() {
        return mainProp;
    }

    public void setMainProp(String mainProp) {
        this.mainProp = mainProp;
    }

    public void toJson(JSONWriter writer) throws JSONException {
        writer.object();
        Json.writeObject(writer, "sExtends", sExtends);
        Json.writeObject(writer, "sButtonText", sButtonText);
        Json.writeObject(writer,"sAjaxUrl",sAjaxUrl);
        Json.writeObject(writer,"sFieldSeperator",sFieldSeperator);
        Json.writeObject(writer, "data", ajaxData);
        Json.writeObject(writer,"tableMarkId",tableMarkId);
        Json.writeObject(writer,"mainProp",mainProp);
        if(fnClick!=null){
            Json.writeFunction(writer,"fnClick",fnClick);
        }



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
