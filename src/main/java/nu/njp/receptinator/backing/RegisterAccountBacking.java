package nu.njp.receptinator.backing;

import nu.njp.receptinator.entity.Account;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.io.IOException;

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

    public void saveAccount() {
        //save account
        //If successful
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("member/index.xhtml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
