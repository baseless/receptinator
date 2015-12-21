package nu.njp.receptinator.backing;

import nu.njp.receptinator.core.EmailProvider;
import nu.njp.receptinator.core.pojo.JsfMessage;
import nu.njp.receptinator.core.qualifier.DefaultLogger;
import org.slf4j.Logger;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.Basic;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.IOException;

/**
 * Password recovery backing bean
 * @author Daniel Ryhle <daniel@ryhle.se>
 */
@Named("passwordRecovery")
@RequestScoped
public class PasswordRecoveryBacking extends BackingBase {

    @Inject
    EmailProvider emailProvider;

    @Inject @DefaultLogger
    private Logger logger;

    @Basic(optional = false)
    @NotNull
    @Size(min = 5, max = 300)
    @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message = "Invalid email format")
    private String emailAddress;

    public String recover() {
        if(emailProvider.emailSender(emailAddress)) {
            try {
                redirect("faces/login.xhtml"); } catch (IOException e) { logger.error(e.getMessage()); }
        } else {
            setMessage(new JsfMessage("Error!", "Email does not exist..", JsfMessage.MessageType.ERROR));
        }
        /*
        setMessageTitle("Email not found!");
        setMessageDescription("Entered email address not found in database");
        setMessageType(MessageType.ERROR);
        setMessage(new JsfMessage("Request approved!", "An email with further instructions has been sent to you.", JsfMessage.MessageType.SUCCESS));
        */
        return null;
    }



    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }


}
