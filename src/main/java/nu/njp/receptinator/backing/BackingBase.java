package nu.njp.receptinator.backing;

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
}
