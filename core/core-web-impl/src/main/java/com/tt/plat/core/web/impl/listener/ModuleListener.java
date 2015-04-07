package com.tt.plat.core.web.impl.listener;

import com.tt.plat.core.web.api.IModule;
import com.tt.plat.core.web.api.Plat;

/**
 * Created by tao on 2015/4/5.
 * 模块监听者
 */
public class ModuleListener {

    public void bind(IModule module){
        Plat.getInstall().add(module);
    }

    public void unbind(IModule module){
        Plat.getInstall().remove(module);
    }
}
