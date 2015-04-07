package com.tt.plat.component.form.datatables.column;

import com.tt.plat.component.form.datatables.AjaxSettings;
import com.tt.plat.component.form.json.Json;
import org.json.JSONException;
import org.json.JSONStringer;
import org.json.JSONWriter;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Created by tangtao on 2014/8/20.
 * Desc:
 */
public class AjaxBtnColumn extends PColumn{

    private AjaxSettings ajax;



    private String name;


    private ICallBack callBack;

    private static String uid;

    public ICallBack getCallBack() {
        return callBack;
    }

    public void setCallBack(ICallBack callBack) {
        this.callBack = callBack;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public AjaxSettings getAjax() {
        if(ajax==null){
            ajax = new AjaxSettings();
        }
        return ajax;
    }

    public void setAjax(AjaxSettings ajax) {
        this.ajax = ajax;
    }


//    public String getContent(){
//        String[] params = getParams();
//        String datas="";
//        Map<String,String> paramMap = new HashMap<>();
//        JSONWriter jsonWriter = new JSONStringer();
//        if(params!=null){
//            try {
//                jsonWriter.object();
//                for(int i=0;i<params.length;i++){
//                    Json.writeObject(jsonWriter,"data"+i,"%s");
//
//                }
//                jsonWriter.endObject();
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//
//        }
//
//        return jsonWriter.toString();
//
//    }
//


    public String getUid() {
        if(uid==null){
            uid =  UUID.randomUUID().toString();
        }
        return uid;
    }

    @Override
    public String getContent() {

       String[] params = getParams();
        String datas="";
        if(params!=null){
            for(int i=0;i<params.length;i++){
                if(i==0){
                    datas+=" data=%s";
                }else{
                    datas+=" data"+i+"=%s";
                }

            }
        }

        return String.format("<input type='button' value='%s' class='%s'  %s />",name,getClassName(),datas);
    }

    public String getJsFunc(){

        setAjaxData();

        return String.format("$('.%s').live('click',function(){var obj=$(this);%s})",getClassName(),getAjaxJS());
    }

    public void setAjaxData(){

    }

    private String getAjaxJS(){

        JSONStringer writer = new JSONStringer();
        try {
            ajax.toJson(writer);
            return String.format("$.ajax(%s)",writer.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }


        return "";
    }
}
