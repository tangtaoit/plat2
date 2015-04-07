package com.tt.plat.theme.def;


import com.tt.plat.theme.def.panel.HeaderPanel;
import com.tt.plat.theme.def.panel.MenusPanel;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.border.Border;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.link.StatelessLink;
import org.apache.wicket.settings.IJavaScriptLibrarySettings;

/**
 * Created with IntelliJ IDEA.
 * User: tangtao
 * Date: 14-1-24
 * Time: 上午10:54
 * To change this template use File | Settings | File Templates.
 */
public class NavomaticBorder extends Border {

//    @Named("pageProviders")
//    @Inject
//    private java.util.List<PageModule> pageProvider;

    private MenusPanel menusPanel;
    public NavomaticBorder(String id) {
        super(id);

        menusPanel =new MenusPanel("menus");
        menusPanel.setRenderBodyOnly(true);
        add(menusPanel);
        add(new HeaderPanel("header")).setRenderBodyOnly(true);


    }

}
