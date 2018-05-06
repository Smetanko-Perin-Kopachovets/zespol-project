package com.javamaster.service.gmail;

import org.springframework.stereotype.Component;

import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import java.util.Properties;

@Component
public class GmailAuthenticator {

    public Session getSession(Properties properties, final String PROP_MAIL_USER, final String PROP_MAIL_PASSWORD) {
        return Session.getDefaultInstance(properties,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(PROP_MAIL_USER, PROP_MAIL_PASSWORD);
                    }
                });
    }
}
