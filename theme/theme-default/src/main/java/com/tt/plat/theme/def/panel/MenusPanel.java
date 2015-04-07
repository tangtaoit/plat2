package com.tt.plat.theme.def.panel;


import com.tt.plat.core.web.api.IModule;
import com.tt.plat.core.web.api.Plat;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.repeater.RepeatingView;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: tangtao
 * Date: 14-1-23
 * Time: 下午6:33
 * To change this template use File | Settings | File Templates.
 */
public class MenusPanel extends Panel {


    public MenusPanel(String id) {
        super(id);


        /**
         * 获取当前系统的菜单模块
         */
        List<IModule> moduleList = Plat.getInstall().getCurrMenus();


        RepeatingView listItems = new RepeatingView("menusItems");

        //获取当前subject
       // Subject subject = SecurityUtils.getSubject();

        if (moduleList != null) {
            for (IModule module : moduleList) {

                String perminssionName =module.getPermissionName();

                if(perminssionName!=null&&!perminssionName.trim().equals("")){ //如果设置了权限
                  //  if (subject.isPermitted(module.getPermissionName())) {//模块是否有权限
                        listItems.add(new ItemPanel(listItems.newChildId(), module));
                   // }
                }else{ //如果没有设置权限 认为所有人都能访问
                    listItems.add(new ItemPanel(listItems.newChildId(), module));
                }

            }
        }

        add(listItems);

    }


}
