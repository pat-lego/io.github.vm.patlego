package patlego.vm.github.io.client;

import org.osgi.service.component.annotations.Component;

@Component(immediate = true, 
    service = SystemClientResource.class, 
    property = { 
        "osgi.http.whiteboard.resource.pattern=/client/*",
        "osgi.http.whiteboard.resource.prefix=/client"
    })
public class SystemClientResource {

}