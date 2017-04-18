package controller.giveInfo.routes;

import dao.RouteDao;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * servlet for deleting routes
 *
 * @author Ali Yan
 * @version 1.0
 */
@SuppressWarnings("SpellCheckingInspection")
@WebServlet("/myroutes/delete")
public class DeleteRoute extends HttpServlet {
    private RouteDao routeDao;

    @Override
    public void init(ServletConfig config) throws ServletException {
        routeDao = (RouteDao) config.getServletContext().getAttribute("RouteDao");
    }

    @SuppressWarnings("DanglingJavadoc")
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));
        routeDao.remove(id);
        response.sendRedirect("/myroutes");
    }
}
