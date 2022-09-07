package jee.training.registration.servlet;

import java.io.*;

import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class HelloServlet extends HttpServlet {
    private String message;

    public void init() {
        message = "Hello %s!";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");

        // Hello
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>" + String.format(message, request.getParameter("name")) + "</h1>");
        out.println("</body></html>");
    }

    public void destroy() {
    }
}