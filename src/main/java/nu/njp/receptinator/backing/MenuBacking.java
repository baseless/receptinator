package nu.njp.receptinator.backing;

import nu.njp.receptinator.core.AuthenticationProvider;
import nu.njp.receptinator.core.pojo.JsfMenuItem;
import nu.njp.receptinator.core.qualifier.DefaultLogger;
import org.slf4j.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Login backing bean
 * @author Daniel Ryhle <daniel@ryhle.se>
 */
@Named("menu")
@ApplicationScoped
public class MenuBacking {

    @Inject
    AuthenticationProvider authenticationProvider;

    @Inject @DefaultLogger
    private Logger logger;

    /**
     * Not so pretty way of generating menu links in top navbar
     * @return
     */
    public Collection<JsfMenuItem> getMenu() {
        Collection<JsfMenuItem> items = new ArrayList<>();
        String viewId = FacesContext.getCurrentInstance().getViewRoot().getViewId();
        if(authenticationProvider.isAuthenticated()) {
        } else {
            JsfMenuItem home = new JsfMenuItem("Home", "index");
            JsfMenuItem login = new JsfMenuItem("Login", "login");
            JsfMenuItem register = new JsfMenuItem("Create account", "register");
            switch(viewId) {
                case "/index.xhtml": home.setActive(true); break;
                case "/login.xhtml": login.setActive(true); break;
                case "/register.xhtml": register.setActive(true); break;
            }
            items.add(home);
            items.add(login);
            items.add(register);
        }
        return items;
    }
}
