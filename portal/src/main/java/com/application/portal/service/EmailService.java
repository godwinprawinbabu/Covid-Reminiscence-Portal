package com.application.portal.service;

import com.sun.mail.smtp.SMTPTransport;
import org.springframework.stereotype.Service;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Properties;

import static com.application.portal.constant.EmailConstant.*;
import static javax.mail.Message.RecipientType.CC;
import static javax.mail.Message.RecipientType.TO;

@Service
public class EmailService {

    public void sendNewPasswordEmail(String firstName, String password, String email) throws MessagingException {
        Message message = createEmail(firstName, password, email);
        SMTPTransport smtpTransport = (SMTPTransport) getEmailSession().getTransport(SIMPLE_MAIL_TRANSFER_PROTOCOL);
        smtpTransport.connect(GMAIL_SMTP_SERVER, USERNAME, PASSWORD);
        smtpTransport.sendMessage(message, message.getAllRecipients());
        smtpTransport.close();
    }
    
    public void sendChangePasswordEmail(String firstName, String password, String email) throws MessagingException {
        Message message = createChangePasswordEmail(firstName, password, email);
        SMTPTransport smtpTransport = (SMTPTransport) getEmailSession().getTransport(SIMPLE_MAIL_TRANSFER_PROTOCOL);
        smtpTransport.connect(GMAIL_SMTP_SERVER, USERNAME, PASSWORD);
        smtpTransport.sendMessage(message, message.getAllRecipients());
        smtpTransport.close();
    }
    
    private Message createChangePasswordEmail(String firstName, String password, String email) throws MessagingException {
        Message message = new MimeMessage(getEmailSession());
        message.setFrom(new InternetAddress(FROM_EMAIL));
        message.setRecipients(TO, InternetAddress.parse(email, false));
        message.setRecipients(CC, InternetAddress.parse(CC_EMAIL, false));
        message.setSubject(CHANGE_PASSWORD_EMAIL_SUBJECT);
        message.setText("Hello " + firstName + ", \n \nYou have changed your password \n \n Your new account password is: " + password + "\n \n The Support Team");
        message.setSentDate(new Date());
        message.saveChanges();
        return message;
    }

    private Message createEmail(String firstName, String password, String email) throws MessagingException {
        Message message = new MimeMessage(getEmailSession());
        message.setFrom(new InternetAddress(FROM_EMAIL));
        message.setRecipients(TO, InternetAddress.parse(email, false));
        message.setRecipients(CC, InternetAddress.parse(CC_EMAIL, false));
        message.setSubject(EMAIL_SUBJECT);
        message.setText("Hello " + firstName + ", \n \n Your new account password is: " + password + "\n \n The Support Team");
        message.setSentDate(new Date());
        message.saveChanges();
        return message;
    }
    
    public void sendNewAppointmentEmail(Long id, String date, String slot, String centre,String name,String email, String vaccine, Integer count) throws MessagingException {
        Message message = createAppointmentEmail(id, date, slot, centre, name,email,vaccine,count);
        SMTPTransport smtpTransport = (SMTPTransport) getEmailSession().getTransport(SIMPLE_MAIL_TRANSFER_PROTOCOL);
        smtpTransport.connect(GMAIL_SMTP_SERVER, USERNAME, PASSWORD);
        smtpTransport.sendMessage(message, message.getAllRecipients());
        smtpTransport.close();
    }

    private Message createAppointmentEmail(Long id,String date, String slot, String centre,String name,String email, String vaccine, Integer count) throws MessagingException {
        Message message = new MimeMessage(getEmailSession());
        message.setFrom(new InternetAddress(FROM_EMAIL));
        message.setRecipients(TO, InternetAddress.parse(email, false));
        message.setRecipients(CC, InternetAddress.parse(CC_EMAIL, false));
        message.setSubject(APPOINTMENT_EMAIL_SUBJECT);
        message.setText("Hi " + name + ", \n \n Your "+vaccine+ " vaccine appointment for " +count+ " individual is booked with booking ID: " + id + "\n \n on " +date+"\n \n at " +slot+ "\n \n in " +centre+"\n \n Please Carry your Aadhaar card and show the booking id to get your Vaccine. ");
        message.setSentDate(new Date());
        message.saveChanges();
        return message;
    }

    private Session getEmailSession() {
        Properties properties = System.getProperties();
        properties.put(SMTP_HOST, GMAIL_SMTP_SERVER);
        properties.put(SMTP_AUTH, true);
        properties.put(SMTP_PORT, DEFAULT_PORT);
        properties.put(SMTP_STARTTLS_ENABLE, true);
        properties.put(SMTP_STARTTLS_REQUIRED, true);
        return Session.getInstance(properties, null);
    }
}

