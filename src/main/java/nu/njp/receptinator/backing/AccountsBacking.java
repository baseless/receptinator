package nu.njp.receptinator.backing;

import nu.njp.receptinator.core.pojo.JsfMessage;
import nu.njp.receptinator.core.qualifier.DefaultLogger;
import nu.njp.receptinator.entities.Account;
import nu.njp.receptinator.interfaces.AccountServiceLocal;
import org.slf4j.Logger;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.Basic;
import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.Map;

/**
 * Created by Mattias on 2016-01-03.
 */
@Named("accountEdit")
@RequestScoped
public class AccountsBacking extends BackingBase{


    @Inject
    @DefaultLogger
    private Logger logger;

    @Inject
    AccountServiceLocal accountService;

    private Account account;

    @Basic(optional = true)
    @Size(min = 6, max = 200)
    private String newPassword;

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    private int accountId;

    public Account getAccount() {
        FacesContext context = FacesContext.getCurrentInstance();
        Map<String, String> params = context.getExternalContext().getRequestParameterMap();
        accountId = Integer.parseInt(params.get("id"));


        if(account == null)
            account = accountService.findAccount(accountId);
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    private Collection<Account> accountList;

    public Logger getLogger() {
        return logger;
    }

    public void setLogger(Logger logger) {
        this.logger = logger;
    }

    public Collection<Account> getAccountList() {
        accountList = accountService.allAccounts();
        return accountList;
    }

    public void setAccountList(Collection<Account> accountList) {
        this.accountList = accountList;
    }

    public JsfMessage update(){
        System.out.println("--------------------------------");
        System.out.println(account.getAccountId());
        System.out.println(account.getEmail());
        System.out.println(account.getFirstName());
        System.out.println(account.getLastName());
        System.out.println(account.getPassword());
        System.out.println(account.getPermission());
        System.out.println(account.getSalt());
        System.out.println(account.getStatus());
        System.out.println(account.getUserName());
        System.out.println("----------------------------------");
        if(newPassword != null)
            account.setPassword(newPassword);
        accountService.updateAccount(account);
        return new JsfMessage("Account Updated!", "Account was successfully updated", JsfMessage.MessageType.SUCCESS);
    }

}
