package controller.authorization;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static model.Traveller.TRAVELLER;

/**
 * delete information of user from session
 * and redirect to main page
 * @author Ali Yan
 * @version 1.0
 */
@WebServlet("/logout")
public class LogOut extends HttpServlet{
    private static final Logger log = LogManager.getLogger(LogOut.class);

        @Override
        protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

           log.debug("Remove information about user id=" +request.getSession().getId()+
                            "from Session");
           request.getSession().removeAttribute(TRAVELLER);
           log.debug("Invalidate session");
           request.getSession().invalidate();

            log.debug("Redirect user to main page");
            response.sendRedirect("/");
        }

        @Override
        protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            doPost (request,response);
        }


    }


