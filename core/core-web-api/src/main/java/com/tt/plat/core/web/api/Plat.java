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

    private static ModuleNode moduleNode = null;

    public static Plat getInstall() {
        if (install == null) {

            /**
             * 设置一个根父类节点
             */
            moduleNode = new ModuleNode();
            ModuleNode childModuleNode = new ModuleNode();
            childModuleNode.setModule(new DefaultModule("plat", "plat.snk.test"));
            moduleNode.addChild(childModuleNode);

            install = new Plat();
        }

        return install;
    }

    private Plat() {

    }

    public void add(IModule module) {
        modules.add(module);

        set2(moduleNode);
    }

    public void remove(IModule module) {

        modules.remove(module);
    }


    private void set2(ModuleNode moduleNode){

       List<ModuleNode> moduleNodes =    moduleNode.getChilds();

        if(moduleNodes!=null&&moduleNodes.size()>0){
            for(ModuleNode md:moduleNodes){
                set(md);

                set2(md);
            }
        }
    }

    private void set(ModuleNode moduleNode) {

        IModule module = moduleNode.getModule();

        /**
         * 获取到target
         */
        String flag = module.getFlag();
        if (flag != null) {
            List<IModule> tempModuleList = new ArrayList<>();
            tempModuleList.addAll(modules);
                for (IModule m : tempModuleList) {
                    String[] targets = m.getTargets();
                    for(String target:targets){
                        if (flag.equals(target)) {//找到目标节点

                            modules.remove(m);
                            //创建子节点
                            ModuleNode cnode = new ModuleNode();
                            cnode.setModule(m);
                            //设置父节点
                            cnode.setParent(moduleNode);
                            //添加子节点
                            moduleNode.addChild(cnode);
                            //继续搜索子节点的目标节点
                            set(cnode);
                        }
                    }

                }
        }

    }


    public ModuleNode getModuleNodeByFlag(String flag){

        List<ModuleNode> moduleNodes =  moduleNode.getChilds();
        if(moduleNodes!=null&&moduleNodes.size()>0){


            return findModuleNode(moduleNodes.get(0).getChilds(),flag);
        }
        return null;
    }

    public ModuleNode findModuleNode(List<ModuleNode> moduleNodes,String flag){

        if(moduleNodes!=null&&moduleNodes.size()>0){
           for(ModuleNode node:moduleNodes){

               IModule md = node.getModule();
               String cflag = md.getFlag();
               if(flag.equals(cflag)){

                   return node;
               }

               ModuleNode temp = findModuleNode(node.getChilds(), flag);
               if (temp != null)
               {
                   return temp;
               }

           }
        }

        return null;
    }

    /**
     * 获取当前菜单
     *
     * @return
     */
    public ModuleNode getCurrMenus() {
      //  String applicationKey = WebApplication.get().getApplicationKey();

       List<ModuleNode> moduleNodes =  moduleNode.getChilds();
        if(moduleNodes!=null&&moduleNodes.size()>0){

            return moduleNodes.get(0);
        }
        return null;
    }


}
