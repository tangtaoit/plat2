package com.tt.plat.comp.modal;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.Component;
import org.apache.wicket.ajax.AbstractDefaultAjaxBehavior;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.attributes.AjaxCallListener;
import org.apache.wicket.ajax.attributes.AjaxRequestAttributes;
import org.apache.wicket.ajax.attributes.IAjaxCallListener;
import org.apache.wicket.extensions.ajax.markup.html.modal.ModalWindow;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.MarkupStream;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.OnDomReadyHeaderItem;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.repeater.RepeatingView;
import org.apache.wicket.model.PropertyModel;

/**
 * Created by tangtao on 2014/9/18.
 * Desc:
 */
public class PWindowModal extends Panel {

    public static final String CONTENT_ID = "content";


    protected ICloseButtonCallback closeButtonCallback;

    protected  IOKButtonCallback okButtonCallback;

    private  PWindowModal install=this;


    private RepeatingView content = new RepeatingView(getContentId());

    private boolean shown = false;

    private WebMarkupContainer webMarkupContainer;

    private Label titleLabel;

    private String title="";

    private String okBeforeJs;

    public PWindowModal(String id) {
        super(id);
        add(new PWindowModalBehavior());

        add(new CloseButtonBehavior());
        add(new OKBtnBehavior());
        webMarkupContainer = new WebMarkupContainer("modal");
        webMarkupContainer.add(new AttributeModifier("id", id));

        webMarkupContainer.add(titleLabel = new Label("title",new PropertyModel(this,"title")));

        Button okBtn = new Button("okBtn"){
            @Override
            protected String getOnClickScript() {

                return "okModalForServer_"+install.getMarkupId()+"()";
            }
        };

        webMarkupContainer.add(okBtn);

        add(webMarkupContainer);




    }

    public void setOkBeforeJs(String okBeforeJs){
        this.okBeforeJs=okBeforeJs;
    }


    public PWindowModal  setTitle(String title){

        this.title=title;
        return this;
    }

    public void setCloseButtonCallback(ICloseButtonCallback closeButtonCallback) {
        this.closeButtonCallback = closeButtonCallback;
    }

    public void setOkButtonCallback(IOKButtonCallback okButtonCallback) {
        this.okButtonCallback = okButtonCallback;
    }

    public void setContent(Component component) {

        if (content != null) {
            remove(content);
        }
        this.content.add(component.setMarkupId(content.newChildId()));
    }

    public void close(){

    }

    @Override
    protected void onBeforeRender() {
        shown = makeContentVisible();

        //getContent().setOutputMarkupId(true);
        // getContent().setVisible(shown);

        super.onBeforeRender();

        webMarkupContainer.add(this.content);
    }

    protected boolean makeContentVisible() {
        // if user is refreshing whole page, the window will not be shown
        if (getWebRequest().isAjax() == false) {
            return false;
        } else {
            return shown;
        }
    }

    public Component getContent() {
        return content;
    }

    public String getContentId() {


        return CONTENT_ID;
    }


    class CloseButtonBehavior extends AbstractDefaultAjaxBehavior {

        @Override
        protected void respond(AjaxRequestTarget target) {
//            if (closeButtonCallback != null) {
//                closeButtonCallback.closeModal(target);
//            }
        }

        @Override
        public void renderHead(Component component, IHeaderResponse response) {
            super.renderHead(component, response);
            String callBackScript = getCallbackFunction().toString();
            callBackScript = "closeModalForServer=" + callBackScript + ";";
            response.render(OnDomReadyHeaderItem.forScript(callBackScript));
        }


    }





    class OKBtnBehavior extends AbstractDefaultAjaxBehavior{
        @Override
        protected void respond(AjaxRequestTarget target) {
            if (okButtonCallback != null) {
                okButtonCallback.okPress(target);
            }
        }

        @Override
        public void renderHead(Component component, IHeaderResponse response) {
            super.renderHead(component, response);
            String callBackScript = getCallbackFunction().toString();
            callBackScript = "okModalForServer_"+install.getMarkupId()+"=" + callBackScript + ";";
            response.render(OnDomReadyHeaderItem.forScript(callBackScript));
        }

        @Override
        protected void updateAjaxAttributes(AjaxRequestAttributes attributes) {
            super.updateAjaxAttributes(attributes);


            if(okBeforeJs!=null){
                AjaxCallListener ajaxCallListener = new AjaxCallListener();
                ajaxCallListener.onBefore(okBeforeJs);
                attributes.getAjaxCallListeners().add(ajaxCallListener);
            }

        }
    }
}


