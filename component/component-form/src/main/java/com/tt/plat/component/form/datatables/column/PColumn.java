package com.tt.plat.component.form.datatables.column;

import com.tt.plat.component.form.datatables.IpOpts;
import com.tt.plat.component.form.datatables.column.IPColumn;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by tangtao on 2014/7/31.
 * Desc:
 */
public class PColumn implements IPColumn {


    private boolean isShow;

    private String data;


    private boolean orderable;

    private String showName;

    private String defaultContent;

    private String className;

    private String content;

    private String[] params;


    /**
     * 对应参数值
     */
    private Map<String,Object> paramValueMap = new HashMap<>();

    /**
     * 控件类型
     */
    private String type;

    /**
     * 参数映射
     */
    private IpOpts[] ipOpts;

    /**
     * 默认值
     */
    private String def;


    public PColumn(String data, boolean orderable, String showName) {
        this.data = data;
        this.orderable = orderable;
        this.showName = showName;
    }

    public PColumn(String data, String showName) {
        this.data = data;
        this.showName = showName;
    }

    public PColumn(String data, boolean orderable) {
        this.data = data;
        this.orderable = orderable;
    }

    public PColumn(){

    }


    public String getData() {
        return data;
    }



    public void setData(String data) {
        this.data = data;
    }

    public boolean isOrderable() {
        return orderable;
    }

    public void setOrderable(boolean orderable) {
        this.orderable = orderable;
    }

    public String getShowName() {
        return showName;
    }

    public void setShowName(String showName) {
        this.showName = showName;
    }

    public String getDefaultContent() {
        return defaultContent;
    }

    public void setDefaultContent(String defaultContent) {
        this.defaultContent = defaultContent;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String[] getParams() {
        return params;
    }

    public void setParams(String[] params) {
        this.params = params;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public IpOpts[] getIpOpts() {
        return ipOpts;
    }

    public void setIpOpts(IpOpts[] ipOpts) {
        this.ipOpts = ipOpts;
    }

    public String getDef() {
        return def;
    }

    public void setDef(String def) {
        this.def = def;
    }

    public boolean isShow() {
        return isShow;
    }

    public void setShow(boolean isShow) {
        this.isShow = isShow;
    }

    public Map<String, Object> getParamValueMap() {
        return paramValueMap;
    }

    public <T> T getParamValue(String key){

        return (T)paramValueMap.get(key);
    }

    public void setParamValueMap(Map<String, Object> paramValueMap) {
        this.paramValueMap = paramValueMap;
    }
}
