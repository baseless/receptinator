package nu.njp.receptinator.services;

import nu.njp.receptinator.core.pojo.JsfMessage;
import nu.njp.receptinator.core.qualifier.Mocked;
import nu.njp.receptinator.entities.Account;
import nu.njp.receptinator.interfaces.AccountServiceLocal;

import javax.ejb.Stateless;

/**
 * AccountServiceLocal mock
 * @author Daniel Ryhle <daniel@ryhle.se>
 */
@Stateless
@Mocked
public class AccountService implements AccountServiceLocal {

    @Override
    public Account authenticate(String userName, String password) {
        return null;
    }

    @Override
    public JsfMessage addAccount(Account account) {
        return new JsfMessage("Account created!", "Account successfully created, please login.", JsfMessage.MessageType.SUCCESS);
    }

}
