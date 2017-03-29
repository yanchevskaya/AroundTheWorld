package controller.giveInfo.routes;

import dao.RouteDao;
import model.Route;
import model.Traveller;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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
    private static final Logger log = LogManager.getLogger(CreateRoute.class);
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
        log.debug("Check if user's created new route");
        if (request.getParameter("new.route")!=null){

            log.debug("Create object Route");
            Route route = new Route();
            log.debug("Set name for Route");
            route.setName(request.getParameter("new.route"));
            log.debug("Set traveller id for Route");
            route.setTraveller((Traveller)request.getSession().getAttribute(TRAVELLER));
            log.debug("Check if route has description");
            String description = request.getParameter("route.description");
            if (description!=null){
                log.debug("Set route's description");
                route.setDescription(description);
            }
            log.debug("Add information about route into data base");
            routeDao.create(route);

            log.debug("Redirect user into myroutes url");
            response.sendRedirect("/myroutes");
        }

        else {
            log.debug("Redirect user into routes/create.jsp");
            request.getRequestDispatcher("/WEB-INF/routes/create.jsp").forward(request, response);
        }
    }
}