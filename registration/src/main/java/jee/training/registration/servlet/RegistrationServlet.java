package jee.training.registration.servlet;

import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jee.training.registration.dao.AttendeeDao;
import jee.training.registration.model.Attendee;
import jee.training.registration.service.RegistrationService;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@WebServlet(name = "RegistrationServlet", value = "/registration")
public class RegistrationServlet extends HttpServlet {
    @Inject
    private RegistrationService registrationService;

    @Inject
    private AttendeeDao attendeeDao;

    @Inject
    private Logger logger;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String eventId = request.getParameter("eventId");
        if("true".equals(request.getParameter("simulateCrud"))) {
            Attendee attendee = new Attendee("Hans", "Hansen");
            registrationService.addAttendee(eventId, attendee);

            registrationService.simulateDirtyChecking(attendee.getId());
            registrationService.simulateUpdateDetachedObject(attendee);
            registrationService.simulateDelete(attendee);

            response.sendRedirect(request.getContextPath());
            return;
        }

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("Registrierungsformular");
        HttpSession session = request.getSession();
        Object obj = session.getAttribute(eventId);
        List<Attendee> attendees = new ArrayList<>();
        if (obj != null && obj instanceof List) {
            System.out.println(String.format("Event #%s existiert bereits", eventId));
            attendees = (List<Attendee>) obj;
        } else {
            System.out.println(String.format("Event #%s existiert noch nicht", eventId));
            session.setAttribute(eventId, attendees);
        }
        response.sendRedirect(request.getContextPath() + "/formular.jsp?eventId=" + eventId);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String eventId = request.getParameter("eventId");
        Attendee attendee = new Attendee(request.getParameter("name"), request.getParameter("email"));
        registrationService.addAttendee(eventId, attendee);

        logger.info("attendee hinzugefügt: " + attendee);

        response.sendRedirect(request.getContextPath() + "?success=true");
    }
}
