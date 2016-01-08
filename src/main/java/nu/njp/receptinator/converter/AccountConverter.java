package nu.njp.receptinator.converter;

import nu.njp.receptinator.entities.Account;
import nu.njp.receptinator.interfaces.AccountServiceLocal;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.inject.Inject;

/**
 * AccountList backing bean
 * @author Daniel Ryhle <daniel@ryhle.se>
 */
@ManagedBean
@ViewScoped
public class AccountConverter implements Converter {

    @Inject
    AccountServiceLocal accountService;

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String s) {
        if (s == null || s.isEmpty()) { return null; }

        try {
            int id = Integer.valueOf(s);
            return accountService.findAccount(id);
        } catch (NumberFormatException e) { throw new ConverterException("Not a valid Account ID: " + s, e); }
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object o) {
        if (o == null) { return ""; }

        if (o instanceof Account) {
            int id = ((Account)o).getAccountId();
            return (id != 0) ? String.valueOf(id) : null;
        } else { throw new ConverterException("Id not corresponding to an Account instance: " + o); }
    }
}
