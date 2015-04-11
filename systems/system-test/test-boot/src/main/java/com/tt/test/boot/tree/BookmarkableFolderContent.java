package com.tt.test.boot.tree;

import com.tt.plat.core.web.api.IModule;
import com.tt.plat.core.web.api.IPageModule;
import com.tt.plat.core.web.api.ModuleNode;
import com.tt.plat.core.web.api.Plat;
import com.tt.plat.theme.def.tree.PFolder;
import com.tt.test.boot.WicketApplication;
import org.apache.wicket.Component;
import org.apache.wicket.MarkupContainer;
import org.apache.wicket.extensions.markup.html.repeater.tree.AbstractTree;
import org.apache.wicket.extensions.markup.html.repeater.tree.content.Folder;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.model.IModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;

/**
 * Created by tao on 2015/4/11.
 */
public class BookmarkableFolderContent extends Content {


    public BookmarkableFolderContent(final AbstractTree<Foo> tree) {
        String id = tree.getRequest().getRequestParameters().getParameterValue("foo").toString();
        if (id != null)
        {
            Foo foo = WicketApplication.get().getFoo(id);
            while (foo != null)
            {
                tree.getModel().getObject().add(foo);
                foo = foo.getParent();
            }
        }

    }

    @Override
    public Component newContentComponent(String id, final AbstractTree<Foo> tree, IModel<Foo> model) {
        return new Folder<Foo>(id, tree, model)
        {
            private static final long serialVersionUID = 1L;

            @Override
            protected MarkupContainer newLinkComponent(String id, IModel<Foo> model)
            {
                Foo foo = model.getObject();

                if (tree.getProvider().hasChildren(foo))
                {
                    return super.newLinkComponent(id, model);
                }
                else
                {
                    PageParameters parameters = new PageParameters();
                    parameters.add("foo", foo.getId());

                    return new BookmarkablePageLink<Void>(id, tree.getPage().getClass(), parameters);
                }
            }
        };
    }
}
