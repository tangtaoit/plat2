package com.tt.plat.theme.def.resources;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.service.http.HttpService;

/**
 * Created with IntelliJ IDEA.
 * User: tangtao
 * Date: 14-1-23
 * Time: 下午2:57
 * To change this template use File | Settings | File Templates.
 */
public class ResoucesRegisterActivator implements BundleActivator {

    private  ServiceReference reference;
    @Override
    public void start(BundleContext bundleContext) throws Exception {
        //To change body of implemented methods use File | Settings | File Templates.
      reference = bundleContext.getServiceReference(HttpService.class);

        HttpService httpService = (HttpService) bundleContext.getService(reference);

        httpService.registerResources("/res","/com/tt/plat/theme/def/resources",null);


    }

    @Override
    public void stop(BundleContext bundleContext) throws Exception {
        //To change body of implemented methods use File | Settings | File Templates.
        bundleContext.ungetService(reference);
        bundleContext=null;

    }
}
