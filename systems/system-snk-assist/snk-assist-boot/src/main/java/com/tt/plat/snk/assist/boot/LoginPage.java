package com.tt.plat.snk.assist.boot;

import org.apache.wicket.markup.html.WebPage;
import org.wicketstuff.shiro.component.LoginPanel;


/**
 * Simple index page
 */
public class LoginPage extends WebPage
{
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor.
	 */
	public LoginPage()
	{
		add(new LoginPanel("login", true));
	}


}
