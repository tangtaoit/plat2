package com.tt.plat.theme.def.tree;

import com.tt.plat.core.web.api.ModuleNode;
import org.apache.wicket.Component;
import org.apache.wicket.behavior.Behavior;
import org.apache.wicket.extensions.markup.html.repeater.tree.AbstractTree;
import org.apache.wicket.extensions.markup.html.repeater.tree.NestedTree;
import org.apache.wicket.extensions.markup.html.repeater.tree.nested.BranchItem;
import org.apache.wicket.extensions.markup.html.repeater.tree.nested.Subtree;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.repeater.IItemFactory;
import org.apache.wicket.markup.repeater.IItemReuseStrategy;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.RefreshingView;
import org.apache.wicket.model.IModel;

import java.util.Iterator;

/**
 * Created by tao on 2015/4/11.
 */
public class PSubtree extends Panel{


    private static final long serialVersionUID = 1L;

    private NestedTree<ModuleNode> tree;

    /**
     * Create a subtree for the children of the node contained in the given model or the root nodes
     * if the model contains <code>null</code>.
     *
     * @param id
     *            component id
     * @param tree
     *            the containing tree
     * @param model
     */
    public PSubtree(String id, final NestedTree<ModuleNode> tree, final IModel<ModuleNode> model)
    {
        super(id, model);

        if (tree == null)
        {
            throw new IllegalArgumentException("argument [tree] cannot be null");
        }
        this.tree = tree;

        RefreshingView<ModuleNode> branches = new RefreshingView<ModuleNode>("branches")
        {
            private static final long serialVersionUID = 1L;

            @Override
            protected Iterator<IModel<ModuleNode>> getItemModels()
            {
                return new ModelIterator();
            }

            @Override
            protected Item<ModuleNode> newItem(String id, int index, IModel<ModuleNode> model)
            {
                return newBranchItem(id, index, model);
            }

            @Override
            protected void populateItem(Item<ModuleNode> item)
            {
                IModel<ModuleNode> model = item.getModel();

                Component node = tree.newNodeComponent("node", model);
                item.add(node);

                item.add(tree.newSubtree("subtree", model));
            }
        };
        branches.setItemReuseStrategy(new IItemReuseStrategy()
        {
            private static final long serialVersionUID = 1L;

            @Override
            public <S> Iterator<Item<S>> getItems(IItemFactory<S> factory,
                                                  Iterator<IModel<S>> newModels, Iterator<Item<S>> existingItems)
            {
                return tree.getItemReuseStrategy().getItems(factory, newModels, existingItems);
            }
        });
        add(branches);
    }

    @SuppressWarnings("unchecked")
    public IModel<ModuleNode> getModel()
    {
        return (IModel<ModuleNode>)getDefaultModel();
    }

    public ModuleNode getModelObject()
    {
        return getModel().getObject();
    }

    protected PBranchItem newBranchItem(String id, int index, IModel<ModuleNode> model)
    {
        return new PBranchItem(id, index, model);
    }

    @Override
    public boolean isVisible()
    {
        ModuleNode t = getModel().getObject();
        if (t == null)
        {
            // roots always visible
            return true;
        }
        else
        {
            return tree.getState(t) == AbstractTree.State.EXPANDED;
        }
    }

    private final class ModelIterator implements Iterator<IModel<ModuleNode>>
    {
        private Iterator<? extends ModuleNode> children;

        public ModelIterator()
        {
            ModuleNode t = getModel().getObject();
            if (t == null)
            {
                children = tree.getProvider().getRoots();
            }
            else
            {
                children = tree.getProvider().getChildren(t);
            }
        }

        @Override
        public void remove()
        {
            throw new UnsupportedOperationException();
        }

        @Override
        public boolean hasNext()
        {
            return children.hasNext();
        }

        @Override
        public IModel<ModuleNode> next()
        {
            return tree.getProvider().model(children.next());
        }
    }
}
