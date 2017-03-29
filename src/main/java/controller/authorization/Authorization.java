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
     @SuppressWarnings("DanglingJavadoc")
     @WebServlet("/entrance/authorization")
    public class Authorization extends HttpServlet {
        private transient TravellerDao travellerDao;
        private transient CityDao cityDao;
        private static final Logger log = LogManager.getLogger(Authorization.class);

        @Override
        public void init(ServletConfig config) throws ServletException {
            travellerDao = (TravellerDao) config.getServletContext().getAttribute("TravellerDao");
            cityDao = (CityDao) config.getServletContext().getAttribute("CityDao");
        }

        @Override
        protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            String email = request.getParameter("j_username");
            @SuppressWarnings("SpellCheckingInspection")
            String hashPassword = request.getParameter("j_password").trim().hashCode()*73+"adgjlnc";

            Traveller authorizedTraveller = travellerDao.getIfExist(email, hashPassword);
            log.debug("Check information about authorization for email - " +email);
            try {
                if (authorizedTraveller==null) {
                    request.setAttribute("error", "try.again");
                    throw new WrongData("User with these email and password aren't contained in DB");
                }
            /**
             * if user has information about current place in data base, set it into traveller parameter
             */
            City city = cityDao.getCity(authorizedTraveller.getId());
            log.debug("Check information about user's city");
            if (city!=null)
                authorizedTraveller.setCurrentCity(city);

            request.getSession().setAttribute(TRAVELLER, authorizedTraveller);
            log.debug("Set attribute to session about user with id =" +authorizedTraveller.getId());

            log.debug("Redirect user to main page");
            response.sendRedirect("/");

            } catch (WrongData w) {
                log.info(w.getErrorMessage());
                request.getRequestDispatcher("/WEB-INF/error/authorizationError.jsp").forward(request, response);
            }
        }

        @Override
        protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            doPost(request, response);
        }
    }


