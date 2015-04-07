package com.tt.plat.component.form.datatables;

/**
 * Created by tangtao on 2014/8/4.
 * Desc:
 */
public interface IPField {

    /**
     * 获取标签名
     * @return
     */
    public String getLabel();

    /**
     * 设置标签名
     * @return
     */
    public void setLabel(String label);

    /**
     * 设置对应字段名
     * @param name
     */
    public void setName(String name);

    /**
     * 获取对应字段名
     * @return
     */
    public String getName();

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
