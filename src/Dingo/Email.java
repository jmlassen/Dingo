/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dingo;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;

/**
 *
 * @author HEIDY2016
 */

public class Email {
    private String from;  // GMail user name (just the part before "@gmail.com")
    private String pass;  // GMail password
    private String to;    // Email recipient does not have to be from gmail
    private String subject;
    private String body;
    
    public Email(String body, String to ) {
        this.from = PropertyManager.getProperty("dropbox.senderUsername");
        this.pass = PropertyManager.getProperty("dropbox.senderPassword");         
        this.subject = "Alert Notification from Dingo";
        this.body = body;
        this.to = to;
    }
    
//    public static void main(String[] args) {
//        
//        Email email = new Email("It works!", "cespedesheidy@gmail.com");
//        email.run();
//    }
    
    public void run() {
        Properties props = System.getProperties();
        String host = "smtp.gmail.com";
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.user", from);
        props.put("mail.smtp.password", pass);
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");

        Session session = Session.getDefaultInstance(props);
        MimeMessage message = new MimeMessage(session);
        
        try {
            message.setFrom(new InternetAddress(from));
            InternetAddress toAddress = new InternetAddress(to);            
            
            message.addRecipient(Message.RecipientType.TO, toAddress);
            message.setSubject(subject);
            message.setText(body);
            Transport transport = session.getTransport("smtp");
            transport.connect(host, from, pass);
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
        }
        catch (AddressException ae) {
            ae.printStackTrace();
        }
        catch (MessagingException me) {
            me.printStackTrace();
        }
    }
    
}