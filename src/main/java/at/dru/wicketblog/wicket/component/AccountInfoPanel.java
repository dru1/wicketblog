package at.dru.wicketblog.wicket.component;

import at.dru.wicketblog.model.Account;
import at.dru.wicketblog.model.AccountRepository;
import at.dru.wicketblog.wicket.behavior.VisibilityBehavior;
import at.dru.wicketblog.wicket.security.AuthUtils;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.LambdaModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import javax.annotation.Nonnull;
import java.util.Optional;

public class AccountInfoPanel extends Panel {

    private static final long serialVersionUID = 1L;

    @SpringBean
    private AccountRepository accountRepository;

    public AccountInfoPanel(@Nonnull String id) {
        super(id);
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();

        WebMarkupContainer logoutPanel = new WebMarkupContainer("logoutPanel");
        add(logoutPanel);

        logoutPanel.add(new VisibilityBehavior(AuthUtils::isSignedIn));

        logoutPanel.add(new Label("accountInfoText", LambdaModel.of(AuthUtils::getAccountId)
                .map(accountRepository::findById)
                .map(Optional::get)
                .map(Account::getLogin)));

        logoutPanel.add(new Link<String>("signOutLink") {

            private static final long serialVersionUID = 1L;

            @Override
            public void onClick() {
                AuthUtils.signOut();
                setResponsePage(getApplication().getHomePage());
            }

        });

        logoutPanel.add(new WebMarkupContainer("adminMenuOpener").add(new VisibilityBehavior(AuthUtils::isAdmin)));

        logoutPanel.add(new WebMarkupContainer("adminMenu").add(new VisibilityBehavior(AuthUtils::isAdmin)));

        add(new LoginFormPanel("loginPanel").setFormType(FormType.INLINE).add(new VisibilityBehavior(AuthUtils::isSignedOut)));
    }
}
