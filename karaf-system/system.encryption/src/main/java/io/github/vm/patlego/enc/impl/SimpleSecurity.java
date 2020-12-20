package io.github.vm.patlego.enc.impl;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.github.vm.patlego.enc.Security;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.EnvironmentStringPBEConfig;

@Component(service = Security.class, immediate = true)
public class SimpleSecurity implements Security {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private StandardPBEStringEncryptor enc;

    @Override
    public String decrypt(String token) {
       return this.enc.decrypt(token);
    }

    @Override
    public String encrypt(String token) {
        return this.enc.encrypt(token);
    }

    @Activate
    public void activate() {
        logger.info(String.format("Instantiating %s service", this.getClass().getName()));
        enc = new StandardPBEStringEncryptor();
        EnvironmentStringPBEConfig env = new EnvironmentStringPBEConfig();
        env.setAlgorithm("PBEWithMD5AndDES");
        env.setPassword("ENCRYPTION_PASSWORD");
        enc.setConfig(env);
        logger.info(String.format("%s service has been created", this.getClass().getName()));
    }
    
}
