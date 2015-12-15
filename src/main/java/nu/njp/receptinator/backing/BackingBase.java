package nu.njp.receptinator.backing;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Abstract base class for all backing beans
 * @author Daniel Ryhle <daniel@ryhle.se>
 */
public abstract class BackingBase {

    public enum MessageType { ERROR, WARNING, SUCCESS, INFORMATION }

    private String messageTitle;
    private String messageDescription;
    private MessageType messageType;

    public String getMessageTitle() {
        return messageTitle;
    }

    public void setMessageTitle(String messageTitle) {
        this.messageTitle = messageTitle;
    }

    public String getMessageDescription() {
        return messageDescription;
    }

    public void setMessageDescription(String messageDescription) {
        this.messageDescription = messageDescription;
    }

    public MessageType getMessageType() {
        return messageType;
    }

    public void setMessageType(MessageType messageType) {
        this.messageType = messageType;
    }

    protected FacesContext getContext() {
        return FacesContext.getCurrentInstance();
    }

    protected HttpServletRequest getRequest() { return (HttpServletRequest) getContext().getExternalContext().getRequest(); }

    protected void redirect(String path) throws IOException { getContext().getExternalContext().redirect(path); }
}
