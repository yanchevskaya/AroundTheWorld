package controller;

import model.Traveller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static model.Traveller.TRAVELLER;
/**
 * set information about user and redirect it to profile page
 * @author Ali Yan
 * @version 1.0
 */
@WebServlet("/profile")
public class Profile extends HttpServlet{

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       Traveller traveller = (Traveller)request.getSession().getAttribute(TRAVELLER);
       request.setAttribute("traveller", traveller);
       request.setAttribute("travellername", traveller.getFirstName()+" " +traveller.getLastName());
       request.getRequestDispatcher("/WEB-INF/profile/index.jsp").forward(request,response);//перенаправить обработку запроса другому ресурсу
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost (request,response);
    }
}


