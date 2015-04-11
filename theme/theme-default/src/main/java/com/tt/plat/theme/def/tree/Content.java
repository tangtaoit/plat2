package com.tt.plat.theme.def.tree;

import com.tt.plat.core.web.api.ModuleNode;
import org.apache.wicket.Component;
import org.apache.wicket.extensions.markup.html.repeater.tree.AbstractTree;
import org.apache.wicket.model.IDetachable;
import org.apache.wicket.model.IModel;

/**
 * Created by tao on 2015/4/11.
 */
public abstract class Content implements IDetachable {


    /**
     * Create new content.
     */
    public abstract Component newContentComponent(String id, AbstractTree<ModuleNode> tree,
                                                  IModel<ModuleNode> model);


    @Override
    public void detach() {

    }
}
