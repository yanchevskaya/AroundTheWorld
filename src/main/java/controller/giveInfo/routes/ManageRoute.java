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

@WebServlet("/myroutes/manage")
public class ManageRoute extends HttpServlet {
    private TravellerDao travellerDao;
    private RouteDao routeDao;
    private Route route;
    private City city;

    @Override
    public void init(ServletConfig config) throws ServletException {
        routeDao = (RouteDao) config.getServletContext().getAttribute("RouteDao");
        }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if ("create".equals(request.getParameter("manage"))) {
           request.getRequestDispatcher("/WEB-INF/routes/create.jsp").forward(request, response);//перенаправить обработку запроса другому ресурсу
        }

        if (request.getParameter("change")!=null) {
           int id = Integer.parseInt(request.getParameter("change"));
            System.out.println("change id" + id);
           String name = request.getParameter("name");
           String description = request.getParameter("routedescription");
           routeDao.update(name, description, id);
           response.sendRedirect("/myroutes");
            }

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

        if ("delete".equals(request.getParameter("manage"))) {
            int id = Integer.parseInt(request.getParameter("id"));
            routeDao.remove(id);
            response.sendRedirect("/myroutes");
        }
    }
}