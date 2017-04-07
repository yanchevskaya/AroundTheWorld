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
 * servlet for updating routes
 * @author Ali Yan
 * @version 1.0
 */
@SuppressWarnings("SpellCheckingInspection")
@WebServlet("/myroutes/manage")
public class ManageRoute extends HttpServlet {
    private RouteDao routeDao;

    @Override
    public void init(ServletConfig config) throws ServletException {
        routeDao = (RouteDao) config.getServletContext().getAttribute("RouteDao");
        }

    @SuppressWarnings("DanglingJavadoc")
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /**
         * check if user press button change and update information in data base
         */
        if (request.getParameter("change")!=null) {
            int id = Integer.parseInt(request.getParameter("change"));

            String name = request.getParameter("name");
            String description = request.getParameter("route.description");

            if (!name.isEmpty()) {
                routeDao.update(name, description, id);
                response.sendRedirect("/myroutes");
            }
            else {
                response.sendRedirect("/myroutes?id="+id);
            }
        }
    }
}