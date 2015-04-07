package com.tt.plat.comp.table.api;

import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;

import java.util.List;

/**
 * Created by json on 2015-01-05.
 * table数据提供接口
 */
public interface ITableSource<T> {


    /**
     * 获取页大小
     * @return
     */
    public int getPageSize();
    /**
     * 获取列信息
     * @return
     */
    public List<IPColumn> getColumnList();

    /**
     * 获取数据源
     * @return
     */
    public IDataSource<T> getDataSource();

    /**
     *获取到条件组件
     * @return
     */
    public Component getCondition(String componentId);

    /**
     * 设置table刷新回调方法
     * @param tableRefreshCallback
     */
    public void setTableRefreshCallback(ITableRefreshCallback tableRefreshCallback);
}

