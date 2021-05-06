package io.github.vm.patlego.sms.sender;

import java.util.concurrent.Future;

import com.twilio.rest.api.v2010.account.Message;

import io.github.vm.patlego.sms.sender.bean.SMSMessage;

public interface SMSService {
    
    public Message sendMessage(SMSMessage smsMessage);
}
