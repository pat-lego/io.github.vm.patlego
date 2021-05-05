package io.github.vm.patlego.sms;

import java.util.concurrent.Future;

import com.twilio.rest.api.v2010.account.Message;

public interface SMSService<T> extends Future<T> {
    
    public Message sendMessage();
}
