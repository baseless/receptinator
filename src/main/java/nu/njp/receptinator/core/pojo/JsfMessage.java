package nu.njp.receptinator.core.pojo;

/**
 * Pojo class for managing JSF form messages
 * @author Daniel Ryhle <daniel@ryhle.se>
 */
public class JsfMessage {

    public enum MessageType { ERROR, WARNING, SUCCESS, INFORMATION }

    private String messageTitle;
    private String messageDescription;
    private MessageType messageType;

    public JsfMessage() {}

    public JsfMessage(String messageTitle, String messageDescription, MessageType messageType) {
        this.messageTitle = messageTitle;
        this.messageDescription = messageDescription;
        this.messageType = messageType;
    }

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
}
