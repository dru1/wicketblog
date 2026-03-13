package at.dru.wicketblog.wicket.security;

import org.apache.wicket.authroles.authorization.strategies.role.Roles;
import org.jspecify.annotations.Nullable;

public final class AuthUtils {

    private AuthUtils() {
    }

    public static @Nullable Long getAccountId() {
        return CurrentAuthenticatedWebSession.get().getAccountId();
    }

    public static boolean signIn(String username, String password) {
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
