package nu.njp.receptinator.backing;

import nu.njp.receptinator.core.pojo.JsfMessage;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Abstract base class for all backing beans
 * @author Daniel Ryhle <daniel@ryhle.se>
 */
public abstract class BackingBase {

    private JsfMessage message = null;

    public JsfMessage getMessage() {
        return message;
    }

    public void setMessage(JsfMessage message) {
        this.message = message;
    }

    protected FacesContext getContext() {
        return FacesContext.getCurrentInstance();
    }

    protected HttpServletRequest getRequest() { return (HttpServletRequest) getContext().getExternalContext().getRequest(); }

    protected void redirect(String path) throws IOException { getContext().getExternalContext().redirect(path); }
}
