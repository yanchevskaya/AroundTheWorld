package controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * change language
 * @author Ali Yan
 * @version 1.0
 */
@WebServlet("/local")
public class Local extends HttpServlet {
    private static final Logger log = LogManager.getLogger(Local.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.info("User has changed language");
        request.getSession().setAttribute("local", request.getParameter("local"));
        response.sendRedirect("/");
    }
}



