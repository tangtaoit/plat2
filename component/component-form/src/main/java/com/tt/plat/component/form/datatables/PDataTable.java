package com.tt.plat.component.form.datatables;

import com.tt.plat.component.form.datatables.column.AjaxBtnColumn;
import com.tt.plat.component.form.datatables.column.ICallBack;
import com.tt.plat.component.form.datatables.column.IPColumn;
import com.tt.plat.component.form.json.Json;
import com.tt.plat.component.form.select2.JQuery;
import org.apache.wicket.Application;
import org.apache.wicket.IResourceListener;
import org.apache.wicket.markup.head.CssHeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.markup.head.OnDomReadyHeaderItem;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.request.IRequestParameters;
import org.apache.wicket.request.Request;
import org.apache.wicket.request.http.WebResponse;
import org.json.JSONException;
import org.json.JSONWriter;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

/**
 * Created by tangtao on 2014/7/30.
 * Desc:
 */
public class PDataTable<T, S> extends WebMarkupContainer implements IResourceListener {
    private static final long serialVersionUID = 1L;

    protected PDataTableSetting settings = new PDataTableSetting();

    protected PDataProvider dataProvider;


    protected List<IPColumn> columns;


    /**
     * 显示主键
     */
    protected boolean showPrimary;

    public boolean isShowPrimary() {
        return showPrimary;
    }

    public void setShowPrimary(boolean showPrimary) {
        this.showPrimary = showPrimary;
    }

    /**
     * Constructor
     *
     * @param id           component id
     * @param columns      list of columns
     * @param dataProvider data provider
     */
    public PDataTable(final String id, final List<IPColumn> columns,
                      final PDataProvider dataProvider) {
        super(id);
        this.setOutputMarkupId(true);
        this.dataProvider = dataProvider;

        this.columns = columns;

        //addTopToolbar(new NavigationToolbar(this));
        // addTopToolbar(new HeadersToolbar<S>(this, dataProvider));
//        addBottomToolbar(new NoRecordsToolbar(this));


    }


    /**
     * 渲染表单头
     *
     * @param response
     */
    protected void renderThead(IHeaderResponse response) {

        response.render(OnDomReadyHeaderItem.forScript(JQuery.execute("$('#%s').html('%s');",
                getJquerySafeMarkupId(), "<thead><tr>" + getThs() + "</tr></thead>")));
    }

    protected String getThs() {
        StringBuffer stringBuffer = new StringBuffer();
        if (columns != null) {

            for (IPColumn column : columns) {
                String data = column.getData();
                if (data != null && !data.trim().equals("") && data.equals("id")) {
                    if (showPrimary) {//显示主键
                        stringBuffer.append("<th>").append(column.getShowName()).append("</th>");
                    }
                } else {
                    stringBuffer.append("<th>").append(column.getShowName()).append("</th>");
                }

            }
        }
        return stringBuffer.toString();
    }


    /**
     * 渲染javascript src
     *
     * @param response
     */
    protected void renderInitJsSrc(IHeaderResponse response) {

        response.render(JavaScriptHeaderItem.forReference(Application.get().getJavaScriptLibrarySettings()
                .getJQueryReference()));

        //  response.render(JavaScriptHeaderItem.forUrl("res/jquery-1.11.1.min.js"));

        response.render(CssHeaderItem.forUrl("res/melon/assets/css/plugins/jquery.dataTables.css"));

        response.render(CssHeaderItem.forUrl("res/melon/assets/css/plugins/dataTables.responsive.css"));

        response.render(JavaScriptHeaderItem.forUrl("res/melon/plugins/datatables/jquery.dataTables.min.js"));

        response.render(JavaScriptHeaderItem.forUrl("res/melon/plugins/datatables/dataTables.responsive.min.js"));
        if (getSettings().getTableTools() != null) {
            response.render(CssHeaderItem.forUrl("res/melon/assets/css/plugins/dataTables.tableTools.min.css"));
            response.render(JavaScriptHeaderItem.forUrl("res/melon/plugins/datatables/tabletools/dataTables.tableTools.min.js"));
            response.render(JavaScriptHeaderItem.forUrl("res/melon/plugins/datatables/tabletools/customBtn.js"));
        }

    }


    @Override
    public void renderHead(IHeaderResponse response) {

        super.renderHead(response);

        renderInitJsSrc(response);

        renderThead(response);

        outJs(response);


    }

