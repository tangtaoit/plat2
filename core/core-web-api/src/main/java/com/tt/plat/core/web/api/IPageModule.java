package com.tt.plat.core.web.api;

import org.apache.wicket.Page;

/**
 * Created by tao on 2015/4/5.
 */
public interface IPageModule extends IModule {


    /**
     * 获取页的pageClass
     * @return
     */
    Class<? extends Page>  getPageClass();
}
