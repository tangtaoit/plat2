package com.tt.plat.comp.modal;

import org.apache.wicket.Component;
import org.apache.wicket.behavior.Behavior;
import org.apache.wicket.markup.head.CssHeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.request.resource.CssResourceReference;
import org.apache.wicket.request.resource.JavaScriptResourceReference;


/**
 * Created by tangtao on 2014/9/18.
 * Desc:
 */
public class PWindowModalBehavior extends Behavior {

    @Override
    public void bind(Component component) {
        super.bind(component);

        component.setOutputMarkupId(true);
    }

    @Override
    public void renderHead(Component component, IHeaderResponse response) {
        super.renderHead(component, response);

        //Bootstrap.renderHead(response);

        response.render(CssHeaderItem.forReference(
                new CssResourceReference(PWindowModalBehavior.class, "bootstrap-modal-bs3patch.css")));

        response.render(CssHeaderItem.forReference(
                new CssResourceReference(PWindowModalBehavior.class, "bootstrap-modal.css")));

        response.render(JavaScriptHeaderItem.forReference(new JavaScriptResourceReference(PWindowModalBehavior.class,"bootstrap-modalmanager.js")));

        response.render(JavaScriptHeaderItem.forReference(new JavaScriptResourceReference(PWindowModalBehavior.class,"bootstrap-modal.js")));

    }
}
