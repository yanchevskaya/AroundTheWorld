package controller.authorization;

import dao.CityDao;
import dao.TravellerDao;
import error.WrongData;
import model.City;
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
 *  Servlet check if user has entered incorrect data throw exception which is
 * caught and user is redirected to error page
 * if data is right data of user save in Session
 * user is redirected to welcome page
 * @author Ali Yan
 * @version 1.0
 */
     @WebServlet("/entrance/authorization")
    public class Authorization extends HttpServlet {
        private TravellerDao travellerDao;
        private CityDao cityDao;
        private static final Logger log = LogManager.getLogger(Authorization.class);

        @Override
        public void init(ServletConfig config) throws ServletException {
            travellerDao = (TravellerDao) config.getServletContext().getAttribute("TravellerDao");
            cityDao = (CityDao) config.getServletContext().getAttribute("CityDao");

        }

        @Override
        protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
           log.info("User's entered to authorization page");
            String email = request.getParameter("j_username");
            String hashPassword = request.getParameter("j_password").trim().hashCode()*73+"adgjlnc";

            Traveller authorizedTraveller = travellerDao.getIfExist(email, hashPassword);

            try {
                if (authorizedTraveller==null)
                    throw new WrongData();
            } catch (WrongData w) {
                log.error("User with these email and password aren't contained in DB");
                request.setAttribute("error", "tryagain");
                request.getRequestDispatcher("/WEB-INF/error/authorizationError.jsp").forward(request, response);
            }

            /**
             * if user has information about current place in data base, set it into traveller parameter
             */
            City city = cityDao.getCity(authorizedTraveller.getId());
            if (city!=null)
                authorizedTraveller.setCurrentCity(city);

            request.getSession().setAttribute(TRAVELLER, authorizedTraveller);

            response.sendRedirect("/");

        }

        @Override
        protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            doPost(request, response);
        }
    }


