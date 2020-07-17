package patlego.vm.github.io.datasource.pojo;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import org.apache.aries.jpa.template.JpaTemplate;
import org.apache.aries.jpa.template.TransactionType;

@Component(service = BookingService.class, immediate = true)
public class BookingServiceImpl implements BookingService {
    @Reference(target = "(osgi.unit.name=karafdb-openjpa)")
    private JpaTemplate jpaTemplate;


    @Override
    public void add(Booking booking) {
        jpaTemplate.tx(entityManager -> {
            entityManager.persist(booking);
            entityManager.flush();
        });
    }
    
}