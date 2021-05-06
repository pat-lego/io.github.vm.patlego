package io.github.vm.patlego.sms.callback;

import com.twilio.rest.api.v2010.account.Message;

public interface SMSCallbackHandler {
    
    public String getStatus();

    public void add(Message message);

    public void delete(Message message);

    public Boolean exists(Message message);
}
