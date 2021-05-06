package io.github.vm.patlego.sms.callback;

import com.twilio.rest.api.v2010.account.Message;

public interface SMSCallbackService {
    
    public String getStatus();

    public void add(Message message, SMSCallback callback);

    public void delete(Message message);

    public Boolean exists(Message message);
}
