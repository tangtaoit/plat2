package com.tt.plat.theme.def.tree;

import com.tt.plat.core.web.api.ModuleNode;
import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.extensions.markup.html.repeater.tree.ITreeProvider;
import org.apache.wicket.extensions.markup.html.repeater.tree.NestedTree;
import org.apache.wicket.extensions.markup.html.repeater.tree.nested.BranchItem;
import org.apache.wicket.extensions.markup.html.repeater.tree.nested.Subtree;
import org.apache.wicket.model.IModel;
import org.apache.wicket.util.visit.IVisit;
import org.apache.wicket.util.visit.IVisitor;

import java.util.Set;

/**
 * Created by tao on 2015/4/11.
 */
public class PDefaultNestedTree extends NestedTree<ModuleNode> {

    private static final long serialVersionUID = 1L;

    /**
     * Construct.
     *
     * @param id
     *            component id
     * @param provider
     *            provider of the tree
     */
    public PDefaultNestedTree(String id, ITreeProvider provider)
    {
        this(id, provider, null);
    }

    /**
     * Construct.
     *
     * @param id
     *            component id
     * @param provider
     *            provider of the tree
     * @param state
     *            expansion state
     */
    public PDefaultNestedTree(String id, ITreeProvider provider, IModel<Set<ModuleNode>> state)
    {
        super(id, provider, state);

        add(new PlatTheme());
    }

    /**
     * Creates {@link org.apache.wicket.extensions.markup.html.repeater.tree.content.Folder} for each node.
     *
     * @param id
     *            component id
     * @param node
     *            the node model
     */
    @Override
    protected Component newContentComponent(String id, IModel<ModuleNode> node)
    {
        return   new BookmarkableFolderContent(this).newContentComponent(id,this, node);
    }

    public Component newNodeComponent(String id, final IModel<ModuleNode> model)
    {
        PNode node =new PNode<ModuleNode>(id, this, model)
        {
            private static final long serialVersionUID = 1L;

            @Override
            protected Component createContent(String id, IModel<ModuleNode> model)
            {
                return PDefaultNestedTree.this.newContentComponent(id, model);
            }
        };
        node.setOutputMarkupId(true);
        return node;
    }

    public Component newSubtree(String id, IModel<ModuleNode> model)
    {
        return new PSubtree(id, this, model);
    }
    /**
     * Overridden to update the corresponding {@link org.apache.wicket.extensions.markup.html.repeater.tree.nested.BranchItem} only.
     */
    @Override
    public void updateBranch(ModuleNode t, final AjaxRequestTarget target)
    {
        if (target != null)
        {
            final IModel<ModuleNode> model = getProvider().model(t);
            visitChildren(PBranchItem.class, new IVisitor<PBranchItem, Void>()
            {
                @Override
                public void component(PBranchItem branch, IVisit<Void> visit)
                {
                    if (model.equals(branch.getModel()))
                    {
                        // BranchItem always outputs its markupId
                        target.add(branch);
                        visit.stop();
                    }
                }
            });
            model.detach();
        }
    }


}
