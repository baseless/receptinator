package nu.njp.receptinator.backing;

import nu.njp.receptinator.core.AuthenticationProvider;
import nu.njp.receptinator.core.pojo.JsfMessage;
import nu.njp.receptinator.core.qualifier.DefaultLogger;
import org.slf4j.Logger;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.Basic;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.IOException;

/**
 * Login backing bean
 * @author Daniel Ryhle <daniel@ryhle.se>
 */
@Named("login")
@RequestScoped
public class LoginBacking extends BackingBase {

    @Inject
    AuthenticationProvider authenticationProvider;

    @Inject @DefaultLogger
    private Logger logger;

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
        if(authenticationProvider.authenticate(userName, password)) {
            try { redirect("member/login.xhtml"); } catch (IOException e) { logger.error(e.getMessage()); }
        } else {
            setMessage(new JsfMessage("Login failed!", "Please check your credentials and try again..", JsfMessage.MessageType.ERROR));
        }
        return null;
    }
}
