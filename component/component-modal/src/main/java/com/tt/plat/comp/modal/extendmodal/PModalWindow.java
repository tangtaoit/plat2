package com.tt.plat.comp.modal.extendmodal;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.Page;
import org.apache.wicket.WicketRuntimeException;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.core.request.handler.PageProvider;
import org.apache.wicket.core.request.handler.RenderPageRequestHandler;
import org.apache.wicket.extensions.ajax.markup.html.modal.ModalWindow;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.head.CssHeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.IRequestHandler;
import org.apache.wicket.request.cycle.RequestCycle;
import org.apache.wicket.request.resource.JavaScriptResourceReference;
import org.apache.wicket.request.resource.ResourceReference;
import org.apache.wicket.resource.CoreLibrariesContributor;
import org.apache.wicket.util.string.AppendingStringBuffer;

import java.io.Serializable;

/**
 * Created by tangtao on 2015-03-12.
 */
public class PModalWindow extends ModalWindow {

    private Label titleLabel;

    private AjaxButton submitBtn;

    public PModalWindow(String id) {
        super(id);
        add(new PModalWindowBehavior());

        titleLabel = new Label("title");
        add(titleLabel);



    }

    @Override
    protected void onComponentTag(ComponentTag tag) {
        super.onComponentTag(tag);

        tag.put("class","modal fade");
        tag.put("tabindex",-1);
        tag.put("data-width",760);
        tag.put("style","display: none");
    }

    @Override
    public PModalWindow setTitle(String title) {

        titleLabel.setDefaultModel(new Model<Serializable>(title));

        return this;
    }

    public void show(final AjaxRequestTarget target) {
        getContent().setVisible(true);
        target.add(this);
        target.appendJavaScript(getWindowOpenJavaScript());
    }


    protected AppendingStringBuffer postProcessSettings(final AppendingStringBuffer settings) {
        settings.clear();
        return settings;
    }

    protected boolean makeContentVisible() {
        // if user is refreshing whole page, the window will not be shown
        if (getWebRequest().isAjax() == false) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    protected String getCloseJavacript() {
        return "$('#" + this.getMarkupId() + "').modal('hide')";
    }

    protected CharSequence getShowJavaScript() {

        return "$('#" + this.getMarkupId() + "').modal('show')";
    }
}

