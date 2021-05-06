package io.github.vm.patlego.sms.callback;

import com.twilio.rest.api.v2010.account.Message;

public class SMSCallback {

    public void sending(Message message) {
        // Do nothing because it is meant to be overriden
    }

    public void sent(Message message) {
        // Do nothing because it is meant to be overriden
    }

    public void failed(Message message) {
        // Do nothing because it is meant to be overriden
    }

    public void delivered(Message message) {
        // Do nothing because it is meant to be overriden
    }

    public void undelivered(Message message) {
        // Do nothing because it is meant to be overriden
    }

    public void receiving(Message message) {
        // Do nothing because it is meant to be overriden
    }

    public void received(Message message) {
        // Do nothing because it is meant to be overriden
    }

    public void read(Message message) {
        // Do nothing because it is meant to be overriden
    }
}
