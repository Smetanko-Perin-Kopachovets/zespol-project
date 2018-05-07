package com.javamaster.service.gmail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
@PropertySource("classpath:application.properties")
public class MailService {

    @Autowired
    private GmailAuthenticator gmailAuthenticator;

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

    public void sendEmail(String mailTo, String subject) {

        String[] arrayTo = {mailTo};

        Properties properties = System.getProperties();

        properties.put(PROP_MAIL_STARTTLS, true);
        properties.put(PROP_MAIL_SSL, env.getRequiredProperty(PROP_MAIL_SSL));
        properties.put(PROP_MAIL_USER, env.getRequiredProperty(PROP_MAIL_USER));
        properties.put(PROP_MAIL_PASSWORD, env.getRequiredProperty(PROP_MAIL_PASSWORD));
        properties.put(PROP_MAIL_PORT, env.getRequiredProperty(PROP_MAIL_PORT));
        properties.put(PROP_MAIL_AUTH, env.getRequiredProperty(PROP_MAIL_AUTH));
        properties.put(PROP_MAIL_HOST, PROP_LOCALHOST);

        Session session = gmailAuthenticator.getSession(properties,
                env.getRequiredProperty(PROP_MAIL_USER),
                env.getRequiredProperty(PROP_MAIL_PASSWORD));

        MimeMessage message = new MimeMessage(session);

        try {

            message.setFrom(new InternetAddress(env.getRequiredProperty(PROP_MAIL_USER)));
            List<InternetAddress> toAddress = new ArrayList<>();

            Arrays.stream(arrayTo).forEach(string -> {
                try {
                    toAddress.add(new InternetAddress(string));
                    message.addRecipient(Message.RecipientType.TO, new InternetAddress(string));
                } catch (MessagingException e) {
                    e.printStackTrace();
                }
            });

//            for (int i = 0; i < toAddress.size(); i++) {
//                message.addRecipient(Message.RecipientType.TO, toAddress.get(i));
//            }

            message.setSubject(subject);
            message.setContent(
                    "<img src='https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTXILbBYCMTgMvu56rjlmRk0iqZySEwS0gPCVi1gNQgU318RkyFPQ' />" +
                            "<h3 > Title </h3>" +
                            "<p> Body </p>" +
                            "<a href='http://localhost:8088/'> <button> Service </button> </a>" +
                            ""

                    , "text/html");

            Transport transport = session.getTransport("smtp");

            transport.connect(
                    env.getRequiredProperty(PROP_MAIL_SSL),
                    env.getRequiredProperty(PROP_MAIL_USER),
                    env.getRequiredProperty(PROP_MAIL_PASSWORD));

            transport.sendMessage(message, message.getAllRecipients());
            transport.close();

        } catch (MessagingException me) {
            me.printStackTrace();
        }
    }
}

