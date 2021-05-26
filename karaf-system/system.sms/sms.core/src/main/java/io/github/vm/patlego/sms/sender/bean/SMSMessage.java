package io.github.vm.patlego.sms.sender.bean;

import java.util.Set;

public interface SMSMessage {

    public String getMessage();

    public Set<String> getNumbers();

}
