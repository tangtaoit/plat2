package com.tt.plat.theme.def.tree;

import org.apache.wicket.Component;
import org.apache.wicket.MarkupContainer;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxFallbackLink;
import org.apache.wicket.behavior.Behavior;
import org.apache.wicket.extensions.markup.html.repeater.tree.AbstractTree;
import org.apache.wicket.extensions.markup.html.repeater.tree.Node;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;

/**
 * Created by tao on 2015/4/11.
 */
public abstract class PNode<T> extends Panel {
    private static final long serialVersionUID = 1L;

    /**
     * The component id for the content component.
     */
    public static final String CONTENT_ID = "content";

    private AbstractTree<T> tree;

    /**
     * Constructor.
     *
     * @param id
     *            component id
     * @param tree
     *            the owning tree
     * @param model
     *            the model for this node
     */
    public PNode(String id, AbstractTree<T> tree, IModel<T> model)
    {
        super(id, model);

        this.tree = tree;

        MarkupContainer junction = createJunctionComponent("junction");
       // junction.add(new StyleBehavior());
        add(junction);

        Component content = createContent(CONTENT_ID, model);
        if (!content.getId().equals(CONTENT_ID))
        {
            throw new IllegalArgumentException(
                    "content must have component id equal to Node.CONTENT_ID");
        }

       // junction.add(content);
        add(content);
    }

    /**
     * @return the model
     */
    @SuppressWarnings("unchecked")
    public IModel<T> getModel()
    {
        return (IModel<T>)getDefaultModel();
    }

    /**
     * @return the model object
     */
    public T getModelObject()
    {
        return getModel().getObject();
    }

    /**
     * The junction component expands and collapses this node.
     *
     * @param id
     *            the component id
     * @return component representing the junction
     */
    protected MarkupContainer createJunctionComponent(String id)
    {
        return new AjaxFallbackLink<Void>(id)
        {
            private static final long serialVersionUID = 1L;

            @Override
            public void onClick(AjaxRequestTarget target)
            {
                toggle();
            }

            @Override
            public boolean isEnabled()
            {
                return tree.getProvider().hasChildren(PNode.this.getModelObject());
            }
        };


//        return new Link<Void>(id) {
//
//            @Override
//            public void onClick() {
//
//                return;
//            }
//
//            @Override
//            protected CharSequence getURL() {
//                return "javascript:void(0);";
//            }
//        };
    }

    /**
     * Toggle the node.
     *
     * @see AbstractTree#collapse(Object)
     * @see AbstractTree#expand(Object)
     */
    protected void toggle()
    {
        T t = getModelObject();

        if (tree.getState(t) == AbstractTree.State.EXPANDED)
        {
            tree.collapse(t);
        }
        else
        {
            tree.expand(t);
        }
    }

    /**
     * Create the component to display the actual node's content.
     *
     * @param id
     *            the component id
     * @param model
     *            the node's model
     * @return the component representing the content
     */
    protected abstract Component createContent(String id, IModel<T> model);

    /**
     * Get the style class depending on the current {@link org.apache.wicket.extensions.markup.html.repeater.tree.AbstractTree.State} of this node.
     *
     * @see #getExpandedStyleClass(Object)
     * @see #getCollapsedStyleClass()
     * @see #getOtherStyleClass()
     * @return the style class
     */
    protected String getStyleClass()
    {
        T t = getModelObject();

        if (tree.getProvider().hasChildren(t))
        {
            if (tree.getState(t) == AbstractTree.State.EXPANDED)
            {
                return getExpandedStyleClass(t);
            }
            else
            {
                return getCollapsedStyleClass();
            }
        }
        return getOtherStyleClass();
    }

    protected String getExpandedStyleClass(T t)
    {
        return "tree-junction-expanded";
    }

    protected String getCollapsedStyleClass()
    {
        return "tree-junction-collapsed";
    }

    protected String getOtherStyleClass()
    {
        return "tree-junction";
    }

    /**
     * Behavior to add the style class attribute.
     *
     * @see Node#getStyleClass()
     */
    private static class StyleBehavior extends Behavior
    {
        private static final long serialVersionUID = 1L;

        @Override
        public void onComponentTag(Component component, ComponentTag tag)
        {
            PNode<?> node = (PNode<?>)component.getParent();

            String styleClass = node.getStyleClass();
            if (styleClass != null)
            {
                tag.put("class", styleClass);
            }
        }
    }


}
