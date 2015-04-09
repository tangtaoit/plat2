package com.tt.plat.core.web.api;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tao on 2015/4/9.
 */
public class ModuleNode {

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
        return childs;
    }

    public void setChilds(List<ModuleNode> childs) {
        this.childs = childs;
    }

    public void addChild(ModuleNode moduleNode){

        this.childs.add(moduleNode);
    }
}
