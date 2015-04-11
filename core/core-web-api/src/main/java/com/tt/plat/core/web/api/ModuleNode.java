package com.tt.plat.core.web.api;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by tao on 2015/4/9.
 */
public class ModuleNode implements Serializable{

    private IModule module;

    private ModuleNode parent;


    private List<ModuleNode> childs = new ArrayList<>();

    public IModule getModule() {
        return module;
    }

    public void setModule(IModule module) {
        this.module = module;
    }

    public ModuleNode getParent() {
        return parent;
    }

    public void setParent(ModuleNode parent) {
        this.parent = parent;
    }

    public List<ModuleNode> getChilds() {
        return Collections.unmodifiableList(childs);
    }


    public void addChild(ModuleNode moduleNode){

        this.childs.add(moduleNode);
    }

    @Override
    public String toString() {

        return module.getName();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ModuleNode that = (ModuleNode) o;

        if (module != null ? !module.equals(that.module) : that.module != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return module != null ? module.hashCode() : 0;
    }
}
