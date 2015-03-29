/**
 * Copyright OPS4J
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may
 * not use this file except in compliance with the License. You may obtain
 * a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.tt.test.boot;

import org.apache.wicket.Page;
import org.apache.wicket.protocol.http.WebApplication;
import org.wicketstuff.osgi.OsgiClassResolver;
import org.wicketstuff.osgi.inject.OsgiComponentInjector;
import org.wicketstuff.shiro.annotation.AnnotationsShiroAuthorizationStrategy;
import org.wicketstuff.shiro.authz.ShiroUnauthorizedComponentListener;

public class WicketApplication extends WebApplication
{
//    public WicketApplication() {
//        getDebugSettings().setAjaxDebugModeEnabled(true);
//    }

    @Override
    protected void init()
    {
        super.init();
        getMarkupSettings().setStripWicketTags(true);

        getDebugSettings().setAjaxDebugModeEnabled(true);

        // Configure Shiro
        AnnotationsShiroAuthorizationStrategy authz = new AnnotationsShiroAuthorizationStrategy();
        getSecuritySettings().setAuthorizationStrategy(authz);
        getSecuritySettings().setUnauthorizedComponentInstantiationListener(
                new ShiroUnauthorizedComponentListener(LoginPage.class, UnauthorizedPage.class, authz));


        mountPage("account/login", LoginPage.class);

        getComponentInstantiationListeners().add(new OsgiComponentInjector());


		/*
		 * Not really needed, at least on Jetty.
		 */
        getApplicationSettings().setClassResolver(new OsgiClassResolver());
    }

    @Override
    public Class<? extends Page> getHomePage()
    {
        return Homepage.class;
    }

}
