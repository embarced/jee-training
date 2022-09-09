package jee.training.registration.servlet;

import jakarta.inject.Inject;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import jee.training.registration.dao.EventDao;
import jee.training.registration.model.Attendee;
import jee.training.registration.model.Event;
import jee.training.registration.service.RegistrationService;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

@WebServlet(name = "AdminServlet", value = "/admin")
public class AdminServlet extends HttpServlet {
    private final RegistrationService registrationService;

    @Inject
    private EventDao eventDao;

    @Inject
    public AdminServlet(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        response.setContentType("text/html");

        out.println("<html><body>");

        String eventId = request.getParameter("eventId");
        if (eventId == null) {
            eventDao.findAll().forEach(e -> {
                out.println(String.format("<a href=\"admin?eventId=%s\">%s</a>", e.getId(), e.getId()));
            });
        } else {
            Event event = registrationService.getEvents().stream().filter(e -> eventId.equals(e.getId())).findFirst().get();
            out.println(String.format("Event #%s, Attendees: %s", eventId, event.getAttendees()));
        }
        out.println("<br/><a href=\"" + request.getContextPath() + "\">Zur Startseite</a>");
        out.println("</body></html>");
        out.close();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
