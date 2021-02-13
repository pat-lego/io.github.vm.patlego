package io.github.vm.patlego.mail.bean;

/**
 * This class will be populated using Apache Blueprint. Properties will be
 * placed within /etc in order to provide the requested properties into the bean
 */
public class SmtpAuthentication {

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
