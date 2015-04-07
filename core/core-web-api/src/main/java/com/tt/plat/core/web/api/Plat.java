package com.tt.plat.core.web.api;

import org.apache.wicket.protocol.http.WebApplication;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tao on 2015/4/5.
 */
public class Plat {

    private static Plat install;


    private List<IModule> modules = new ArrayList<>();

    public static Plat getInstall(){
        if(install==null){

            install = new Plat();
        }

        return install;
    }

    private Plat(){

    }

    public void add(IModule module){
        modules.add(module);
    }

    public void remove(IModule module){

        modules.remove(module);
    }

    public List<IModule> findModule(String... flags){
        List<IModule> smodules = new ArrayList<IModule>();


        for(IModule module:modules){
            for(String flag : flags){

                String[] targets = module.getTargets();
                if(targets!=null&&targets.length>0){

                    for(String target:targets){

                        if(flag.equals( target)){
                            smodules.add(module);
                        }
                    }

                }

            }
        }
        return smodules;
    }

    /**
     * 获取当前菜单
     * @return
     */
    public List<IModule> getCurrMenus(){
       String applicationKey = WebApplication.get().getApplicationKey();

        return findModule(applicationKey);
    }


}
