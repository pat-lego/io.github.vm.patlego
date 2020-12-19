package io.github.vm.patlego.email.impl;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.metatype.annotations.Designate;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;

import io.github.vm.patlego.email.EmailService;

@Component(service = EmailService.class, immediate = true)
@Designate(ocd = EmailServiceImpl.SampleConfig.class)
public class EmailServiceImpl implements EmailService {

    @Override
    public void send() {
        // TODO Auto-generated method stub

    }

    @ObjectClassDefinition(name = "Sample Configuration", pid = "io.github.vm.patlego.email.impl")
    public @interface SampleConfig {
        String name() default "default";
        int intProperty() default 0;
        boolean booleanProperty() default false;
    }

}
