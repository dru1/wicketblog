package at.dru.wicketblog.wicket;

import org.apache.wicket.authroles.authorization.strategies.role.Roles;

public final class AuthUtils {

    private AuthUtils() {
    }

    public static boolean isAdmin() {
        return CurrentAuthenticatedWebSession.get().getRoles().hasRole(Roles.ADMIN);
    }

}
