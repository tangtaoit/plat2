package com.tt.plat.core.web.api;

import org.apache.wicket.Component;

/**
 * Created with IntelliJ IDEA.
 * User: tangtao
 * Date: 14-2-17
 * Time: 下午2:23
 * 模块接口
 */
public interface IModule {
    /**
     * 模块名称
     * @return
     */
    String getName();

    /**
     * 模块图标
     * @return
     */
    String getIcon();

    /**
     * 模块标记
     * @return
     */
    String getFlag();



    /**
     * 获取目标模块
     * @return
     */
    public String[] getTargets();


    /**
     * 获取权限名
     * @return
     */
    public String getPermissionName();
}
