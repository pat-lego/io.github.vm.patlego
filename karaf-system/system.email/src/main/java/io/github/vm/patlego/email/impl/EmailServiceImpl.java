package io.github.vm.patlego.email.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.github.vm.patlego.email.EmailService;
import io.github.vm.patlego.email.bean.EmailConfig;
import io.github.vm.patlego.email.template.Templater;
import io.github.vm.patlego.enc.Security;

public class EmailServiceImpl implements EmailService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private EmailConfig emailConfig;
    private Security security;

    public void setSecurity(Security security) {
        this.security = security;
    }

    public void setEmailConfig(EmailConfig emailConfig) {
        this.emailConfig = emailConfig;
    }

    public void init() {
        logger.info(String.format("Starting the %s bean", this.getClass().getName()));
    }

    public void destroy() {
        logger.info(String.format("Destroying the %s service", this.getClass().getName()));
        this.emailConfig = null;
        logger.info(String.format("destroyed the %s service", this.getClass().getName()));
    }

    public String getUsername() {
        return this.emailConfig.getUsername();
    }

    public String getPassword() {
        return this.emailConfig.getPassword();
    }

    @Override
    public void send(Templater template) {
        // TODO Auto-generated method stub

    }
}
