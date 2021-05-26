package io.github.vm.patlego.sms.sender;

import java.util.HashMap;
import java.util.Map;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;

import org.osgi.service.component.ComponentContext;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.github.vm.patlego.enc.Security;
import io.github.vm.patlego.sms.sender.bean.SMSMessage;

@Component(service = SMSService.class, immediate = true, configurationPid = "io.github.vm.patlego.sms.SMSService")
public class SimpleSMSService implements SMSService {

    private ComponentContext context;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Reference
    private Security security;

    @Override
    public Map<String, Message> sendMessage(SMSMessage smsMessage) {
        Map<String, Message> messageMap = new HashMap<>();
        Twilio.init(this.getSid(), this.getToken());
        smsMessage.getNumbers().forEach(phoneNumber -> {
            try {
                Message msg = Message.creator(new com.twilio.type.PhoneNumber(phoneNumber), getFrom(), smsMessage.getMessage()).create();
                messageMap.put(phoneNumber, msg);
            } catch (Exception e) {
                logger.error(String.format("Failed to send sms to %s", phoneNumber), e);
            }
            
        });

        return messageMap;
    }

    @Activate
    protected void activate(ComponentContext context) {
        this.context = context;
    }

    @Modified
    protected void modified(ComponentContext context) {
        this.context = context;
    }

    @Deactivate
    protected void deactivate(ComponentContext context) {
        this.context = null;
    }

    private String getToken() {
        return this.security.decrypt(context.getProperties().get("account.token").toString());
    }

    private String getSid() {
        return this.security.decrypt(context.getProperties().get("account.sid").toString());
    }

    private com.twilio.type.PhoneNumber getFrom() {
        return new com.twilio.type.PhoneNumber(context.getProperties().get("msg.from").toString());
    }
    
}
