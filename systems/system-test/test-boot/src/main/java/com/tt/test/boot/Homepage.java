package com.tt.test.boot;


import com.tt.plat.theme.def.page.BasePage;
import org.wicketstuff.shiro.ShiroConstraint;
import org.wicketstuff.shiro.annotation.ShiroSecurityConstraint;

/**
 * Created by tao on 2015/3/29.
 */
@ShiroSecurityConstraint(constraint = ShiroConstraint.LoggedIn,loginMessage = "登录成功！",unauthorizedMessage = "wei r zheng")
public class Homepage extends BasePage {


    public Homepage() {

        super();

        TestPanel testPanel = new TestPanel("test",getBreadCrumbBar());
        add(testPanel);

        getBreadCrumbBar().setActive(testPanel);
    }
}
