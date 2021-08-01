package at.dru.wicketblog.wicket.page;

import at.dru.wicketblog.wicket.component.LoginFormPanel;
import at.dru.wicketblog.wicket.security.AuthUtils;
import org.apache.wicket.RestartResponseException;

public class LoginPage extends AbstractPage {

    private static final long serialVersionUID = 1L;

    @Override
    protected void onInitialize() {
        super.onInitialize();

        add(new LoginFormPanel("loginPanel"));
    }

    @Override
    protected void onConfigure() {
        super.onConfigure();

        if (AuthUtils.isSignedIn()) {
            throw new RestartResponseException(getApplication().getHomePage());
        }
    }

    @Override
    protected String getPageTitle() {
        return "Login";
    }

}
