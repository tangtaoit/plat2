package com.tt.plat.theme.def.page;

import com.tt.plat.theme.def.NavomaticBorder;
import com.tt.plat.theme.def.panel.HeaderPanel;
import com.tt.plat.theme.def.panel.MenusPanel;
import com.tt.plat.theme.def.panel.PBreadCrumbBar;
import org.apache.wicket.MarkupContainer;
import org.apache.wicket.extensions.breadcrumb.BreadCrumbBar;
import org.apache.wicket.markup.head.CssHeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.border.Border;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.resource.JQueryResourceReference;

import java.io.Serializable;

/**
 * Created by tao on 2015/3/29.
 */
public abstract class BasePage extends WebPage implements Serializable {

    /**
     * 页面标题
     */
    private String pageTitle = "(no title)";


    //bar
    private BreadCrumbBar breadCrumbBar;




    public BasePage(){
        breadCrumbBar = new PBreadCrumbBar("breadCrumbBar");
        add(breadCrumbBar);

        add(new Label("title", new PropertyModel<String>(this, "pageTitle")));

        add(new HeaderPanel("header").setRenderBodyOnly(true));

        add(new MenusPanel("menus").setRenderBodyOnly(true));

//        navomaticBorder =new NavomaticBorder("plat");
//        super.add(navomaticBorder);


    }


    @Override
    public void renderHead(IHeaderResponse response) {
       // super.renderHead(response);

        for (String url : getCssResources()) {
            response.render(CssHeaderItem.forUrl(url));

        }




       // response.render(JavaScriptHeaderItem.forReference(JQueryResourceReference.get()));


        for (String url : getJsResources()) {
            response.render(JavaScriptHeaderItem.forUrl(url));

        }

        response.render(JavaScriptHeaderItem.forScript("$(document).ready(function () {\n" +
                "        App.init();\n" +
                "        Plugins.init();\n" +
                "        FormComponents.init()\n" +
                "    }); var dateSelected;", ""));
    }

    public BreadCrumbBar getBreadCrumbBar() {
        return breadCrumbBar;
    }

    public String getPageTitle() {
        return pageTitle;
    }

    public void setPageTitle(String pageTitle) {
        this.pageTitle = pageTitle;
    }



    private String[] getCssResources() {

        return new String[]{"/res/css/cal.css",
                "/res/melon/bootstrap/css/bootstrap.min.css",
                "/res/melon/plugins/jquery-ui/jquery.ui.1.10.2.ie.css",
                "/res/melon/assets/css/main.css",
                "/res/melon/assets/css/plugins.css",
                "/res/melon/assets/css/responsive.css",
                "/res/melon/assets/css/icons.css",
                "/res/melon/assets/css/fontawesome/font-awesome.min.css",
                "/res/melon/assets/css/fontawesome/font-awesome-ie7.min.css",
                "/res/melon/assets/css/ie8.css",
                "/res/kendo/kendo.common.min.css",
                "/res/kendo/kendo.default.min.css"};
    }

    private String[] getJsResources() {

        return new String[]{
                "/res/jquery-1.11.1.min.js",
                "/res/melon/plugins/jquery-ui/jquery-ui-1.10.2.custom.min.js",
                "/res/melon/bootstrap/js/bootstrap.min.js",
                "/res/melon/plugins/touchpunch/jquery.ui.touch-punch.min.js",
                "/res/melon/plugins/event.swipe/jquery.event.move.js",
                "/res/melon/plugins/event.swipe/jquery.event.swipe.js",
                "/res/melon/assets/js/libs/breakpoints.js",
                "/res/melon/plugins/respond/respond.min.js",
                "/res/melon/plugins/cookie/jquery.cookie.min.js",
                "/res/melon/plugins/slimscroll/jquery.slimscroll.min.js",
                "/res/melon/plugins/slimscroll/jquery.slimscroll.horizontal.min.js",
                "/res/melon/plugins/noty/jquery.noty.js",
                "/res/melon/plugins/noty/layouts/top.js",
                "/res/melon/plugins/noty/themes/default.js",
                "/res/melon/assets/js/app.js",
                "/res/melon/assets/js/plugins.js",
                "/res/melon/assets/js/plugins.form-components.js",
                "/res/melon/plugins/bootstrap-inputmask/jquery.inputmask.min.js"};
    }
}