    /**
     * 输出JS
     *
     * @param response
     */
    protected void outJs(IHeaderResponse response) {





        response.render(OnDomReadyHeaderItem.forScript(JQuery.execute("$('#%s').dataTable(%s);",
                getJquerySafeMarkupId(), settings.toJson())));

        if(columns!=null){
            for(IPColumn column:columns){
                if(column instanceof AjaxBtnColumn){
                    AjaxBtnColumn ajaxBtnColumn = (AjaxBtnColumn)column;

                     CharSequence url =ajaxBtnColumn.getAjax().getUrl();
                    if(url==null){
                        ajaxBtnColumn.getAjax().setUrl(settings.getAjax().getUrl());
                    }
                    response.render(OnDomReadyHeaderItem.forScript(JQuery.execute(ajaxBtnColumn.getJsFunc())));
                }
            }
        }

    }


    protected String getJquerySafeMarkupId() {
        return getMarkupId().replace(".", "\\\\.");
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();

        settings.tableMarkId = getJquerySafeMarkupId();

        AjaxSettings ajax = settings.getAjax(true);

        ColumnsSettings columnsSettings = settings.getColumns(true);
        columnsSettings.setShowPrimary(showPrimary);
        if (columns != null) {
            IPColumn[] pcolumns = new IPColumn[columns.size()];
            for (int i = 0; i < columns.size(); i++) {
                pcolumns[i] = columns.get(i);
            }

            columnsSettings.setColumns(pcolumns);
        }




//        ajax.setData(String
//                .format("function(term, page) { return { term: term, page:page, '%s':true, '%s':[window.location.protocol, '//', window.location.host, window.location.pathname].join('')}; }",
//                        WebRequest.PARAM_AJAX, WebRequest.PARAM_AJAX_BASE_URL));
    }

    @Override
    protected void onConfigure() {
        super.onConfigure();
        getSettings().setProcessing(true);
        getSettings().setServerSide(true);
        getSettings().getAjax().setUrl(urlFor(IResourceListener.INTERFACE, null));


        PTableTools tableTools  = settings.getTableTools();
        if(tableTools!=null){
            List<PToolBtn> pToolBtns = tableTools.getaButtons();
            if(pToolBtns!=null){
                for(PToolBtn toolBtn:pToolBtns){
                    if(toolBtn instanceof SelectedToolBtn){
                        SelectedToolBtn selectedToolBtn =(SelectedToolBtn)toolBtn;
                        if(toolBtn.getsAjaxUrl()==null){
                              toolBtn.setsAjaxUrl( getSettings().getAjax().getUrl().toString());


                        }
                        List<ISelectedBtnCallBack> selectedBtnCallBacks = selectedToolBtn.getSelectedBtnCallBacks();
                        if(selectedBtnCallBacks!=null){
                            for(ISelectedBtnCallBack selectedBtnCallBack:selectedBtnCallBacks){
                                selectedToolBtn.setSuccess(String.format("function(data){%s}",selectedBtnCallBack.getSuccessJs()));
                                selectedToolBtn.setError(String.format("function(data){%s}", selectedBtnCallBack.getErrorJs()));

                            }
                        }
                    }
                }
            }
        }
    }

    @Override
    public void onResourceRequested() {
        System.out.println("----");


        // this is the callback that retrieves matching choices used to populate the dropdown

        Request request = getRequestCycle().getRequest();
        IRequestParameters params = request.getRequestParameters();

        // retrieve choices matching the search term

        String action = params.getParameterValue("action").toString(null);

        if("selected".equals(action)){
            excuteSelected(params);
        }else{
            executeQuery(params);
        }




    }

    private void excuteSelected(IRequestParameters params) {


        String data = params.getParameterValue("data").toString("");

        String[] datas = data.split(",");

        String uid =params.getParameterValue("uid").toString("");

        IPColumn[] ipColumns = settings.getColumns().getColumns();

        for(IPColumn column:ipColumns){
            if(column instanceof AjaxBtnColumn){
                AjaxBtnColumn ajaxBtnColumn = (AjaxBtnColumn)column;

                if(uid.equals( ajaxBtnColumn.getUid())){
                    ICallBack callBack = ajaxBtnColumn.getCallBack();
                    if(callBack!=null){
                        callBack.toSelected(data);
                    }
                }

            }
        }

        List<PToolBtn> pToolBtns = settings.getTableTools().getaButtons();
        if (pToolBtns != null) {
            for (PToolBtn pToolBtn : pToolBtns) {
                if (pToolBtn instanceof SelectedToolBtn) {
                    SelectedToolBtn selectedToolBtn = (SelectedToolBtn) pToolBtn;
                    List<ISelectedBtnCallBack> selectedBtnCallBacks = selectedToolBtn.getSelectedBtnCallBacks();
                    if (selectedBtnCallBacks.size() > 0) {

                        if(uid.equals( selectedToolBtn.getUid())){
                            for (ISelectedBtnCallBack selectedBtnCallBack : selectedBtnCallBacks) {
                                selectedBtnCallBack.toSelected(Arrays.<Serializable>asList(datas));
                            }
                        }

                    }
                }
            }
        }


    }


