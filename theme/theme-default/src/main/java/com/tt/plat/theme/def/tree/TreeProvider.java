package com.tt.plat.theme.def.tree;

import com.tt.plat.core.web.api.ModuleNode;
import com.tt.plat.core.web.api.Plat;
import org.apache.wicket.extensions.markup.html.repeater.tree.ITreeProvider;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import java.util.Iterator;
import java.util.List;

/**
 * Created by tao on 2015/4/11.
 */
public class TreeProvider implements ITreeProvider<ModuleNode> {

    @Override
    public Iterator<? extends ModuleNode> getRoots() {
        List<ModuleNode> roots = Plat.getInstall().getCurrMenus().getChilds();


        return roots.iterator();
    }

    @Override
    public boolean hasChildren(ModuleNode node) {
        return node.getParent() == null|| !node.getChilds().isEmpty();
    }

    @Override
    public Iterator<? extends ModuleNode> getChildren(ModuleNode node) {
        return node.getChilds().iterator();
    }

    @Override
    public IModel<ModuleNode> model(ModuleNode object) {


        return Model.of(object);
    }

    @Override
    public void detach() {

    }
}
