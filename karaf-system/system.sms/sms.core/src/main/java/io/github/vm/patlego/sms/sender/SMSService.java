package io.github.vm.patlego.sms.sender;

import java.util.Map;

import com.twilio.rest.api.v2010.account.Message;

import io.github.vm.patlego.sms.sender.bean.SMSMessage;

public interface SMSService {
    
    /**
     * 
     * @param smsMessage
     * @return Returns the to number linked to the message object sent
     */
    public Map<String, Message> sendMessage(SMSMessage smsMessage);
}