    private void executeQuery(IRequestParameters params) {


        //查询关键字
        String search = params.getParameterValue("search[value]").toOptionalString();
        //排序列号
        int order = params.getParameterValue("order[0][column]").toInt();

        //排序方式 asc desc
        String orderDir = params.getParameterValue("order[0][dir]").toOptionalString();

        //开始数据
        int start = params.getParameterValue("start").toInt();

        //数据长度
        int length = params.getParameterValue("length").toInt();

        int draw = params.getParameterValue("draw").toInt();

        int pageNo = (start / length) + 1;


        PResponse<T> response = new PResponse<T>();
        dataProvider.query(search, pageNo, length, order, orderDir, response);


        OutputStreamWriter out = getOutStream();

        JSONWriter json = new JSONWriter(out);

        try {
            json.object();
            json.key("draw").value(draw);
            json.key("recordsTotal").value(response.getRecordsTotal());
            json.key("recordsFiltered").value(response.getRecordsTotal());
            json.key("data").array();
            for (T item : response) {
                json.object();
                boolean flag = dataProvider.toJson(item, json);
                if (!flag) {
                    printItemJson(json, item);
                }

                json.endObject();
            }
            json.endArray();
            json.endObject();
        } catch (JSONException e) {
            throw new RuntimeException("Could not write Json response", e);
        }
        try {
            out.flush();

        } catch (IOException e) {
            throw new RuntimeException("Could not write Json to servlet response", e);
        }
    }

    protected OutputStreamWriter getOutStream() {
        WebResponse webResponse = (WebResponse) getRequestCycle().getResponse();
        webResponse.setContentType("application/json");

        OutputStreamWriter out = new OutputStreamWriter(webResponse.getOutputStream(), getRequest().getCharset());


        return out;
    }



    protected void printItemJson(JSONWriter json, Object item) throws JSONException {
        for (IPColumn column : columns) {
            String data = column.getData();


            if ((data != null && !data.trim().equals(""))) {

                Object value = null;



                if (column.getParams() != null && column.getContent() != null) {
                    String[] params = column.getParams();
                    Object values[] = new Object[params.length];
                    for (int i = 0; i < params.length; i++) {
                        String key = params[i];
                        Object objValue = getFieldValue(item, params[i]);
                        if (objValue == null) {
                            values[i] = "";
                        } else {
                            values[i] = objValue;
                        }
                        column.getParamValueMap().put(key,objValue);
                    }


                    value = String.format(column.getContent(), values);
                }

                if (value == null) {
                    value = getFieldValue(item, column.getData());
                }
                if (value == null) {
                    value = "";
                }
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
    }

    protected Object getFieldValue(Object item, String fieldName) {
        Field field = null;
        try {
            field = getField(fieldName, item.getClass());
            if (field != null) {
                field.setAccessible(true);
                //
                return field.get(item);
            }
        } catch (IllegalAccessException e) {
            // e.printStackTrace();
        }
        return null;
    }

    private Field getField(String fieldName, Class cls) {
        try {
            Field field = cls.getDeclaredField(fieldName);
            field.setAccessible(true);
            return field;
        } catch (NoSuchFieldException e) {
            // e.printStackTrace();
            cls = cls.getSuperclass();
            if (cls == null) {
                return null;
            }
            return getField(fieldName, cls);
        }
    }


    public PDataProvider getDataProvider() {
        return dataProvider;
    }

    public void setDataProvider(PDataProvider dataProvider) {
        this.dataProvider = dataProvider;
    }

    public PDataTableSetting getSettings() {
        if (settings == null) {
            settings = new PDataTableSetting();
        }
        return settings;
    }


}

