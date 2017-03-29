package controller.giveInfo.routes;

import dao.RouteDao;
import model.Route;
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

/**
 * show information about routes to user
 * @author Ali Yan
 * @version 1.0
 */
@WebServlet("/routes")
public class   ListOfRouts extends HttpServlet {
    private static final Logger log = LogManager.getLogger(ListOfRouts.class);
    private RouteDao routeDao;

    /**
     * number of element which need to show on one page
     */
    private static final int TOTAL = 2;

    /**
     * initialized JDBC driver for route
     */
    @Override
    public void init(ServletConfig config) throws ServletException {
        routeDao = (RouteDao) config.getServletContext().getAttribute("RouteDao");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @SuppressWarnings("DanglingJavadoc")
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /**
         *check from which pages request
         */
        log.debug("Get information about page");
        String page = request.getParameter("page");
        int pageStart = 0;
        if (page != null)
            pageStart = Integer.valueOf(page);
            log.debug("Request from page "+ pageStart);
        /**
         * get all information about routes
         */
            log.debug("Get information about list of routes from data base");
            List<Route>routes = routeDao.getAll();
            log.debug("Divide list into needed pieces");
            TakeRouteList t = new TakeRouteList();
            RouteCollection routeList = t.takeRoutes(TOTAL, pageStart, routes);

            log.debug("Set information about list of routes into request's attribute");
            request.setAttribute("routes", routeList);
            log.debug("Redirect user  into routes/index.jsp");
            request.getRequestDispatcher("/WEB-INF/routes/index.jsp").forward(request, response);
        }
    }



