package controller.authorization;

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

        @Override
        protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

           request.getSession().removeAttribute(TRAVELLER);
           request.getSession().invalidate();

            response.sendRedirect("/");
        }

        @Override
        protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            doPost (request,response);
        }


    }


