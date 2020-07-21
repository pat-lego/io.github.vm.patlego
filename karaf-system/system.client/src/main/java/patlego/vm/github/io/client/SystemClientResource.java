package patlego.vm.github.io.client;

import org.osgi.service.component.annotations.Component;

@Component(service = SystemClientResource.class, 
    property = { 
        "osgi.http.whiteboard.resource.pattern=/client/*",
        "osgi.http.whiteboard.resource.prefix=/client"
    })
public class SystemClientResource {

}