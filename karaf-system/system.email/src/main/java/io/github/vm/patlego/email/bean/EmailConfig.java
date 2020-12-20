package io.github.vm.patlego.email.bean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EmailConfig {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private String username;
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
