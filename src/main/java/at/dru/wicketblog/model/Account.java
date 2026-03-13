package at.dru.wicketblog.model;

import jakarta.persistence.Basic;
import jakarta.persistence.Entity;

@Entity
public class Account extends AbstractEntity {

    private String login;

    private String password;

    private Boolean adminUser;

    @Basic(optional = false)
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    @Basic(optional = false)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic(optional = false)
    public Boolean getAdminUser() {
        return adminUser;
    }

    public void setAdminUser(Boolean adminUser) {
        this.adminUser = adminUser;
    }

}
