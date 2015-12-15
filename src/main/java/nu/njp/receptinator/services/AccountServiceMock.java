package nu.njp.receptinator.services;

import nu.njp.receptinator.core.JsfMessage;
import nu.njp.receptinator.core.qualifier.Mocked;
import nu.njp.receptinator.entities.Account;
import nu.njp.receptinator.interfaces.AccountService;

import javax.ejb.Stateless;

/**
 * AccountService mock
 * @author Daniel Ryhle <daniel@ryhle.se>
 */
@Stateless
@Mocked
public class AccountServiceMock implements AccountService {

    @Override
    public Account authenticate(String userName, String password) {
        return null;
    }

    @Override
    public JsfMessage addAccount(Account account) {
        return new JsfMessage("Account created!", "Account successfully created, please login.", JsfMessage.MessageType.SUCCESS);
    }

}
