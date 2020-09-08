package patlego.vm.github.io.client;

import org.osgi.service.component.annotations.Component;

/**
 * This resource will allow URL's to navigate straight to the profile/contact page and not have to click on 
 * the Contact button to be routed to the Contact page
 */
@Component(immediate = true, 
    service = ContactProfileResource.class, 
    property = { 
        "osgi.http.whiteboard.resource.pattern=/profile/contact/*", 
        "osgi.http.whiteboard.resource.prefix=/profile"
    })
public class ContactProfileResource {

}