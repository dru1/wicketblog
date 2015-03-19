package at.dru.wicketblog.components;

import at.dru.wicketblog.model.AccountRepository;
import at.dru.wicketblog.wicket.CurrentAuthenticatedWebSession;
import org.apache.commons.lang3.StringUtils;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

public class AccountInfoPanel extends Panel {

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
            @Override
            protected void onInitialize() {
                super.onInitialize();

                add(new Label("accountInfoLink", new LoadableDetachableModel<String>() {
                    @Override
                    protected String load() {
                        Long accountId = CurrentAuthenticatedWebSession.get().getAccountId();

                        if (accountId != null) {
                            return accountRepository.findOne(accountId).getLogin();
                        }

                        return StringUtils.EMPTY;
                    }
                }));
            }

            @Override
            public boolean isVisible() {
                return isSignedIn();
            }
        });

        add(new WebMarkupContainer("signOut") {
            @Override
            protected void onInitialize() {
                super.onInitialize();

                add(new Link<String>("signOutLink") {
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
            @Override
            protected String getFormCssClass() {
                return "navbar-form navbar-right";
            }

            @Override
            public boolean isVisible() {
                return !isSignedIn();
            }
        });
    }
}
