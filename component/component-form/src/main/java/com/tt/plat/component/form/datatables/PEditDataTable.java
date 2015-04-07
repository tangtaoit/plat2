package com.tt.plat.component.form.datatables;

import com.tt.plat.component.form.datatables.column.IPColumn;
import com.tt.plat.component.form.json.Json;
import com.tt.plat.component.form.select2.JQuery;
import org.apache.wicket.markup.head.*;
import org.apache.wicket.request.IRequestParameters;
import org.apache.wicket.request.Request;
import org.apache.wicket.util.string.StringValue;
import org.json.JSONException;
import org.json.JSONWriter;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

/**
 * Created by tangtao on 2014/8/1.
 * Desc:
 */
public class PEditDataTable<T, S> extends PDataTable<T, S> {

    private PEditDataSetting editDataSetting = new PEditDataSetting();

    private Editor editor;

    private Delete delete;

    private IPCallback callback;

    public Editor getEditor() {
        if (editor == null) {
            editor = new Editor(getJquerySafeMarkupId());
            editor.setBtnTitle("更新");
            editor.setTitle("编辑");
        }
        return editor;
    }

    public Delete getDelete() {
        if (delete == null) {
            delete = new Delete(getJquerySafeMarkupId());
            delete.setBtnTitle("删除");
            delete.setTitle("删除");
            delete.setMessage("确定要执行删除操作吗？");
        }
        return delete;
    }

    public PEditDataSetting getEditDataSetting() {
        return editDataSetting;
    }

    public void setEditDataSetting(PEditDataSetting editDataSetting) {
        this.editDataSetting = editDataSetting;
    }

    /**
     * Constructor
     *
     * @param id           component id
     * @param columns      list of columns
     * @param dataProvider data provider
     */
    public PEditDataTable(String id, List<IPColumn> columns, PDataProvider dataProvider, IPCallback callback) {
        super(id, columns, dataProvider);

        this.callback = callback;
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();

        editDataSetting.setAjax(settings.getAjax());

        editDataSetting.setTable("#" + getJquerySafeMarkupId());

        FieldSettings fieldSettings = editDataSetting.getFieldSettings(true);

        if (fieldSettings.getIpFields()== null || fieldSettings.getIpFields().length <= 0) {
            if (columns != null) {
                List<IPField> ipFieldList = new ArrayList<>();
                for (int i = 0; i < columns.size(); i++) {
                    IPColumn column = columns.get(i);
                    String data =column.getData();
                    if(data!=null&&!data.trim().equals("")){
                        if(data.equals("id")){
                            if(showPrimary){
                                IPField field = new PField(column.getShowName(),column.getData());
                                ipFieldList.add(field);
                            }
                        }else{
                            IPField field = new PField(column.getShowName(),column.getData());
                            ipFieldList.add(field);
                        }

                    }

                }
                fieldSettings.setIpFields(listToArray(ipFieldList));
            }
        }


//        ajax.setData(String
//                .format("function(term, page) { return { term: term, page:page, '%s':true, '%s':[window.location.protocol, '//', window.location.host, window.location.pathname].join('')}; }",
//                        WebRequest.PARAM_AJAX, WebRequest.PARAM_AJAX_BASE_URL));
    }

    public IPField[] listToArray(List<IPField> ipFieldList){
        IPField[] ipFields = new IPField[ipFieldList.size()];
        if(ipFieldList!=null){
            for(int i=0;i<ipFieldList.size();i++){
                ipFields[i]=ipFieldList.get(i);
            }
        }
        return ipFields;
    }

    private Object renderValue(IPColumn column,Object item){
        Object value = null;

        if(column.getParams()!=null&&column.getContent()!=null){
            String[] params = column.getParams();
            Object values[]  =new Object[params.length];
            for(int i=0;i<params.length;i++){
                Object objValue = getFieldValue(item,params[i]);
                if(objValue==null){
                    values[i]="";
                }else{
                    values[i]=objValue;
                }

            }
            value = String.format(column.getContent(),values);
        }

        if(value==null){
            value =getFieldValue(item, column.getData());
        }
        if(value==null){
            value="";
        }

        return value;
    }

