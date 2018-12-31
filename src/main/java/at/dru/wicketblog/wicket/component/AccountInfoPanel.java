package at.dru.wicketblog.wicket.component;

import java.util.Optional;

import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.LambdaModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import at.dru.wicketblog.wicket.behavior.VisibilityBehavior;
import at.dru.wicketblog.model.Account;
import at.dru.wicketblog.model.AccountRepository;
import at.dru.wicketblog.wicket.security.AuthUtils;

public class AccountInfoPanel extends Panel {

    private static final long serialVersionUID = 1L;

    @SpringBean
    private AccountRepository accountRepository;

    public AccountInfoPanel(String id) {
        super(id);
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();

        add(new WebMarkupContainer("logoutPanel") {

            private static final long serialVersionUID = 1L;

            @Override
            protected void onInitialize() {
                super.onInitialize();

                add(new Label("accountInfoText", LambdaModel.of(AuthUtils::getAccountId)
                        .map(accountRepository::findById).map(Optional::get).map(Account::getLogin)));

                add(new Link<String>("signOutLink") {

                    private static final long serialVersionUID = 1L;

                    @Override
                    public void onClick() {
                        AuthUtils.signOut();
                        setResponsePage(getApplication().getHomePage());
                    }
                });

                add(new WebMarkupContainer("adminMenuOpener").add(new VisibilityBehavior(AuthUtils::isAdmin)));
                add(new WebMarkupContainer("adminMenu").add(new VisibilityBehavior(AuthUtils::isAdmin)));
            }
        }.add(new VisibilityBehavior(AuthUtils::isSignedIn)));

        add(new LoginFormPanel("loginPanel").setFormType(FormType.INLINE)
                .add(new VisibilityBehavior(AuthUtils::isSignedOut)));
    }
}
