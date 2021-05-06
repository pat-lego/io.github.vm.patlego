package io.github.vm.patlego.sms.sender.bean;

import java.util.List;

public interface SMSMessage {

    public String getMessage();

    public List<String> getNumbers();

}
