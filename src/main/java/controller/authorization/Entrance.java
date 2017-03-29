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
 * when user press the button'log in' he is redirected to page for entering email and password
 * @author Ali Yan
 * @version 1.0
 */
@WebServlet("/entrance")
    public class Entrance extends HttpServlet{
    private static final Logger log = LogManager.getLogger(Entrance.class);

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.debug("redirect user to entrance/index.jsp");
        request.getRequestDispatcher("/WEB-INF/entrance/index.jsp").forward(request,response);//перенаправить обработку запроса другому ресурсу
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost (request,response);
    }
}
