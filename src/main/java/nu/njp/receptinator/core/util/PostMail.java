package nu.njp.receptinator.core.util;

import nu.njp.receptinator.entities.Account;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
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



    public void getAccountCredentials(Account account) {
        Properties properties = System.getProperties();
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.socketFactory.port", "465");
        properties.put("mail.smtp.socketFactory.class",
                "javax.net.ssl.SSLSocketFactory");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.port", "465");



        Session session = Session.getDefaultInstance(properties,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(RECEPTINATORUSERNAME,RECEPTINATORPASSWORD);
                    }
                });
        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(RECEPTINATORUSERNAME));
            message.setRecipients(Message.RecipientType.TO,InternetAddress.parse(account.getEmail()));
            sendMessage(message, textToClient(account));

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    private void sendMessage(Message message, String text) throws MessagingException {
        String subject = "Receptinator forgotten password here to assist";
        message.setSubject(subject);
        message.setText(text);
        Transport.send(message);
    }

    private String textToClient(Account account) {
        setNewPassword();
        return "Hey" + account.getUserName() + CRLF +
            "Dear " + account.getFirstName() + " " + account.getLastName() + CRLF +
            "Arnold Schwarzenegger has granted you a new password! " + CRLF +
            "Your new password is: " + newPassword;
    }

    private String newPasswordGenerator() {
        return new BigInteger(130, random).toString(32);
    }

    public void setNewPassword() {
        this.newPassword = newPasswordGenerator();
    }

    public String getNewPassword() {
        return newPassword;
    }
}
