package com.tt.plat.theme.def.tree;

import com.tt.plat.core.web.api.IModule;
import com.tt.plat.core.web.api.IPageModule;
import com.tt.plat.core.web.api.ModuleNode;
import com.tt.plat.core.web.api.Plat;
import org.apache.wicket.Component;
import org.apache.wicket.MarkupContainer;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxFallbackLink;
import org.apache.wicket.extensions.markup.html.repeater.tree.AbstractTree;
import org.apache.wicket.extensions.markup.html.repeater.tree.content.Folder;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.model.IModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import java.util.Set;

/**
 * Created by tao on 2015/4/11.
 */
public class BookmarkableFolderContent extends Content{


    public BookmarkableFolderContent(final AbstractTree<ModuleNode> tree) {
        String flag = tree.getRequest().getRequestParameters().getParameterValue("flag").toString();
        if (flag != null)
        {
            ModuleNode moduleNode = Plat.getInstall().getModuleNodeByFlag(flag);
            while (moduleNode != null)
            {
                tree.getModel().getObject().add(moduleNode);
                moduleNode = moduleNode.getParent();
            }
        }

    }

    @Override
    public Component newContentComponent(String id, final AbstractTree<ModuleNode> tree, IModel<ModuleNode> model) {
        return new PFolder(id, tree, model)
        {
            private static final long serialVersionUID = 1L;

            @Override
            protected MarkupContainer newLinkComponent(String id, IModel<ModuleNode> model)
            {
                ModuleNode moduleNode = model.getObject();

                if (tree.getProvider().hasChildren(moduleNode))
                {
                    return super.newLinkComponent(id, model);
                }
                else
                {
                    PageParameters parameters = new PageParameters();
                    IModule md =moduleNode.getModule();
                    parameters.add("flag", md.getFlag());

                    IPageModule pageModule =(IPageModule)model.getObject().getModule();
                   Link link = new BookmarkablePageLink<Void>(id,pageModule.getPageClass() , parameters);
                    /**
                     * 箭头
                     */
                    Label arrow = new Label("arrow","<i></i>");
                    arrow.setEscapeModelStrings(false);

                    arrow.add(ARROWSTYLE);
                    link.add(arrow);
                    return link;
                }
            }
        };
    }
}
