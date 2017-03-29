package controller.giveInfo.routes;

import dao.RouteDao;
import model.Route;
import model.Traveller;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import tags.bean.RouteCollection;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static model.Traveller.TRAVELLER;

/**
 * show information about user's routes
 * @author Ali Yan
 * @version 1.0
 */
@SuppressWarnings("SpellCheckingInspection")
@WebServlet("/myroutes")
public class MyRoutes extends HttpServlet {
    private static final Logger log = LogManager.getLogger(MyRoutes.class);
    private RouteDao routeDao;
    /**
     * number of element which need to show on one page
     */
    private static final int TOTAL = 2;

    @Override
    public void init(ServletConfig config) throws ServletException {
        routeDao = (RouteDao) config.getServletContext().getAttribute("RouteDao");
          }

    @SuppressWarnings("DanglingJavadoc")
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /**
         * check if user wants to get the information about specifies route
         * and redirect him for page with this information
         */
        log.debug("Check if user wants to receive information about route");
        if (request.getParameter("id") != null) {
            int id = Integer.parseInt(request.getParameter("id"));
            log.debug("Get information about route with id "+id+ " from database");
            Route route = routeDao.get(id);
            log.debug("Set attribute of route into request parameter");
            request.setAttribute("route", route);
            log.debug("Redirect user to routes/update.jsp");
            request.getRequestDispatcher("/WEB-INF/routes/update.jsp").forward(request, response);
        }

        /**
        *check from which pages request
        */
        log.debug("get information about page");
        String page = request.getParameter("page");
        int pageStart = 0;
        if (page != null)
            pageStart = Integer.parseInt(page);
            log.debug("Get information from page ="+pageStart);
        /**
         * get all routes of user
         */
            log.debug("Get information about user");
            Traveller traveller = (Traveller) request.getSession().getAttribute(TRAVELLER);
            int travellerId = traveller.getId();
            log.debug("Get information about all routes from user id="+traveller+" from database");
            List<Route> routes = routeDao.getAll(travellerId);

            log.debug("Divide list of routes into needed pieces");
            TakeRouteList t = new TakeRouteList();
            RouteCollection routeList = t.takeRoutes(TOTAL, pageStart, routes);

            log.debug("Set attribute into request");
            request.setAttribute("routes", routeList);
            log.debug("redirect into page routes/userroutes.jsp");
            request.getRequestDispatcher("/WEB-INF/routes/userroutes.jsp").forward(request, response);
        }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}

