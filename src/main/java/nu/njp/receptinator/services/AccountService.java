package nu.njp.receptinator.services;

import nu.njp.receptinator.core.pojo.JsfMessage;
import nu.njp.receptinator.core.qualifier.DefaultLogger;
import nu.njp.receptinator.core.qualifier.Mocked;
import nu.njp.receptinator.core.util.PasswordHasher;
import nu.njp.receptinator.core.util.PostMail;
import nu.njp.receptinator.entities.Account;
import nu.njp.receptinator.entities.Recipe;
import nu.njp.receptinator.interfaces.AccountServiceLocal;
import org.slf4j.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.Collection;

/**
 * AccountServiceLocal
 * @author Daniel Ryhle, Mattias Hjalmarsson, Andreas Svensson
 */
@Stateless
public class AccountService implements AccountServiceLocal {

    @PersistenceContext(unitName = "NewPersistenceUnit")
    private EntityManager em;

    @Inject
    @DefaultLogger
    private Logger logger;

    @Override
    public Account authenticate(String userName, String password) {
        Account selectedAcc;
        try {
            selectedAcc = (Account) em.createNamedQuery("findIdByUserName").setParameter("userName", userName).getSingleResult();
        } catch (NoResultException e) {
            return null;
        }

        String hashedPwd = PasswordHasher.Hash256(password, selectedAcc.getSalt());
        if (hashedPwd.equals(selectedAcc.getPassword())) {
            return selectedAcc;
        } else {
            return null;
        }
    }

    @Override
    public JsfMessage addAccount(Account account) {
        try {
            account.setPassword(PasswordHasher.Hash256(account.getPassword(), account.getSalt()));
            em.persist(account);
        } catch (Exception e) {
            return new JsfMessage("Error creating account!", "Error in creating account, please try again.", JsfMessage.MessageType.ERROR);
        }
        return new JsfMessage("Account created!", "Account successfully created, please login.", JsfMessage.MessageType.SUCCESS);
    }

    @Override
    public Account recoverPassword(String email) {
        Account selectedAcc;
        PostMail postMail = new PostMail();
        try {
            selectedAcc = (Account) em.createNamedQuery("findIdByEmail").setParameter("email", email).getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
        postMail.setRecipient(selectedAcc);
        postMail.sendMessage();
        String newPassword = PasswordHasher.Hash256(postMail.getNewPassword(), selectedAcc.getSalt());
        em.createNamedQuery("setPasswordById").setParameter("newPassword", newPassword).setParameter("accountId", selectedAcc.getAccountId()).executeUpdate();

        return selectedAcc;
    }

    @Override
    public JsfMessage removeAccount(int accountId) {
        try {
            em.remove(em.find(Account.class, accountId));
        } catch(Exception e){
            return new JsfMessage("Error removing account!", "Error in removing account, please try again.", JsfMessage.MessageType.ERROR);
        }
        return new JsfMessage("Account removed!", "Account successfully removed.", JsfMessage.MessageType.SUCCESS);
    }

    @Override
    public JsfMessage updateAccount(Account account) {
        try {
            //account.setPassword(PasswordHasher.Hash256(account.getPassword(), account.getSalt()));
            em.merge(account);
        } catch (NoResultException e) {
            return new JsfMessage("Error updating account!", "Error in updating account, please try again.", JsfMessage.MessageType.ERROR);
        }
        return new JsfMessage("Account updated!", "Account successfully updated.", JsfMessage.MessageType.SUCCESS);
    }

    @Override
    public Account findAccount(int accountId) {
        return em.find(Account.class, accountId);
    }

    @Override
    public Collection<Account> allAccounts() {
        Collection<Account> result = new ArrayList<>();
        try {
            result = em.createNamedQuery("getAllAccounts", Account.class).getResultList();
        } catch (Exception e) {
            logger.warn(e.getMessage());
        }
        return result;
    }


}


