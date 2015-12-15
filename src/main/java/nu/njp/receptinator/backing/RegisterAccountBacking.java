package nu.njp.receptinator.backing;

import nu.njp.receptinator.entity.Account;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.persistence.Basic;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Register backing bean
 * @author Daniel Ryhle <daniel@ryhle.se>
 */
@Named("registerAccount")
@RequestScoped
public class RegisterAccountBacking {

    @Named("newAccount")
    @Produces
    @RequestScoped
    private Account account = new Account();

    private String errorTitle;
    private String errorDescription;

    @Basic(optional = false)
    @NotNull
    @Size(min = 6, max = 200)
    private String passwordConfirm;

    public String save() {
        //save account
        //If successful

        return null;
    }

    public String getErrorTitle() {
        return errorTitle;
    }

    public void setErrorTitle(String errorTitle) {
        this.errorTitle = errorTitle;
    }

    public String getErrorDescription() {
        return errorDescription;
    }

    public void setErrorDescription(String errorDescription) {
        this.errorDescription = errorDescription;
    }

    public String getPasswordConfirm() {
        return passwordConfirm;
    }

    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
    }
}
