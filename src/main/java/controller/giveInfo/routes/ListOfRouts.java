package controller.giveInfo.routes;

import dao.RouteDao;
import model.Route;
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
    private RouteDao routeDao;
    /**
     * number of elements which are need to be shown on one page
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
        int pageStart = 0;
        /**
         *check from which page was request
         */
        String page = request.getParameter("page");
        if (page != null)
            pageStart = Integer.valueOf(page);
        /**
         * get all information about routes
         */
            List<Route>routes = routeDao.getAll();
            TakeRouteList t = new TakeRouteList();
            RouteCollection routeList = t.takeRoutes(TOTAL, pageStart, routes);

            request.setAttribute("routes", routeList);
            request.getRequestDispatcher("/WEB-INF/routes/index.jsp").forward(request, response);
        }
    }



