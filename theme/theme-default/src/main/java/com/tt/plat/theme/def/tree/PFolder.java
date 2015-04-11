package com.tt.plat.theme.def.tree;

import com.tt.plat.core.web.api.ModuleNode;
import org.apache.wicket.Component;
import org.apache.wicket.MarkupContainer;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxFallbackLink;
import org.apache.wicket.behavior.Behavior;
import org.apache.wicket.extensions.markup.html.repeater.tree.AbstractTree;
import org.apache.wicket.extensions.markup.html.repeater.tree.content.Folder;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.model.IModel;

/**
 * Created by tao on 2015/4/11.
 */
public class PFolder extends Folder<ModuleNode>{

    private AbstractTree tree;

    public final static ArrowStyleBehavior ARROWSTYLE= new ArrowStyleBehavior();

    public PFolder(String id, AbstractTree<ModuleNode> tree, IModel<ModuleNode> model) {
        super(id, tree, model);

        this.tree=tree;
    }

    protected MarkupContainer newLinkComponent(String id, IModel<ModuleNode> model)
    {
        Link link = new AjaxFallbackLink<Void>(id)
        {
            private static final long serialVersionUID = 1L;

            @Override
            public boolean isEnabled()
            {
                return PFolder.this.isClickable();
            }

            @Override
            public void onClick(AjaxRequestTarget target)
            {
                PFolder.this.onClick(target);
            }
        };

        /**
         * 箭头
         */
        Label arrow = new Label("arrow","<i></i>");
        arrow.setEscapeModelStrings(false);

        arrow.add(ARROWSTYLE);
        link.add(arrow);
        return link;
    }

    @Override
    protected String getStyleClass() {
        return "";
    }

    private String getArrowLeftCss(){

        return "arrow icon-angle-left";
    }

    private String getArrowDownCss(){

        return "arrow icon-angle-down";
    }

    public String getArrowCss(){

        ModuleNode t = getModelObject();

        String styleClass = "";
        if (tree.getProvider().hasChildren(t))
        {
            if (tree.getState(t) == AbstractTree.State.EXPANDED)
            {
                styleClass = getArrowDownCss();
            }
            else
            {
                styleClass = getArrowLeftCss();
            }
        }


        return styleClass;

    }

    private static class ArrowStyleBehavior extends Behavior
    {
        private static final long serialVersionUID = 1L;

        @Override
        public void onComponentTag(Component component, ComponentTag tag)
        {
            PFolder parent = (PFolder)component.getParent().getParent();

            String styleClass = parent.getArrowCss();
            if (styleClass != null)
            {
                tag.put("class", styleClass);
            }
        }
    }
}
