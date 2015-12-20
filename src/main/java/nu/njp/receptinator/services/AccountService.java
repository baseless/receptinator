package nu.njp.receptinator.services;

import nu.njp.receptinator.core.pojo.JsfMessage;
import nu.njp.receptinator.core.qualifier.Mocked;
import nu.njp.receptinator.core.util.PasswordHasher;
import nu.njp.receptinator.core.util.PostMail;
import nu.njp.receptinator.entities.Account;
import nu.njp.receptinator.interfaces.AccountServiceLocal;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * AccountServiceLocal
 * @author Daniel Ryhle, Mattias Hjalmarsson, Andreas Svensson
 */
@Stateless
@Mocked
public class AccountService implements AccountServiceLocal {

    @PersistenceContext(unitName = "NewPersistenceUnit")
    private EntityManager em;

    @Override
    public Account authenticate(String userName, String password) {
        Account selectedAcc;

        try {
            //hämta användare
            Query q = em.createNativeQuery("SELECT accountId FROM accounts WHERE userName = '" + userName + "';");
            int selectedId = (int) q.getSingleResult();
            selectedAcc = em.find(Account.class, selectedId);
        } catch (NoResultException e) {
            return null;
        }

        //hasha lösenordet med användarens salt
        String hashedPwd = PasswordHasher.Hash256(password, selectedAcc.getSalt());

        //jämför lösenorden
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
    public Account passwordLost(String email) {
        Account selectedAcc;
        PostMail postMail = null;
        int selectedId;
        try {
            //hämta användare
            Query q = em.createNativeQuery("SELECT accountId FROM accounts WHERE email = '" + email + "';");
            selectedId = (int) q.getSingleResult();
            selectedAcc = em.find(Account.class, selectedId);
        } catch (NoResultException e) {
            return null;
        }
        //jämför email and insert a new password into that account.
        if (email.equals(selectedAcc.getEmail())) {
            postMail.getAccountCredentials(selectedAcc);
            em.createNativeQuery("INSERT INTO accounts (password) VALUES (" + PasswordHasher.Hash256(postMail.getNewPassword(), selectedAcc.getSalt()) + ")")
                    .setParameter(1, selectedId)
                    .executeUpdate();
        } else {
            return null;
        }
        return selectedAcc;
    }
}


