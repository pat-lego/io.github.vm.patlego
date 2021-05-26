package io.github.vm.patlego.sms.sender.commands;

import java.util.HashSet;
import java.util.Set;

import io.github.vm.patlego.sms.sender.bean.SMSMessage;

public class SimpleSMSMessage implements SMSMessage {

    private String msg;
    private String to;

    public SimpleSMSMessage(String msg, String to) {
        this.msg = msg;
        this.to = to;
    }

    @Override
    public String getMessage() {
        return this.msg;
    }

    @Override
    public Set<String> getNumbers() {
        Set<String> toSet = new HashSet<>();
        toSet.add(this.to);

        return toSet;
    }
    
}
