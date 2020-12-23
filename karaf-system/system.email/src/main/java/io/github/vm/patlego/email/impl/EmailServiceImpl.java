package io.github.vm.patlego.email.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.github.vm.patlego.email.EmailService;
import io.github.vm.patlego.email.bean.SmtpAuthentication;
import io.github.vm.patlego.email.bean.SmtpServer;
import io.github.vm.patlego.email.bean.EmailContent;
import io.github.vm.patlego.email.bean.EmailRecipient;
import io.github.vm.patlego.email.template.Templater;
import io.github.vm.patlego.enc.Security;

public class EmailServiceImpl implements EmailService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public SmtpAuthentication smtpAuthentication;
    public SmtpServer smtpServer;
    public Security security;

    public void setSecurity(Security security) {
        this.security = security;
    }

    public void setSmtpServer(SmtpServer smtpServer) {
        this.smtpServer = smtpServer;
    }

    public void setSmtpAuthentication(SmtpAuthentication smtpAuthentication) {
        this.smtpAuthentication = smtpAuthentication;
    }

    public void init() {
        logger.info(String.format("Starting the %s bean", this.getClass().getName()));
        logger.info("smtpServer set to " + this.smtpAuthentication.getUsername());
        logger.info("smtpServer set to " + this.smtpServer.getSmtpPort());
    }

    public void destroy() {
        logger.info(String.format("Destroying the %s service", this.getClass().getName()));
        this.smtpAuthentication = null;
        logger.info(String.format("destroyed the %s service", this.getClass().getName()));
    }

    public String getUsername() {
        return this.smtpAuthentication.getUsername();
    }

    public String getPassword() {
        return this.security.decrypt(this.smtpAuthentication.getPassword());
    }

    @Override
    public void send(EmailRecipient recipients, Templater template, EmailContent content) {
        // TODO Auto-generated method stub

    }
}
