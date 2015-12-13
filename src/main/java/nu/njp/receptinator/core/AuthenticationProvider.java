package nu.njp.receptinator.core;

import nu.njp.receptinator.entity.Account;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.io.IOException;
import java.io.Serializable;

/**
 * Created by base on 2015-12-13.
 */
@Named
@SessionScoped
public class AuthenticationProvider implements Serializable {

    //@Inject
    //AuthenticationService authenticationService;

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
}
