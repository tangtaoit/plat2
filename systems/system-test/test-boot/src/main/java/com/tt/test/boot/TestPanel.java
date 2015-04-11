package com.tt.test.boot;

import com.tt.plat.theme.def.panel.BasePanel;
import com.tt.test.boot.tree.BookmarkableFolderContent;
import com.tt.test.boot.tree.Foo;
import com.tt.test.boot.tree.FooProvider;
import org.apache.wicket.Component;
import org.apache.wicket.extensions.markup.html.repeater.tree.DefaultNestedTree;
import org.apache.wicket.extensions.markup.html.repeater.tree.NestedTree;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

/**
 * Created by tao on 2015/3/29.
 */
public class TestPanel extends BasePanel{


    public TestPanel(String id, org.apache.wicket.extensions.breadcrumb.IBreadCrumbModel breadCrumbModel) {
        super(id, breadCrumbModel);

        add(new DefaultNestedTree<Foo>("tree", new FooProvider())
        {

            /**
             * To use a custom component for the representation of a node's content we would
             * override this method.
             */
            @Override
            protected Component newContentComponent(String id, IModel<Foo> node)
            {
                return new BookmarkableFolderContent(this).newContentComponent(id,this,node);
            }
        });
    }

    @Override
    public IModel<String> getTitle() {
        return Model.of("测试");
    }
}