    protected void printItemJson(JSONWriter json, Object item) throws JSONException {


        IPField[] fields = editDataSetting.getFieldSettings().getIpFields();
        Set<String> fieldNameS = new HashSet<>();
        if(fields!=null&&fields.length>0){
            for(IPField field:fields){
                fieldNameS.add(field.getName());
            }
        }

        for (IPColumn column : columns) {
            String data = column.getData();

            if(fieldNameS.contains(data)){
                fieldNameS.remove(data);
            }
            if ((data != null && !data.trim().equals(""))) {

                Object value = renderValue(column,item);
                if (data.equals("id")) {
                    Json.writeObject(json, "DT_RowId", value);
                    if (showPrimary) {//显示主键
                        Json.writeObject(json, column.getData(), value);
                    }
                } else {
                    Json.writeObject(json, column.getData(), value);
                }

            }
        }
        for(String colName:fieldNameS){

            Json.writeObject(json, colName, getFieldValue(item,colName));
        }
    }

    protected void renderInitJsSrc(IHeaderResponse response) {
        super.renderInitJsSrc(response);

        response.render(CssHeaderItem.forUrl("res/melon/assets/css/plugins/dataTables.editor.min.css"));

        response.render(JavaScriptHeaderItem.forUrl("res/melon/plugins/datatables/dataTables.editor.js"));
    }


    protected void newEditor(IHeaderResponse response) {

        //response.render(JavaScriptContentHeaderItem.forScript("var editor_" + getJquerySafeMarkupId() + ";", null));

        response.render(OnDomReadyHeaderItem.forScript(JQuery.runJs("var editor_%s=new $.fn.dataTable.Editor(%s)",
                getJquerySafeMarkupId(), editDataSetting.toJson())));

        //response.render(JavaScriptContentHeaderItem.forScript("var editor_"+getJquerySafeMarkupId()+";",null));
    }


    protected void outJs(IHeaderResponse response) {

        super.outJs(response);

        newEditor(response);

//        response.render(OnDomReadyHeaderItem.forScript(JQuery.runJs("editor_%s=new $.fn.dataTable.Editor(%s)",
//                getJquerySafeMarkupId(), "{\n" +
//                        "        \"ajax\": \"../php/staff.php\",\n" +
//                        "        \"table\": \"#"+getJquerySafeMarkupId()+"\",\n" +
//                        "        \"fields\": [ {\n" +
//                        "                \"label\": \"First name:\",\n" +
//                        "                \"name\": \"id\"\n" +
//                        "            }, {\n" +
//                        "                \"label\": \"Last name:\",\n" +
//                        "                \"name\": \"name\"\n" +
//                        "            }, {\n" +
//                        "                \"label\": \"Position:\",\n" +
//                        "                \"name\": \"loginName\"\n" +
//                        "            }\n" +
//                        "        ]\n" +
//                        "    } ")));


        response.render(OnDomReadyHeaderItem.forScript(getEditor().toJquery()));

        response.render(OnDomReadyHeaderItem.forScript(getDelete().toJquery()));


    }

    @Override
    public void onResourceRequested() {
        Request request = getRequestCycle().getRequest();
        IRequestParameters params = request.getRequestParameters();

        String action = params.getParameterValue("action").toString("query");
        if ("query".equals(action)) {
            super.onResourceRequested();
        } else if ("remove".equals(action)) {
            executeDelete(params);
        } else if ("edit".equals(action)) {
            executeUpdate(params);
        }
    }

    private void executeUpdate(IRequestParameters params) {


        String id = params.getParameterValue("id").toString(null);
        if (id != null) {
            if (callback instanceof IPEditORMCallback) {
                IPEditORMCallback ipEditORMCallback = ((IPEditORMCallback) callback);

                Object entity = ipEditORMCallback.query(id);

                Field[] fields = entity.getClass().getDeclaredFields();
                if (fields != null) {
                    for (Field field : fields) {
                        field.setAccessible(true);
                        String name = field.getName();
                        String typeName = field.getType().getName();
                        try {
                            Object value = valueToTypeV(typeName, name, params);
                            if(value!=null){
                               Method method = entity.getClass().getMethod("set" + firstToUpperCase(name), field.getType());
                                method.invoke(entity,value);
                               // field.set(entities, value);
                            }
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        } catch (NoSuchMethodException e) {
                            e.printStackTrace();
                        } catch (InvocationTargetException e) {
                            e.printStackTrace();
                        }
                    }
                }

               boolean isUpdateSuccess = ipEditORMCallback.update(entity);

                if(isUpdateSuccess){
                    outEntity(entity);
                }
            }
        }

    }

