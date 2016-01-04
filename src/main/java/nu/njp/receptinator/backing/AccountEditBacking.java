package nu.njp.receptinator.backing;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import nu.njp.receptinator.core.pojo.JsfMessage;
import nu.njp.receptinator.core.qualifier.DefaultLogger;
import nu.njp.receptinator.entities.Account;
import nu.njp.receptinator.interfaces.AccountServiceLocal;
import org.slf4j.Logger;

import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.Basic;
import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.Map;

/**
 * Created by Mattias & Daniel on 2016-01-03.
 */
@Named("accountEdit")
@ViewScoped
public class AccountEditBacking extends BackingBase{
    @Inject
    AccountServiceLocal accountService;

    private Account account;

    @Basic(optional = true)
    @Size(min = 6, max = 200)
    private String newPassword;

    private int accountId = 0;

    public AccountEditBacking() {
        if(accountId == 0) {
            try {
                Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
                accountId = Integer.parseInt(params.get("id"));
            } catch(Exception e) {}
        }
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public Account getAccount() {
        if(accountId != 0) {
            account = accountService.findAccount(accountId);
        }
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public String update() {
        account.setPassword(newPassword);
        accountService.updateAccount(account);
        return null;
    }
}