package at.dru.wicketblog.model;

import javax.annotation.Nonnull;
import javax.persistence.Basic;
import javax.persistence.Entity;

@Entity
public class Account extends DefaultEntity {

    private String login;

    private String password;

    private Boolean adminUser;

    @Nonnull
    @Basic(optional = false)
    public String getLogin() {
        return login;
    }

    public void setLogin(@Nonnull String login) {
        this.login = login;
    }

    @Nonnull
    @Basic(optional = false)
    public String getPassword() {
        return password;
    }

    public void setPassword(@Nonnull String password) {
        this.password = password;
    }

    @Nonnull
    @Basic(optional = false)
    public Boolean getAdminUser() {
        return adminUser;
    }

    public void setAdminUser(@Nonnull Boolean adminUser) {
        this.adminUser = adminUser;
    }
}
