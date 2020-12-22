package io.github.vm.patlego.email;

import io.github.vm.patlego.email.template.Templater;

public interface EmailService {

    public void send(Templater template);
    
}
