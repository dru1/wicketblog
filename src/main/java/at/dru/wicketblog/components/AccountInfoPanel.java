package at.dru.wicketblog.components;

import at.dru.wicketblog.model.Account;
import at.dru.wicketblog.model.AccountRepository;
import at.dru.wicketblog.wicket.CurrentAuthenticatedWebSession;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

public class AccountInfoPanel extends Panel {

    private static final long serialVersionUID = 1L;

    @SpringBean
    private AccountRepository accountRepository;

    public AccountInfoPanel(String id) {
        super(id);
    }

    protected boolean isSignedIn() {
        return CurrentAuthenticatedWebSession.get().isSignedIn();
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();

        add(new WebMarkupContainer("accountInfo") {

            private static final long serialVersionUID = 1L;

            @Override
            protected void onInitialize() {
                super.onInitialize();

                add(new Label("accountInfoLink", new LoadableDetachableModel<String>() {

                    private static final long serialVersionUID = 1L;

                    @Override
                    protected String load() {
                        return Optional.ofNullable(CurrentAuthenticatedWebSession.get().getAccountId())
                            .flatMap(accountRepository::findById)
                            .map(Account::getLogin)
                            .orElse(StringUtils.EMPTY);
                    }
                }));
            }

            @Override
            public boolean isVisible() {
                return isSignedIn();
            }
        });

        add(new WebMarkupContainer("signOut") {

            private static final long serialVersionUID = 1L;

            @Override
            protected void onInitialize() {
                super.onInitialize();

                add(new Link<String>("signOutLink") {

                    private static final long serialVersionUID = 1L;

                    @Override
                    public void onClick() {
                        CurrentAuthenticatedWebSession.get().invalidate();
                        setResponsePage(getApplication().getHomePage());
                    }
                });
            }

            @Override
            public boolean isVisible() {
                return isSignedIn();
            }
        });

        add(new LoginFormPanel("loginPanel") {

            private static final long serialVersionUID = 1L;

            @Override
            protected String getFormCssClass() {
                return super.getFormCssClass() + " navbar-form navbar-right";
            }

            @Override
            public boolean isVisible() {
                return !isSignedIn();
            }
            
        }.setFormType(FormType.INLINE));
    }
}
