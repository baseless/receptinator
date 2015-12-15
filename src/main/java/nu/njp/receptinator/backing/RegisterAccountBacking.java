package nu.njp.receptinator.backing;

import nu.njp.receptinator.core.JsfMessage;
import nu.njp.receptinator.entities.Account;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
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
public class RegisterAccountBacking extends BackingBase {

    @Named("newAccount")
    @Produces
    @RequestScoped
    private Account account = new Account();

    @Basic(optional = false)
    @NotNull
    @Size(min = 6, max = 200)
    private String passwordConfirm;

    public String save() {
        //setMessageTitle("Username taken!");
        //setMessageDescription("Selected username already taken, plase select another.");
        //setMessageType(MessageType.ERROR);
        setMessage(new JsfMessage("Account created!", "Account successfully created, please login.", JsfMessage.MessageType.SUCCESS));
        return null;
    }

    public String getPasswordConfirm() {
        return passwordConfirm;
    }

    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
    }
}
