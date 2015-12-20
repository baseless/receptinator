package nu.njp.receptinator.core;

import nu.njp.receptinator.entities.Account;
import nu.njp.receptinator.interfaces.AccountServiceLocal;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

/**
 * Created by Andreas on 2015-12-20.
 */
@Named("emailProvider")
@SessionScoped
public class EmailProvider implements Serializable {

    @Inject
    AccountServiceLocal accountServiceLocal;
    private Account account = null;

    public boolean accountExist() { return account != null; }

    public boolean emailSender(String email) {
        account = accountServiceLocal.passwordLost(email);
        return  accountExist();
    }

}
