package io.github.vm.patlego.mail.bean;

public class SmtpServer {

    private String smtpHost;
    private Integer smtpPort;
    private String smtpProtocol;

    /**
     * This class will be populated using Apache Blueprint. Properties will be
     * placed within /etc in order to provide the requested properties into the bean
     */
    public String getSmtpHost() {
        return smtpHost;
    }

    public void setSmtpHost(String smtpHost) {
        this.smtpHost = smtpHost;
    }

    public Integer getSmtpPort() {
        return smtpPort;
    }

    public void setSmtpPort(Integer smtpPort) {
        this.smtpPort = smtpPort;
    }

    public String getSmtpProtocol() {
        return smtpProtocol;
    }

    public void setSmtpProtocol(String smtpProtocol) {
        this.smtpProtocol = smtpProtocol;
    }

}
