package controller.giveInfo.travellers;

import dao.CityDao;
import dao.TravellerDao;
import model.City;
import model.Traveller;
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
        /**
         *receive information about traveller and send user to traveller's profile page
         */
        if (request.getParameter("id") != null) {
            Traveller anyTraveller = travellerDao.getById(Integer.parseInt(request.getParameter("id")));
            String travellerName = anyTraveller.getFirstName() + " " + anyTraveller.getLastName();

            request.setAttribute("traveller", anyTraveller);
            request.setAttribute("travellerName", travellerName);

            request.getRequestDispatcher("WEB-INF/profile/traveller.jsp").forward(request, response);
        } else {
            Traveller traveller = (Traveller) request.getSession().getAttribute(TRAVELLER);
            int id = traveller.getId();
            /**
             * get information about page from which user ask about information
             */
            int pageStart = 0;
            String page = request.getParameter("page");
            if (page != null)
                pageStart = Integer.parseInt(page);

            /**
             * receive information about all travellers except user who wants to see the information
             */
            List<Traveller> travellers = travellerDao.getAll(id);

            for (Traveller anyTraveller : travellers) {
                City city = cityDao.getCity(anyTraveller.getId());
                if (city != null)
                    anyTraveller.setCurrentCity(city);
            }
            TakeTravellerList t = new TakeTravellerList();
            TravellerCollection travellersList = t.takeTravellers(TOTAL, pageStart, travellers);

            request.setAttribute("travellers", travellersList);
            request.getRequestDispatcher("/WEB-INF/travellers/index.jsp").forward(request, response);
        }
    }
}

