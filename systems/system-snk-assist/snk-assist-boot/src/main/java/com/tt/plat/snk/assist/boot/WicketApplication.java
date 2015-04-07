package com.tt.plat.snk.assist.boot;

import org.apache.wicket.Page;
import org.apache.wicket.mock.MockApplication;
import org.wicketstuff.shiro.annotation.AnnotationsShiroAuthorizationStrategy;
import org.wicketstuff.shiro.authz.ShiroUnauthorizedComponentListener;

/**
 * Created by tao on 2015/4/5.
 */
public class WicketApplication extends MockApplication {


    public WicketApplication() {
        super();
    }



    @Override
    protected void init()
    {
        super.init();

        getMarkupSettings().setStripWicketTags(true);

        getDebugSettings().setAjaxDebugModeEnabled(true);
        getDebugSettings().setDevelopmentUtilitiesEnabled(true);

        // Configure Shiro
        AnnotationsShiroAuthorizationStrategy authz = new AnnotationsShiroAuthorizationStrategy();
        getSecuritySettings().setAuthorizationStrategy(authz);
        getSecuritySettings().setUnauthorizedComponentInstantiationListener(
                new ShiroUnauthorizedComponentListener(LoginPage.class, UnauthorizedPage.class, authz));


        mountPage("/login", LoginPage.class);


    }

    @Override
    public Class<? extends Page> getHomePage()
    {
        return Homepage.class;
    }


}
