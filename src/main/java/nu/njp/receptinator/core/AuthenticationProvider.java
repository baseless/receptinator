package nu.njp.receptinator.core;

import nu.njp.receptinator.core.qualifier.Mocked;
import nu.njp.receptinator.entities.Account;
import nu.njp.receptinator.interfaces.AccountService;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

/**
 * Authentication provider
 * @author Daniel Ryhle <daniel@ryhle.se>
 */
@Named("authenticationProvider")
@SessionScoped
public class AuthenticationProvider implements Serializable {

    @Inject @Mocked
    AccountService accountService;

    private Account account = null;

    public boolean isAuthenticated() { return account != null; }

    public boolean authenticate(String userName, String password) {
        account = accountService.authenticate(userName, password);
        return  isAuthenticated();
    }

    public void logout() {
        account = null;
    }
}
