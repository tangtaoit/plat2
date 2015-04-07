package com.tt.plat.component.form.datatables;


import java.util.Map;

/**
 * Created by tangtao on 2014/8/4.
 * Desc:
 */
public interface IPEditCallback<T> extends IPCallback {

    /**
     * 删除
     * @param id
     */
    public void delete(String id);

    /**
     * 更新
     * @param valueMap
     * @return
     */
    public T update(Map<String,Object> valueMap);




}
