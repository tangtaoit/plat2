package com.tt.plat.test.a.view;


import com.tt.plat.test.a.view.panel.TestPanel;
import com.tt.test.core.BasePage;
import org.apache.wicket.markup.html.basic.Label;

/**
 * Created by tao on 2015/4/8.
 */
public class TestPage extends BasePage {


    public TestPage() {
        super();

        TestPanel testPanel = new TestPanel("test",getBreadCrumbBar());
        add(testPanel);

        getBreadCrumbBar().setActive(testPanel);
//        Label text  =new Label("text","test");
//        add(text);

    }
}
