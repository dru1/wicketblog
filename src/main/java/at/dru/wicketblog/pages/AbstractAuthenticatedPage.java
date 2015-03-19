package at.dru.wicketblog.pages;

import at.dru.wicketblog.model.Account;
import at.dru.wicketblog.model.AccountRepository;
import at.dru.wicketblog.wicket.CurrentAuthenticatedWebSession;
import org.apache.wicket.authroles.authorization.strategies.role.Roles;
import org.apache.wicket.spring.injection.annot.SpringBean;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public abstract class AbstractAuthenticatedPage extends AbstractPage {

    @SpringBean
    protected AccountRepository accountRepository;

    @Override
    protected void onConfigure() {
        super.onConfigure();

        checkPrivileges();
    }

    private void checkPrivileges() {
        if (!hasRequiredPrivileges()) {
            // throws an exception
            wicketWebApplication.restartResponseAtSignInPage();
        }
    }

    private boolean hasRequiredPrivileges() {
        return CurrentAuthenticatedWebSession.get().isSignedIn() && !isMissingPrivileges();
    }

    private boolean isMissingPrivileges() {
        boolean result = false;

        CurrentAuthenticatedWebSession s = CurrentAuthenticatedWebSession.get();
        if (getRequiresAnyRole() != null && !s.getRoles().hasAnyRole(getRequiresAnyRole())) {
            result = true;
        } else if (getRequiresAllRoles() != null && !s.getRoles().hasAllRoles(getRequiresAllRoles())) {
            result = true;
        } else if (getRequiresRole() != null && !s.getRoles().hasRole(getRequiresRole())) {
            result = true;
        }

        return result;
    }

    @Nullable
    protected String getRequiresRole() {
        return null;
    }

    @Nullable
    protected Roles getRequiresAllRoles() {
        return null;
    }

    @Nullable
    protected Roles getRequiresAnyRole() {
        return null;
    }

    @Nonnull
    protected Account getCurrentAccount() {
        checkPrivileges();

        Long accountId = CurrentAuthenticatedWebSession.get().getAccountId();
        if (accountId == null) {
            // throws an exception
            wicketWebApplication.restartResponseAtSignInPage();
        }

        Account account = accountRepository.findOne(accountId);
        if (account == null) {
            // throws an exception
            wicketWebApplication.restartResponseAtSignInPage();
        }

        return account;
    }

}
