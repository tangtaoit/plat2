package com.tt.plat.component.form.datatables;

import java.io.Serializable;
import java.util.List;

/**
 * Created by tangtao on 2014/8/19.
 * Desc:
 */
public interface ISelectedBtnCallBack<T> {

    /**
     * 被选中的数据
     * @param ids
     */
    public void toSelected(List<T> ids);


    /**
     * 获取执行成功的JS
     * @return
     */
    public String getSuccessJs();

    /**
     * 获取执行失败的JS
     * @return
     */
    public String getErrorJs();
}
