package controller.giveInfo.routes;

import dao.RouteDao;
import model.City;
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

@WebServlet("/myroutes")
public class MyRoutes extends HttpServlet {
    private Traveller traveller;
    private RouteDao routeDao;
    private Route route;
    private RouteCollection routeList;
    private RouteCollection subList;
    private City city;
    private List<Route> routes;
    private List<Route> shortList;
    private int TOTAL = 2;

    @Override
    public void init(ServletConfig config) throws ServletException {
        routeDao = (RouteDao) config.getServletContext().getAttribute("RouteDao");
          }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getParameter("id") != null) {
            int id = Integer.parseInt(request.getParameter("id"));
            Route route = routeDao.get(id);
            request.setAttribute("route", route);
            request.getRequestDispatcher("/WEB-INF/routes/update.jsp").forward(request, response);
        }


        String page = request.getParameter("page");
        int pageStart = 0;
        if (page != null)
            pageStart = Integer.parseInt(page);

            traveller = (Traveller) request.getSession().getAttribute(TRAVELLER);
            int travId = traveller.getId();
            routes = routeDao.getAll(travId);

            TakeRouteList t = new TakeRouteList();
            routeList = t.routes(2, pageStart, routes);

            request.setAttribute("routes", routeList);
            request.getRequestDispatcher("/WEB-INF/routes/userroutes.jsp").forward(request, response);
        }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}

