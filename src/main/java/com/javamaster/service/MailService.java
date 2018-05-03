package com.javamaster.service;

import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

@Service
@PropertySource("classpath:application.properties")
public class MailService {

    @Resource
    private Environment env;

    private static final String PROP_MAIL_STARTTLS = "mail.smtp.starttls.enable";
    private static final String PROP_MAIL_SSL = "mail.smtp.ssl.trust";
    private static final String PROP_MAIL_USER = "mail.smtp.user";
    private static final String PROP_MAIL_PASSWORD = "mail.smtp.password";
    private static final String PROP_MAIL_PORT = "mail.smtp.port";
    private static final String PROP_MAIL_AUTH = "mail.smtp.auth";
    private static final String PROP_MAIL_HOST = "mail.smtp.host";
    private static final String PROP_LOCALHOST = "localhost";

    public void sendEmail(String mailTo) {

        String[] to = {mailTo};

        Properties properties = System.getProperties();

        properties.put(PROP_MAIL_STARTTLS, true);
        properties.put(PROP_MAIL_SSL, env.getRequiredProperty(PROP_MAIL_SSL));
        properties.put(PROP_MAIL_USER, env.getRequiredProperty(PROP_MAIL_USER));
        properties.put(PROP_MAIL_PASSWORD, env.getRequiredProperty(PROP_MAIL_PASSWORD));
        properties.put(PROP_MAIL_PORT, env.getRequiredProperty(PROP_MAIL_PORT));
        properties.put(PROP_MAIL_AUTH, env.getRequiredProperty(PROP_MAIL_AUTH));
        properties.put(PROP_MAIL_HOST, PROP_LOCALHOST);

        Session session = Session.getDefaultInstance(properties,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(
                                env.getRequiredProperty(PROP_MAIL_USER),
                                env.getRequiredProperty(PROP_MAIL_PASSWORD));
                    }
                });

        MimeMessage message = new MimeMessage(session);

        try {

            message.setFrom(new InternetAddress(env.getRequiredProperty(PROP_MAIL_USER)));
            InternetAddress[] toAddress = new InternetAddress[to.length];

            for (int i = 0; i < to.length; i++) {
                toAddress[i] = new InternetAddress(to[i]);
            }

            for (int i = 0; i < toAddress.length; i++) {
                message.addRecipient(Message.RecipientType.TO, toAddress[i]);
            }

            message.setSubject("Notification - jober");
            message.setContent(
                    "<img src='https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTXILbBYCMTgMvu56rjlmRk0iqZySEwS0gPCVi1gNQgU318RkyFPQ' />" +
                            "<h3 >Title</h3>" +
                            "<p> body text </p>"

                    , "text/html");

            Transport transport = session.getTransport("smtp");

            transport.connect(
                    env.getRequiredProperty(PROP_MAIL_SSL),
                    env.getRequiredProperty(PROP_MAIL_USER),
                    env.getRequiredProperty(PROP_MAIL_PASSWORD));

            transport.sendMessage(message, message.getAllRecipients());
            transport.close();

        } catch (AddressException ae) {
            ae.printStackTrace();
        } catch (MessagingException me) {
            me.printStackTrace();
        }
    }
}

