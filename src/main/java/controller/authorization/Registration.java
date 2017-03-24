package controller.authorization;

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

        @Override
        protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
           request.getRequestDispatcher("/WEB-INF/registration/index.jsp").forward(request,response);//перенаправить обработку запроса другому ресурсу
    }

        @Override
        protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            doPost (request,response);
        }
}

