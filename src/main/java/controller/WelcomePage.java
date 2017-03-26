package controller;

import model.Traveller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

import static model.Traveller.TRAVELLER;

/**
 * welcome page with main information
 * @author Ali Yan
 * @version 1.0
 */
@WebServlet("/")
public class WelcomePage extends HttpServlet {
    private Traveller traveller;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

       traveller = (Traveller) request.getSession().getAttribute(TRAVELLER);
       request.setAttribute("travellerName", traveller.getFirstName());
       request.getRequestDispatcher("/WEB-INF/index.jsp").forward(request,response);
  }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost (request,response);
    }
}



