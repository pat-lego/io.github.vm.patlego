package io.github.vm.patlego.sms.callback;

import com.twilio.rest.api.v2010.account.Message;

public interface SMSCallback {

    public default void sending(Message message) {
        // Do nothing because it is meant to be overriden
    }

    public default void sent(Message message) {
        // Do nothing because it is meant to be overriden
    }

    public default void failed(Message message) {
        // Do nothing because it is meant to be overriden
    }

    public default void delivered(Message message) {
        // Do nothing because it is meant to be overriden
    }

    public default void undelivered(Message message) {
        // Do nothing because it is meant to be overriden
    }

    public default void receiving(Message message) {
        // Do nothing because it is meant to be overriden
    }

    public default void received(Message message) {
        // Do nothing because it is meant to be overriden
    }

    public default void read(Message message) {
        // Do nothing because it is meant to be overriden
    }
}
