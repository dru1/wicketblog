package at.dru.wicketblog.wicket.security;

import org.apache.wicket.authroles.authorization.strategies.role.Roles;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public final class AuthUtils {

    private AuthUtils() {
    }

    @Nullable
    public static Long getAccountId() {
        return CurrentAuthenticatedWebSession.get().getAccountId();
    }

    public static boolean signIn(@Nonnull String username, @Nonnull String password) {
        return CurrentAuthenticatedWebSession.get().signIn(username, password);
    }

    public static void signOut() {
        CurrentAuthenticatedWebSession.get().invalidate();
    }

    public static boolean isSignedIn() {
        return CurrentAuthenticatedWebSession.get().isSignedIn();
    }

    public static boolean isSignedOut() {
        return !isSignedIn();
    }

    public static boolean isAdmin() {
        return CurrentAuthenticatedWebSession.get().getRoles().hasRole(Roles.ADMIN);
    }

}
