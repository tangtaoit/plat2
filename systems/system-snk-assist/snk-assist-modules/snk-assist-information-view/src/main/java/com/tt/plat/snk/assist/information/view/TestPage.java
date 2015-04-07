package com.tt.plat.snk.assist.information.view;

import com.tt.plat.snk.assist.information.view.panel.TestPanel;
import com.tt.plat.theme.def.page.BasePage;

/**
 * Created by tao on 2015/4/5.
 */
public class TestPage extends BasePage {

    public TestPage() {
        super();

        TestPanel testPanel = new TestPanel("test",getBreadCrumbBar());
        add(testPanel);
    }
}
