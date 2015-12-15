package nu.njp.receptinator.backing;

import nu.njp.receptinator.core.AuthenticationProvider;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.Basic;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Login backing bean
 * @author Daniel Ryhle <daniel@ryhle.se>
 */
@Named("login")
@RequestScoped
public class LoginBacking {

    @Inject
    AuthenticationProvider authentication;

    private String errorTitle;
    private String errorDescription;

    @Basic
    @NotNull
    @Size(min = 4, max = 50, message = "Invalid Username")
    private String userName;
    @Basic
    @NotNull
    @Size(min = 6, max = 200, message = "Invalid password")
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

    public String login() {
        authentication.isAuthenticated();
        errorTitle = "Login failed!";
        errorDescription = " Please check your credentials and try again..";
        //throw new RuntimeException("AUTHED: " + result);
        return null;
    }
    public String getErrorTitle() {
        return errorTitle;
    }

    public void setErrorTitle(String errorTitle) {
        this.errorTitle = errorTitle;
    }

    public String getErrorDescription() {
        return errorDescription;
    }

    public void setErrorDescription(String errorDescription) {
        this.errorDescription = errorDescription;
    }
}
