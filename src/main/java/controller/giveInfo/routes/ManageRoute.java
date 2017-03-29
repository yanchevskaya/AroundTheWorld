package controller.giveInfo.routes;

import dao.RouteDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * servlet for managing of routes (create, update, delete)
 * @author Ali Yan
 * @version 1.0
 */
@SuppressWarnings("SpellCheckingInspection")
@WebServlet("/myroutes/manage")
public class ManageRoute extends HttpServlet {
    private static final Logger log = LogManager.getLogger(ManageRoute.class);
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
        log.debug("Check if user's asked to change his route");
        if (request.getParameter("change")!=null) {
           int id = Integer.parseInt(request.getParameter("change"));
           log.debug("Change route id="+id);

           String name = request.getParameter("name");
           String description = request.getParameter("route.description");

           log.debug("Update information in data base for route id=" +id);
           routeDao.update(name, description, id);

           log.debug("Redirect user into myroute url");
           response.sendRedirect("/myroutes");
            }
    }
}