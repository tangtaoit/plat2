package com.tt.plat.snk.assist.boot;

import com.tt.plat.theme.def.panel.BasePanel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

/**
 * Created by tao on 2015/3/29.
 */
public class TestPanel extends BasePanel{


    public TestPanel(String id, org.apache.wicket.extensions.breadcrumb.IBreadCrumbModel breadCrumbModel) {
        super(id, breadCrumbModel);
    }

    @Override
    public IModel<String> getTitle() {
        return Model.of("测试");
    }
}
