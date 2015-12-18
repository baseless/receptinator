package nu.njp.receptinator.core;

import nu.njp.receptinator.core.qualifier.Mocked;
import nu.njp.receptinator.entities.Account;
import nu.njp.receptinator.interfaces.AccountServiceLocal;

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
    AccountServiceLocal accountServiceLocal;

    private Account account = null;

    public boolean isAuthenticated() { return account != null; }

    public boolean authenticate(String userName, String password) {
        account = accountServiceLocal.authenticate(userName, password);

        account = new Account();
        account.setAccountId(1);
        account.setUserName("base");

        return  isAuthenticated();
    }

    public String logout() {
        account = null;
        return "/login";
    }
}
