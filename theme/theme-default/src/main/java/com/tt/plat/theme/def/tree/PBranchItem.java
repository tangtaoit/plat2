package com.tt.plat.theme.def.tree;

import com.tt.plat.core.web.api.ModuleNode;
import org.apache.wicket.Component;
import org.apache.wicket.behavior.Behavior;
import org.apache.wicket.extensions.markup.html.repeater.tree.AbstractTree;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.model.IModel;

/**
 * Created by tao on 2015/4/12.
 */
public class PBranchItem extends Item<ModuleNode> {
    private static final long serialVersionUID = 1L;

    public static final StatusStyleBehavior STATUS_STYLE_BEHAVIOR =new StatusStyleBehavior();

    public PBranchItem(String id, int index, IModel<ModuleNode> model)
    {
        super(id, index, model);

        setOutputMarkupId(true);

        add(STATUS_STYLE_BEHAVIOR);
    }

    protected String getClosedStyleClass()
    {
        return "";
    }

    protected String getOpenStyleClass()
    {
        return "open";
    }
    public String getStatusCss(){

        ModuleNode t = getModelObject();

        if(t==null){

            return "test";
        }

        String styleClass = "";
        AbstractTree tree = this.findParent(PDefaultNestedTree.class);
        if (tree.getProvider().hasChildren(t))
        {
            if (tree.getState(t) == AbstractTree.State.EXPANDED)
            {
                styleClass = getOpenStyleClass();
            }
            else
            {
                styleClass = getClosedStyleClass();
            }
        }else{

            return "";
        }


        return styleClass;

    }


    private boolean isLast()
    {
        return getIndex() == getParent().size() - 1;
    }

    private static class StatusStyleBehavior extends Behavior
    {
        private static final long serialVersionUID = 1L;

        public StatusStyleBehavior(){

        }


        @Override
        public void onComponentTag(Component component, ComponentTag tag)
        {

            PBranchItem pBranchItem = (PBranchItem)component;
            String styleClass = pBranchItem.getStatusCss();
            if (styleClass != null)
            {
                tag.put("class", styleClass);
            }
        }
    }

}
