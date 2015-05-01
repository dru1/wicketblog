package at.dru.wicketblog.pages;

import at.dru.wicketblog.components.FormType;
import at.dru.wicketblog.components.LoginFormPanel;

public class LoginPage extends AbstractPage {

    @Override
    protected void onInitialize() {
        super.onInitialize();

        add(new LoginFormPanel("loginPanel").setFormType(FormType.HORIZONTAL));
    }

    @Override
    protected String getPageTitle() {
        return "Login";
    }
}
