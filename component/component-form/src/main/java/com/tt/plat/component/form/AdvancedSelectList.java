package com.tt.plat.component.form;

import org.apache.wicket.markup.head.CssHeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.markup.head.JavaScriptUrlReferenceHeaderItem;
import org.apache.wicket.markup.html.internal.HtmlHeaderContainer;
import org.apache.wicket.markup.html.panel.Panel;

/**
 * Created by tangtao on 2014/7/24.
 * Desc:
 */
public class AdvancedSelectList extends Panel {

    public AdvancedSelectList(String id) {
        super(id);
        setOutputMarkupId(true);
    }

    @Override
    public void renderHead(IHeaderResponse response) {
        super.renderHead(response);


        response.render(CssHeaderItem.forUrl("res/melon/bootstrap/css/bootstrap.min.css"));

        response.render(CssHeaderItem.forUrl("res/melon/assets/css/main.css"));

        response.render(CssHeaderItem.forUrl("res/melon/assets/css/plugins.css"));

        response.render(CssHeaderItem.forUrl("res/melon/assets/css/responsive.css"));

        response.render(CssHeaderItem.forUrl("res/melon/assets/css/icons.css"));

        response.render( JavaScriptHeaderItem.forUrl("res/jquery-1.10.2.min.js"));

        response.render( JavaScriptHeaderItem.forUrl("res/melon/plugins/jquery-ui/jquery-ui-1.10.2.custom.min.js"));
        response.render( JavaScriptHeaderItem.forUrl("res/melon/plugins/uniform/jquery.uniform.min.js"));
        response.render( JavaScriptHeaderItem.forUrl("res/melon/assets/js/libs/breakpoints.js"));

        response.render( JavaScriptHeaderItem.forUrl("res/melon/bootstrap/js/bootstrap.min.js"));
        response.render( JavaScriptHeaderItem.forUrl("res/melon/assets/js/libs/lodash.compat.min.js"));
        response.render( JavaScriptHeaderItem.forUrl("res/melon/plugins/touchpunch/jquery.ui.touch-punch.min.js"));
        response.render( JavaScriptHeaderItem.forUrl("res/melon/plugins/event.swipe/jquery.event.move.js"));
        response.render( JavaScriptHeaderItem.forUrl("res/melon/plugins/event.swipe/jquery.event.swipe.js"));
        response.render( JavaScriptHeaderItem.forUrl("res/melon/plugins/select2/select2.min.js"));

        response.render( JavaScriptHeaderItem.forUrl("res/melon/assets/js/app.js"));

        response.render( JavaScriptHeaderItem.forUrl("res/melon/assets/js/plugins.js"));

        response.render( JavaScriptHeaderItem.forUrl("res/melon/assets/js/plugins.form-components.js"));


        StringBuilder js = new StringBuilder();
        js.append("$(document).ready( function() {\n");
        js.append("	App.init();\n");
        js.append("	Plugins.init();\n");
        js.append("	FormComponents.init();\n");
        js.append("} );");




        response.render(JavaScriptHeaderItem.forScript(js, getId() + "_selectList"));

    }
}
