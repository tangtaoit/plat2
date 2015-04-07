package com.tt.plat.comp.charts;

import org.apache.wicket.Application;
import org.apache.wicket.Component;
import org.apache.wicket.behavior.Behavior;
import org.apache.wicket.markup.head.CssHeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;

/**
 * Created by tangtao on 2014/9/13.
 * Desc:
 */
public class FlotResourcesBehavior extends Behavior{

    @Override
    public void renderHead(Component component, IHeaderResponse response) {

        final ApplicationSettings settings = ApplicationSettings.get();

        // Include Wicket's provided jQuery reference
        response.render(JavaScriptHeaderItem.forReference(Application.get().getJavaScriptLibrarySettings()
                .getJQueryReference()));

        if (settings.isIncludeFlotCss()) {
            response.render(CssHeaderItem.forReference(settings.getCssFlotpackageResourceReference()));
        }

        if (settings.isIncludeJqueryFlot()) {
            response.render(JavaScriptHeaderItem.forReference(settings.getJqueryFlotpackageResourceReference()));
        }

        if(settings.isIncludeFlotResize()){
            response.render(JavaScriptHeaderItem.forReference(settings.getJqueryFlotResizepackageResourceReference()));
        }

        if(settings.isIncludeFlotToolTip()){
            response.render(JavaScriptHeaderItem.forReference(settings.getJqueryFlotToolTippackageResourceReference()));
        }

        if (settings.isIncludeJqueryTime()) {
            response.render(JavaScriptHeaderItem.forReference(settings.getJqueryTimeFlotpackageResourceReference()));
        }


    }
}
