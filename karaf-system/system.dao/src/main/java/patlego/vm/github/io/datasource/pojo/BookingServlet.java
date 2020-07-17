package patlego.vm.github.io.datasource.pojo;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.aries.jpa.template.JpaTemplate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(
    property = {
        "alias=/booking-servlet", "servlet-name=Example Servlet"
    }
)
public class BookingServlet extends HttpServlet implements Servlet {
   
    @Reference
    BookingService bookingService;

    
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Booking b = new Booking();
        b.setFlight("Away");
        b.setCustomer("Pat");

        this.bookingService.add(b);

        try (PrintWriter pw = response.getWriter()) {
            pw.write("Hello World from an SCR servlet!");
        }
    }
}