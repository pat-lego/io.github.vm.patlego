package patlego.vm.github.io.client;

import org.osgi.service.component.annotations.Component;

@Component(immediate = true, 
    service = PersonalProfileResource.class, 
    property = { 
        "osgi.http.whiteboard.resource.pattern=/profile/*",
        "osgi.http.whiteboard.resource.prefix=/profile"
    })
public class PersonalProfileResource {

}