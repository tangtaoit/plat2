package com.tt.plat.theme.def.tree;

import org.apache.wicket.Component;
import org.apache.wicket.behavior.Behavior;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.head.CssHeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.request.resource.CssResourceReference;
import org.apache.wicket.request.resource.ResourceReference;

/**
 * Created by tao on 2015/4/11.
 */
public class PlatTheme extends Behavior {
    private static final ResourceReference CSS = new CssResourceReference(PlatTheme.class,
            "ptheme.css");

    @Override
    public void onComponentTag(Component component, ComponentTag tag)
    {
        tag.append("class", "tree-theme-plat", " ");
    }

    @Override
    public void renderHead(Component component, IHeaderResponse response)
    {
        response.render(CssHeaderItem.forReference(CSS));
    }

}
