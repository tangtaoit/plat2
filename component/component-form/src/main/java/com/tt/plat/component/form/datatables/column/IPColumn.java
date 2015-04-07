package com.tt.plat.component.form.datatables.column;

import com.tt.plat.component.form.datatables.IpOpts;

import java.util.Map;

/**
 * Created by tangtao on 2014/7/31.
 * Desc:列
 */
public interface IPColumn {



    /**
     * 列名
     * @return
     */
    public String getData();


    /**
     * 默认列的内容
     * @return
     */
    public String getDefaultContent();

    /**
     * HTML元素的className
     * @return
     */
    public String getClassName();

    /**
     * 是否排序
     * @return
     */
    public boolean isOrderable();

    /**
     * 获取显示的名称
     * @return
     */
    public String getShowName();


    /**
     * 参数列 与content属性对应
     * @return
     */
    public String[] getParams();

    /**
     * 获取参数值的MAP
     * @return
     */
    public Map<String, Object> getParamValueMap();

    /**
     * 参数列 与content属性对应
     * @return
     */
    public void setParams(String... params);

    /**
     * 获取cell的内容如果为空则为列的内容
     * @return
     */
    public String getContent();

    /**
     * 设置cell的内容如果为空则为列的内容
     * @return
     */
    public void setContent(String content);

    /**
     * 获取参数映射
     * @return
     */
    public IpOpts[] getIpOpts();

    /**
     * 设置参数映射
     * @param ipOpts
     */
    public void setIpOpts(IpOpts... ipOpts);

    /**
     * 设置默认值
     * @param def
     */
    public void setDef(String def);

    /**
     * 获取默认值
     * @return
     */
    public String getDef();

    /**
     * 设置域控件类型
     * @param type
     */
    public void setType(String type);

    /**
     * 获取域控件类型
     * @return
     */
    public String getType();




}