    private String firstToUpperCase(String str){

        return str.substring(0,1).toUpperCase()+str.substring(1,str.length());
    }



    private void outEntity(Object entity){
        OutputStreamWriter outputStreamWriter = getOutStream();
        JSONWriter jsonWriter = new JSONWriter(outputStreamWriter);

        try {
            jsonWriter.object();
            jsonWriter.key("row");
            jsonWriter.object();
            printItemJson(jsonWriter, entity);
            jsonWriter.endObject();
            jsonWriter.endObject();
        } catch (JSONException e) {
            e.printStackTrace();
        }finally {
            try {
                outputStreamWriter.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private Object valueToTypeV(String typeName, String fieldName, IRequestParameters params) {

        StringValue stringValue = params.getParameterValue("data["+fieldName+"]");

        if(stringValue.isEmpty()){

            return null;
        }

        if ("java.lang.String".equals(typeName)) {
            return stringValue.toString();
        }

        if ("java.lang.Long".equals(typeName)) {

            return stringValue.toLong();
        }
        if ("java.util.Date".equals(typeName)) {

            return stringValue.toTime().toDate();
        }

        if ("java.lang.Integer".equals(typeName)) {

            return stringValue.toTime().toDate();
        }

        return null;
    }

    private void executeDelete(IRequestParameters params) {
        String id = params.getParameterValue("id[]").toString(null);

        if (callback instanceof IPEditORMCallback) {
          boolean isDelete = ((IPEditORMCallback) callback).delete(id);

            if(isDelete){
                OutputStreamWriter outputStreamWriter = getOutStream();
                JSONWriter jsonWriter = new JSONWriter(outputStreamWriter);
                try {
                    jsonWriter.array();
                    jsonWriter.endArray();
                } catch (JSONException e) {
                    e.printStackTrace();
                }finally {
                    try {
                        outputStreamWriter.flush();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            }
        }

    }
}

class Editor {

    private String tableMarkupId;

    Editor(String tableMarkupId) {
        this.tableMarkupId = tableMarkupId;
    }

    //面板标题
    private String title;

    //按钮标题
    private String btnTitle;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBtnTitle() {
        return btnTitle;
    }

    public void setBtnTitle(String btnTitle) {
        this.btnTitle = btnTitle;
    }

    public String toJquery() {
        return "  $('#" + tableMarkupId + "').on( 'click', 'a.editor_edit', function (e) {\n" +
                "        e.preventDefault();\n" +
                " \n" +
                "        editor_" + tableMarkupId + "\n" +
                "            .title( '" + title + "' )\n" +
                "            .buttons( { \"label\": \"" + btnTitle + "\", \"fn\": function () { editor_" + tableMarkupId + ".submit() } } )\n" +
                "            .edit( $(this).closest('tr') );\n" +
                "    } );";
    }
}

class Delete {

    private String tableMarkupId;

    //按钮标题
    private String btnTitle;

    private String message;

    Delete(String tableMarkupId) {
        this.tableMarkupId = tableMarkupId;
    }

    //面板标题
    private String title;


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getBtnTitle() {
        return btnTitle;
    }

    public void setBtnTitle(String btnTitle) {
        this.btnTitle = btnTitle;
    }

    public String toJquery() {
        return " $('#" + tableMarkupId + "').on( 'click', 'a.editor_remove', function (e) {\n" +
                "        editor_" + tableMarkupId + "\n" +
                "            .title( '" + title + "' )\n" +
                "            .message( '" + message + "' )\n" +
                "            .buttons( { \"label\": \"" + btnTitle + "\", \"fn\": function () { editor_" + tableMarkupId + ".submit() } } )\n" +
                "            .remove( $(this).closest('tr') );\n" +
                "    } );";
    }

}
