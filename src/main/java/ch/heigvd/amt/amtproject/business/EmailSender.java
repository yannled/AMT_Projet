package ch.heigvd.amt.amtproject.business;

import ch.heigvd.amt.amtproject.model.User;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;

@Stateless
public class EmailSender {

    @Resource(name = "mail/amt")
    Session mailSession;

    public void sendNewPassword(User user, String password) throws MessagingException, UnsupportedEncodingException{
        Message message = new MimeMessage(mailSession);

        message.setSubject("Password Reset");
        message.setRecipient(Message.RecipientType.TO, new InternetAddress(user.getEmail(), user.getName() + " " + user.getLastName()));
        message.setText("Hello,\n"+
                "Your new password is : " + password);

        Transport.send(message);
    }

}