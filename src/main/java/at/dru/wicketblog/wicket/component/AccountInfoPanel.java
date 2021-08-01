package at.dru.wicketblog.wicket.component;

import at.dru.wicketblog.model.Account;
import at.dru.wicketblog.model.AccountRepository;
import at.dru.wicketblog.wicket.behavior.VisibilityBehavior;
import at.dru.wicketblog.wicket.page.LoginPage;
import at.dru.wicketblog.wicket.security.AuthUtils;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
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

        setRenderBodyOnly(true);
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();

        add(new Label("accountInfoText", LambdaModel.of(AuthUtils::getAccountId)
                .map(accountRepository::findById)
                .map(Optional::get)
                .map(Account::getLogin))
                .add(new VisibilityBehavior(AuthUtils::isSignedIn)));

        add(new WebMarkupContainer("admin").add(new VisibilityBehavior(AuthUtils::isAdmin)));

        add(new Link<String>("signOutLink") {

            private static final long serialVersionUID = 1L;

            @Override
            public void onClick() {
                AuthUtils.signOut();
                setResponsePage(getApplication().getHomePage());
            }

        }.add(new VisibilityBehavior(AuthUtils::isSignedIn)));

        add(new BookmarkablePageLink<>("login", LoginPage.class).add(new VisibilityBehavior(AuthUtils::isSignedOut)));
    }

}
