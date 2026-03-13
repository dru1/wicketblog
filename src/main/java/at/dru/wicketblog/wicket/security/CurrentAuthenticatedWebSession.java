package at.dru.wicketblog.wicket.security;

import at.dru.wicketblog.model.Account;
import at.dru.wicketblog.service.AccountService;
import org.apache.wicket.Session;
import org.apache.wicket.authroles.authentication.AuthenticatedWebSession;
import org.apache.wicket.authroles.authorization.strategies.role.Roles;
import org.apache.wicket.injection.Injector;
import org.apache.wicket.request.Request;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.jspecify.annotations.Nullable;

public class CurrentAuthenticatedWebSession extends AuthenticatedWebSession {

    private static final long serialVersionUID = 1L;

    @SpringBean
    private AccountService accountService;

    private Roles roles;

    private Long accountId;

    public static CurrentAuthenticatedWebSession get() {
        return (CurrentAuthenticatedWebSession) Session.get();
    }

    public CurrentAuthenticatedWebSession(Request request) {
        super(request);

        Injector.get().inject(this);

        reset();
    }

    public @Nullable Long getAccountId() {
        return accountId;
    }

    @Override
    public boolean authenticate(String username, String password) {
        boolean result = false;

        reset();
        Account account = accountService.login(username, password);

        if (account != null) {
            accountId = account.getId();

            if (Boolean.TRUE.equals(account.getAdminUser())) {
                roles.add(Roles.ADMIN);
            }

            result = true;
        }

        return result;
    }

    @Override
    public void signOut() {
        super.signOut();

        reset();
    }

    private void reset() {
        accountId = null;
        roles = new Roles();
        roles.add(Roles.USER);
    }

    @Override
    public Roles getRoles() {
        return roles;
    }
}
