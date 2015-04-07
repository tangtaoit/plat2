package com.tt.plat.component.form.datatables;

import java.util.Map;

/**
 * Created by tangtao on 2014/8/4.
 * Desc:
 */
public interface IPEditORMCallback<T> extends IPCallback{

    /**
     * 删除
     * @param id
     */
    public boolean delete(String id);


    /**
     * 查询单个
     * @param id
     * @return
     */
    public T query(String id);

    /**
     * 更新
     * @param t
     * @return
     */
    public boolean update(T t);
}
