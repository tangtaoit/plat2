package com.tt.plat.component.form.datatables;

import org.apache.wicket.model.IDetachable;
import org.json.JSONException;
import org.json.JSONWriter;

import java.util.Collection;

/**
 * Created by tangtao on 2014/7/31.
 * Desc:
 */
public abstract class PDataProvider<T> implements IDetachable {


    /**
     * Queries application for choices that match the search {@code term} and adds them to the {@code response}
     *
     * @param term
     *            search term
     * @param pageNo
     *            requested search term results page
     *@param pageSize
     *          页大小
     *@param order
     *            排序列
     *@param orderDir
     *            排序方式
     *@param response
     *            aggregate for matching choices as well as other response options

     */
    public abstract void query(String term, int pageNo,int pageSize,int order,String orderDir, PResponse<T> response);


    public  boolean toJson(T choice, JSONWriter writer) throws JSONException{
        return false;
    }




    @Override
    public void detach() {

    }
}
