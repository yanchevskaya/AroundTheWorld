package controller.giveInfo.routes;

import dao.RouteDao;
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
 * servlet for creating routes
 * @author Ali Yan
 * @version 1.0
 */
@SuppressWarnings("SpellCheckingInspection")
@WebServlet("/myroutes/create")
public class CreateRoute extends HttpServlet {
    private RouteDao routeDao;

    @Override
    public void init(ServletConfig config) throws ServletException {
        routeDao = (RouteDao) config.getServletContext().getAttribute("RouteDao");
    }

    @SuppressWarnings("DanglingJavadoc")
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /**
         * check if user wants to create new route receive information and add it to database
         */
        String nameOfRoute = request.getParameter("new.route");
        if (nameOfRoute!=null && !nameOfRoute.isEmpty()){

            Route route = new Route();
            route.setTraveller((Traveller)request.getSession().getAttribute(TRAVELLER));
            route.setName(nameOfRoute);
            String description = request.getParameter("route.description");
            route.setDescription(description);
            routeDao.create(route);

            response.sendRedirect("/myroutes");
        } else {
            request.getRequestDispatcher("/WEB-INF/routes/create.jsp").forward(request, response);
        }
    }
}