package com.tt.plat.theme.def.panel;

import org.apache.wicket.extensions.breadcrumb.IBreadCrumbModel;
import org.apache.wicket.extensions.breadcrumb.panel.BreadCrumbPanel;
import org.apache.wicket.model.IModel;

/**
 * Created by tao on 2015/3/29.
 */
public abstract class BasePanel extends BreadCrumbPanel{


    public BasePanel(String id, IBreadCrumbModel breadCrumbModel) {
        super(id, breadCrumbModel);
    }
}
