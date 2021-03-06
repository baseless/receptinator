package nu.njp.receptinator.interfaces;

import nu.njp.receptinator.core.pojo.JsfMessage;
import nu.njp.receptinator.entities.Account;

import javax.ejb.Local;
import java.io.Serializable;
import java.util.Collection;

/**
 * AccountServiceLocal interface
 * @author Daniel Ryhle <daniel@ryhle.se>
 */

public interface AccountServiceLocal extends Serializable {
    /**
     * Authenticates a login request and returns the Account instance if successful.
     * @param userName      the username to authenticate
     * @param password      the password to authenticate
     * @return              the account instance if succeeded else null
     */
    Account authenticate(String userName, String password);

    /**
     * Adds a new account to the database
     * @param account   The account instance to be persisted in database
     * @return          JsfMessage instance containing status of the operation
     */
    JsfMessage addAccount(Account account);

    Account recoverPassword(String email);

    JsfMessage removeAccount(int accountId) throws javax.ejb.EJBTransactionRolledbackException;
    JsfMessage updateAccount(Account account);
    Account findAccount(int accountId);
    Collection<Account> allAccounts();
    boolean checkIfEmailExists(String email);
}
