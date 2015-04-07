package com.tt.plat.core.web.api;

import org.apache.wicket.Page;

/**
 * Created by tao on 2015/4/5.
 */
public class DefaultModule implements IPageModule{

    private String name;


    private String icon;

    private String no;


    private String flag;

    //权限名称
    private String permissionName;


    private String target;

    private String[] targets;

    private Class<? extends Page> pageClass;

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getIcon() {
        return icon;
    }


    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }


    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    @Override
    public String[] getTargets() {

        if(target!=null){
            return new String[]{target};
        }
        return targets;
    }

    public void setTargets(String[] targets) {
        this.targets = targets;
    }

    @Override
    public String getPermissionName() {
        return permissionName;
    }

    public void setPermissionName(String permissionName) {
        this.permissionName = permissionName;
    }

    public void setPageClass(Class<? extends Page> pageClass) {
        this.pageClass = pageClass;
    }

    @Override
    public Class<? extends Page> getPageClass() {

        return pageClass;
    }
}
