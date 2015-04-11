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

    public DefaultModule(){

    }


    public DefaultModule(String name, String flag) {
        this.name = name;
        this.flag = flag;
    }

    public DefaultModule(String name) {
        this.name = name;
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DefaultModule that = (DefaultModule) o;

        if (flag != null ? !flag.equals(that.flag) : that.flag != null) return false;
        if (no != null ? !no.equals(that.no) : that.no != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = no != null ? no.hashCode() : 0;
        result = 31 * result + (flag != null ? flag.hashCode() : 0);
        return result;
    }
}
