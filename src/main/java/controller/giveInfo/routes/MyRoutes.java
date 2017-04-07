package controller.giveInfo.routes;

import dao.RouteDao;
import model.Route;
import model.Traveller;
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
        if (request.getParameter("id") != null) {
            int id = Integer.parseInt(request.getParameter("id"));
            Route route = routeDao.get(id);
            request.setAttribute("route", route);
            request.getRequestDispatcher("/WEB-INF/routes/update.jsp").forward(request, response);
        }

        /**
        *check from which pages request
        */
        String page = request.getParameter("page");
        int pageStart = 0;
        if (page != null)
            pageStart = Integer.parseInt(page);
        /**
         * get all routes of user
         */
            Traveller traveller = (Traveller) request.getSession().getAttribute(TRAVELLER);
            int travellerId = traveller.getId();
            List<Route> routes = routeDao.getAll(travellerId);

            TakeRouteList t = new TakeRouteList();
            RouteCollection routeList = t.takeRoutes(TOTAL, pageStart, routes);

            request.setAttribute("routes", routeList);
            request.getRequestDispatcher("/WEB-INF/routes/userroutes.jsp").forward(request, response);
        }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}

