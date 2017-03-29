package controller.giveInfo.travellers;

import dao.CityDao;
import dao.TravellerDao;
import model.City;
import model.Traveller;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import tags.bean.TravellerCollection;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;

import static model.Traveller.TRAVELLER;
/**
 * show information about travellers to user
 * @author Ali Yan
 * @version 1.0
 */
@WebServlet("/travellers")
public class ListOfTravellers extends HttpServlet {
    private static final Logger log = LogManager.getLogger(ListOfTravellers.class);
    private TravellerDao travellerDao;
    private CityDao cityDao;

    /**
     * number of element which need to show on one page
     */
    private static final int TOTAL = 2;


    @Override
    public void init(ServletConfig config) throws ServletException {
        travellerDao = (TravellerDao) config.getServletContext().getAttribute("TravellerDao");
        cityDao = (CityDao) config.getServletContext().getAttribute("CityDao");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @SuppressWarnings({"ControlFlowStatementWithoutBraces", "DanglingJavadoc"})
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Traveller traveller = (Traveller) request.getSession().getAttribute(TRAVELLER);
        int id = traveller.getId();
        log.debug("Get information about user's id =" + id);

        /**
         * get information about page from which user ask about information
         */
        int pageStart = 0;
        String page = request.getParameter("page");
        log.debug("Request from page=" + page);
        if (page != null)
            pageStart = Integer.parseInt(page);

        /**
         *receive information about traveller and send user to traveller's profile page
         */
        if (request.getParameter("id") != null) {
            log.debug("Get information about traveller id=" + id);
            Traveller anyTraveller = travellerDao.getById(Integer.parseInt(request.getParameter("id")));
            String travellerName = anyTraveller.getFirstName() + " " + anyTraveller.getLastName();

            log.debug("Set attribute - traveller and traveller name " + travellerName);
            request.setAttribute("traveller", anyTraveller);
            request.setAttribute("travellerName", travellerName);

            log.debug("Redirect to profile/traveller.jsp");
            request.getRequestDispatcher("WEB-INF/profile/traveller.jsp").forward(request, response);

        } else {
            /**
             * receive information about all travellers except user who wants to see the information
             */
            log.debug("Get information about all travellers");
            List<Traveller> travellers = travellerDao.getAll(id);

            for (Traveller anyTraveller : travellers) {
                log.debug("get information about traveller's city");
                City city = cityDao.getCity(anyTraveller.getId());
                if (city != null)
                    anyTraveller.setCurrentCity(city);
            }
            log.debug("Get info about sublist of travellers");
            TakeTravellerList t = new TakeTravellerList();
            TravellerCollection travellersList = t.takeTravellers(TOTAL, pageStart, travellers);

            request.setAttribute("travellers", travellersList);
            log.debug("Redirect to travellers/index.jsp");
            request.getRequestDispatcher("/WEB-INF/travellers/index.jsp").forward(request, response);
        }
    }
}

