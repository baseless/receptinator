package nu.njp.receptinator.backing;

import nu.njp.receptinator.core.pojo.JsfMessage;
import nu.njp.receptinator.core.qualifier.DefaultLogger;
import nu.njp.receptinator.entities.Account;
import nu.njp.receptinator.interfaces.AccountServiceLocal;
import org.slf4j.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.persistence.Basic;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * AccountList backing bean
 * @author Daniel Ryhle <daniel@ryhle.se>;
 */
@ManagedBean
@ViewScoped
public class AccountEditBacking extends BackingBase{

    @Inject
    @DefaultLogger
    private Logger logger;

    @Inject
    AccountServiceLocal accountService;

    private Account account = new Account();

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public String update() {
        System.out.println("-----------UPDATE---------------");
        JsfMessage result = accountService.updateAccount(account);
        setMessage(result);
        return null;
    }

    public String delete() {
        System.out.println("------------DELETE--------------");
        JsfMessage result = accountService.removeAccount(account.getAccountId());
        if(!result.getMessageType().equals(JsfMessage.MessageType.SUCCESS)) {
            setMessage(result);
            return null;
        } else {
            return "list";
        }
    }

    private Account.Status[] statuses;

    public Account.Status[] getStatuses() { return Account.Status.values(); }

    public void setStatuses(Account.Status[] statuses) {}

    private Account.Permission[] permissions;

    public Account.Permission[] getPermissions() { return Account.Permission.values(); }

    public void setPermissions(Account.Permission[] permissions) {}
}