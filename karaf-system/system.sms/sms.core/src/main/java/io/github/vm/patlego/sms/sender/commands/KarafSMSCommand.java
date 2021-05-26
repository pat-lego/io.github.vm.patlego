package io.github.vm.patlego.sms.sender.commands;

import org.apache.commons.lang3.StringUtils;
import org.apache.karaf.shell.api.action.Action;
import org.apache.karaf.shell.api.action.Argument;
import org.apache.karaf.shell.api.action.Command;
import org.apache.karaf.shell.api.action.lifecycle.Reference;
import org.apache.karaf.shell.api.action.lifecycle.Service;

import io.github.vm.patlego.sms.sender.SMSService;

@Service
@Command(scope = "sms", name = "send", description = "Send a SMS to a given user")
public class KarafSMSCommand implements Action {

    @Argument(index = 0, name = "to", description = "Phone Number to send msg to", required = true, multiValued = false)
    public String to = StringUtils.EMPTY;

    @Argument(index = 1, name = "message", description = "Message to be sent to client", required = true, multiValued = false)
    public String msg = StringUtils.EMPTY;

    @Reference
    private SMSService smsService;

    @Override
    public Object execute() throws Exception {
        SimpleSMSMessage smsMsg = new SimpleSMSMessage(msg, to);
        this.smsService.sendMessage(smsMsg);

        return String.format("Sent %s to %s please wait for the message to be received", this.msg, this.to);
    }
    
}
