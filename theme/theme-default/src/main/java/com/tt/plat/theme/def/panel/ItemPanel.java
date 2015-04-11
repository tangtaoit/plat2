//package com.tt.plat.theme.def.panel;
//
//import com.tt.plat.core.web.api.IModule;
//import com.tt.plat.core.web.api.IPageModule;
//import com.tt.plat.core.web.api.Plat;
//import org.apache.shiro.SecurityUtils;
//import org.apache.shiro.subject.Subject;
//import org.apache.wicket.markup.html.WebMarkupContainer;
//import org.apache.wicket.markup.html.basic.Label;
//import org.apache.wicket.markup.html.link.BookmarkablePageLink;
//import org.apache.wicket.markup.html.link.Link;
//import org.apache.wicket.markup.html.link.StatelessLink;
//import org.apache.wicket.markup.html.list.ListItem;
//import org.apache.wicket.markup.html.list.ListView;
//import org.apache.wicket.markup.html.panel.Panel;
//
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * Created by tao on 2015/4/5.
// */
//public class ItemPanel extends Panel {
//
//    /**
//     * 菜单容器
//     */
//    private WebMarkupContainer menusContainer = new WebMarkupContainer("menusContainer");
//
//    /**
//     * 子菜单容器
//     */
//    private WebMarkupContainer subMenusContainer = new WebMarkupContainer("submenusContainer");
//
//
//    public ItemPanel(String id, final IModule module) {
//        super(id);
//        menusContainer.setOutputMarkupId(true);
//
//        //菜单链接
//        Link menusLink = null;
//
//        //子菜单模块
//        List<IModule> submenus = null;
//
//        if (isDirectory(module)) { //是否是目录
//
//            /**
//             * 创建一个目录点击的A标签
//             */
//            menusLink = new StatelessLink("menus") {
//                @Override
//                public void onClick() {
//                }
//
//                protected CharSequence getURL() {
//
//                    return "javascript:void(0);";
//                }
//            };
//
//            /**
//             * 获取子菜单信息
//             */
//            //获取子菜单
//            submenus = Plat.getInstall().findModule(module.getFlag());
//
//            if (submenus != null) {
//                /**
//                 * 获取权限集合
//                 */
//               // submenus = getPermList(submenus);
//            }
//
//        } else {
//            //获取到当前组件
//            menusLink = new BookmarkablePageLink("menus", ((IPageModule)module).getPageClass());
//
//        }
//
//        Label iconLabel = new Label("name", " <i class=\"" + module.getIcon() + "\"></i>" + module.getName());
//        iconLabel.setEscapeModelStrings(false);
//        iconLabel.setRenderBodyOnly(true);
//        menusLink.add(iconLabel);
//        menusContainer.add(menusLink);
//
//        //菜单消息数量
//        Label msgNumLabel = new Label("msgNum", "");
//        msgNumLabel.setOutputMarkupId(true);
//        msgNumLabel.setVisible(false);//暂时隐藏
//        menusLink.add(msgNumLabel);
//
//
//        initSubmenus(submenus);
//
//        //控制子菜单显示与否
//        subMenusContainer.setVisible(submenus==null?false:(submenus.size()>0?true:false));
//
//      //  menusContainer.setVisible(submenus==null?false:(submenus.size()>0?true:false));
//        menusContainer.add(subMenusContainer);
//
//        add(menusContainer);
//
//    }
//
//    /**
//     * 初始化子菜单
//     * @param submenus
//     */
//    private void initSubmenus(List<IModule> submenus) {
//        //设置子菜单
//
//        ListView<IModule> links = new ListView<IModule>("menusItems", submenus) {
//
//
//            @Override
//            protected void populateItem(final ListItem<IModule> item) {
//                //  BookmarkablePageLink submenusLink = new BookmarkablePageLink("menus", ((IMenusModule)item.getModelObject()).getPageClass());
//                StatelessLink submenusLink = new StatelessLink("menus") {
//                    @Override
//                    public void onClick() {
//                        setResponsePage(((IPageModule)item.getModelObject()).getPageClass());
//                    }
//
//                };
//                submenusLink.setOutputMarkupId(true);
//                submenusLink.add(new Label("name", " <i class=\"" + item.getModelObject().getIcon() + "\"></i>" + item.getModelObject().getName()).setRenderBodyOnly(true).setEscapeModelStrings(false));
//                item.add(submenusLink);
//
//            }
//        };
//        links.setOutputMarkupId(true);
//
//        subMenusContainer.setVisible(submenus==null?false:(submenus.size()>0?true:false));
//        subMenusContainer.add(links);
//    }
//
//
//    /**
//     * 是否是目录
//     *
//     * @param module
//     * @return
//     */
//    private boolean isDirectory(IModule module) {
//
//        IPageModule pageModule = (IPageModule) module;
//        if (pageModule.getPageClass() == null) { //如果没有targets 那么认为是目录
//
//            return true;
//        }
//
//        return false;
//    }
//
//    /**
//     * 获取有权限的模块集合
//     *
//     * @param moduleList
//     * @return
//     */
//    private List<IModule> getPermList(List<IModule> moduleList) {
//        Subject subject = SecurityUtils.getSubject();
//        List<IModule> newModuleList = new ArrayList<>();
//        if (moduleList != null) {
//            for (IModule module : moduleList) {
//
//                //如果设置了权限信息
//                if (module.getPermissionName() != null && !module.getPermissionName().trim().equals("")) {
//                    if (subject.isPermitted(module.getPermissionName())) { //如果通过了权限信息 就加入List
//                        newModuleList.add(module);
//                    }
//                } else { //如果没设置权限信息 就认为都能看到
//                    newModuleList.add(module);
//                }
//            }
//        }
//        return newModuleList;
//    }
//}
