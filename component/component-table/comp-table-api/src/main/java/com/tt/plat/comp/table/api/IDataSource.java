package com.tt.plat.comp.table.api;

import java.util.Iterator;

/**
 * Created by json on 2015-01-05.
 * table数据源
 */
public interface IDataSource<T> {

    /**
     * 关键字key
     */
    public static String KEYWORD="keyword";
    /**
     * 分页后的数据源
     * @param first 起始数据位置
     * @param count 每次请求数据量
     * @return
     */
    public Iterator<T> iterator(long first, long count);

    //public T model(T object);

    /**
     * 总数据量
     * @return
     */
    public long totalSize();

    /**
     * 添加条件数据
     * @param key
     * @param value
     */
    public void addConditionData(String key,Object value);


}
