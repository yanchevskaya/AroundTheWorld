package controller.authorization;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * redirect user to jsp registration form
 * @author Ali Yan
 * @version 1.0
 */
@WebServlet("/registration")
public class Registration extends HttpServlet{
    private static final Logger log = LogManager.getLogger(Registration.class);

        @Override
        protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            log.debug("Redirect user to registration/index.jsp");
            request.getRequestDispatcher("/WEB-INF/registration/index.jsp").forward(request,response);
    }
        @Override
        protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            doPost (request,response);
        }
}

