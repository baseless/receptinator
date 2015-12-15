package nu.njp.receptinator.core;

import nu.njp.receptinator.entity.Account;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;

/**
 * Authentication provider
 * @author Daniel Ryhle <daniel@ryhle.se>
 */
@Named
@SessionScoped
public class AuthenticationProvider implements Serializable {

    private Account account = null;

    public boolean isAuthenticated() {
        /*
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            context.getExternalContext().redirect("http://www.msn.com");
        } catch (IOException e) {
            e.printStackTrace();
        } */
        return account != null;
    }

    public boolean authenticate(String userName, String password) {
        return  false;
    }
}
