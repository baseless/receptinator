package nu.njp.receptinator.backing;

import nu.njp.receptinator.entities.Account;
import nu.njp.receptinator.interfaces.AccountServiceLocal;

import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.Collection;

/**
 * AccountList backing bean
 * @author Daniel Ryhle <daniel@ryhle.se>
 */
@Named("accountList")
@RequestScoped
public class AccountListBacking {

    private Collection<Account> accountList;

    @Inject
    AccountServiceLocal accountService;

    public Collection<Account> getAccountList() {
        if(accountList == null) {
            accountList = accountService.allAccounts();
        }
        return accountList;
    }

    public void setAccountList(Collection<Account> accountList) {
        this.accountList = accountList;
    }
}
