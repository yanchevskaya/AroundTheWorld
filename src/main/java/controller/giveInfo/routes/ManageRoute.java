package controller.giveInfo.routes;


import dao.RouteDao;
import dao.TravellerDao;
import model.City;
import model.Route;
import model.Traveller;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static model.Traveller.TRAVELLER;

/**
 * servlet for managing of routes (create, update, delete)
 * @author Ali Yan
 * @version 1.0
 */
@WebServlet("/myroutes/manage")
public class ManageRoute extends HttpServlet {
    private RouteDao routeDao;
    private Route route;

    @Override
    public void init(ServletConfig config) throws ServletException {
        routeDao = (RouteDao) config.getServletContext().getAttribute("RouteDao");
        }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if ("create".equals(request.getParameter("manage"))) {
           request.getRequestDispatcher("/WEB-INF/routes/create.jsp").forward(request, response);//перенаправить обработку запроса другому ресурсу
        }
        /**
         * check if user press button change and update information in data base
         */
        if (request.getParameter("change")!=null) {
           int id = Integer.parseInt(request.getParameter("change"));
            System.out.println("change id" + id);
           String name = request.getParameter("name");
           String description = request.getParameter("routedescription");
           routeDao.update(name, description, id);
           response.sendRedirect("/myroutes");
            }
        /**
         * check if user wants to create new route receive information and add it to database
         */
        if (request.getParameter("newroute")!=null){

            route = new Route();
            route.setName(request.getParameter("newroute"));
            route.setTraveller((Traveller)request.getSession().getAttribute(TRAVELLER));
            String description = request.getParameter("routedescription");
            if (description!=null){
                route.setDescription(description);
            }
            int id =routeDao.create(route);
            route.setId(id);

            response.sendRedirect("/myroutes");
        }

        /**
         * check if user wants to delete information
         * receive parameters and delete information from database
         */
        if ("delete".equals(request.getParameter("manage"))) {
            int id = Integer.parseInt(request.getParameter("id"));
            routeDao.remove(id);
            response.sendRedirect("/myroutes");
        }
    }
}