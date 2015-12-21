package nu.njp.receptinator.core.util;

import nu.njp.receptinator.entities.Account;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.ws.rs.POST;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Properties;

/**
 * Created by Andreas on 2015-12-18.
 */
public class PostMail {

    private final static String RECEPTINATORUSERNAME = "receptinator@gmail.com";
    private final static String RECEPTINATORPASSWORD ="Hejsan1234";
    private String CRLF = "\r\n";
    private SecureRandom random = new SecureRandom();
    private String newPassword;
    private Account recipient;
    private Properties properties;
    private Session session;
    private MimeMessage message;
    private String textMessage;

    public PostMail(){
        properties = System.getProperties();
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.socketFactory.port", "587");
        properties.put("mail.smtp.socketFactory.class",
                "javax.net.ssl.SSLSocketFactory");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.port", "587");
        properties.put("mail.debug", "true");
        properties.put("mail.smtp.starttls.enable", "true");

        session = Session.getDefaultInstance(properties,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(RECEPTINATORUSERNAME,RECEPTINATORPASSWORD);
                    }
                });


    }

    public void setRecipient(Account account) {
        recipient = account;
        try {
            message = new MimeMessage(session);
            message.setFrom(new InternetAddress(RECEPTINATORUSERNAME));
            message.setRecipients(Message.RecipientType.TO,InternetAddress.parse(account.getEmail()));
            textMessage = textToClient();
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    public void sendMessage() {
        String subject = "Receptinator forgotten password here to assist";
        try {
            message.setSubject(subject);
            message.setText(textMessage);
            Transport.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    private String textToClient() {
        setNewPassword();
        return "Hey " + recipient.getUserName() + CRLF +
            "Dear " + recipient.getFirstName() + " " + recipient.getLastName() + CRLF +
            "Arnold Schwarzenegger has granted you a new password! " + CRLF +
            "Your new password is: " + newPassword;
    }

    private String newPasswordGenerator() {
        return new BigInteger(130, random).toString(32);
    }

    private void setNewPassword() {
        this.newPassword = newPasswordGenerator();
    }

    public String getNewPassword() {
        return newPassword;
    }
}
