package nu.njp.receptinator.backing;

import nu.njp.receptinator.core.AuthenticationProvider;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * Created by base on 2015-12-13.
 */
@Named
@RequestScoped
public class LoginBacking {

    @Inject
    AuthenticationProvider authentication;

    private String userName;
    private String password;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void authenticate() {
        authentication.isAuthenticated();
        //throw new RuntimeException("AUTHED: " + result);
    }
}
