package com.tt.plat.test.a.view.panel;

import com.tt.plat.theme.def.panel.BasePanel;
import org.apache.wicket.extensions.breadcrumb.IBreadCrumbModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

/**
 * Created by tao on 2015/4/8.
 */
public class TestPanel extends BasePanel {

    public TestPanel(String id, IBreadCrumbModel breadCrumbModel) {
        super(id, breadCrumbModel);
    }

    @Override
    public IModel<String> getTitle() {
        return Model.of("test");
    }
}
