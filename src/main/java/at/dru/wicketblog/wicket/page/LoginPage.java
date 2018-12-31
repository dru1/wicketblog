package at.dru.wicketblog.wicket.page;

import at.dru.wicketblog.wicket.component.FormType;
import at.dru.wicketblog.wicket.component.LoginFormPanel;

public class LoginPage extends AbstractPage {

    private static final long serialVersionUID = 1L;

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
