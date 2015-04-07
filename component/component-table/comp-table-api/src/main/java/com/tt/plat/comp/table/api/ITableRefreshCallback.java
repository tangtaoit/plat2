package com.tt.plat.comp.table.api;

import org.apache.wicket.ajax.AjaxRequestTarget;

/**
 * Created by tangtao on 2015-01-14.
 */
public interface ITableRefreshCallback {

    public void tablerefreshCallback(AjaxRequestTarget target);
}
