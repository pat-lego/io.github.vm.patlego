package patlego.vm.github.io.logger;

import org.ops4j.pax.logging.spi.PaxAppender;
import org.ops4j.pax.logging.spi.PaxLoggingEvent;
import org.osgi.service.component.annotations.Component;

@Component(immediate = true, property = { "org.ops4j.pax.logging.appender.name=Karaf VM Logger" })
public class KarafVMLogger implements PaxAppender {

    @Override
    public void doAppend(PaxLoggingEvent loggingEvent) {
        System.out.println(this.getMessage(loggingEvent));
    }

    /**
     * Function ussed to get the message to add to the system
     * @param loggingEvent
     * @return
     */
    private String getMessage(PaxLoggingEvent loggingEvent) {
        try {
            if (loggingEvent != null) {
                return String.format("LOGGER - %s, LEVEL - %s, TIME - %d, MSG - %s", 
                        loggingEvent.getLoggerName(), 
                        loggingEvent.getLevel(), 
                        loggingEvent.getTimeStamp(), 
                        loggingEvent.getRenderedMessage());
            } else {
                return String.format("Logger is null and the error message has been lost in the transaction");
            }
        } catch(Exception e) {
            return "An exception occured in retrieving the error message please review the system properties for the `Karaf VM Logger` immediately";
        }
        
    }
    
}