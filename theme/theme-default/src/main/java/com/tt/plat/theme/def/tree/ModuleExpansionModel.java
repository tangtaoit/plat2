package com.tt.plat.theme.def.tree;

import com.tt.plat.core.web.api.ModuleNode;
import org.apache.wicket.model.AbstractReadOnlyModel;

import java.util.Set;

/**
 * Created by tao on 2015/4/12.
 */
public class ModuleExpansionModel extends AbstractReadOnlyModel<Set<ModuleNode>> {
    @Override
    public Set<ModuleNode> getObject() {
        return ModuleExpansion.get();
    }
}
