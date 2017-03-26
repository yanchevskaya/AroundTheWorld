package controller.giveInfo;

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
import java.util.ArrayList;
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
    private TravellerCollection travellersList;
    private List<Traveller>  travellers;
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

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
         *receive information about traveller and send user to traveller's profile page
         */
        if (request.getParameter("id") != null) {
            Traveller anyTraveller = travellerDao.getById(Integer.parseInt(request.getParameter("id")));
            String travellerName = anyTraveller.getFirstName() + " " + anyTraveller.getLastName();

            request.setAttribute("traveller", anyTraveller);
            request.setAttribute("travellerName", travellerName);

            request.getRequestDispatcher("WEB-INF/profile/traveller.jsp").forward(request, response);

            /**
             * find information about user by name
             */
        } else if (request.getParameter("traveller") != null) {
            String name = request.getParameter("traveller");
            String[] travellerNames = name.split("\\s");
            /**
            * if request has space, divide it and search using name and last name
            */
            if (travellerNames.length > 1) {
                travellers = travellerDao.getByName(travellerNames[0], travellerNames[1]);
                travellersList = takeTravellers (pageStart, travellers);

            } else {
                List<Traveller> findNameTraveller = new ArrayList<>();
                travellers = travellerDao.getAll(id);

                for (Traveller anyTraveller : travellers) {
                    if (anyTraveller.getFirstName().equals(travellerNames[0]))
                        findNameTraveller.add(traveller);
                }
                travellersList = takeTravellers (pageStart, findNameTraveller);
                             }
            request.setAttribute("travellers", travellersList);
        } else {
            /**
             * receive information about all travellers except user who wants to see the information
             */
            travellers = travellerDao.getAll(id);

            for (Traveller anyTraveller : travellers) {
                City city = cityDao.getCity(anyTraveller.getId());
                if (city != null)
                    anyTraveller.setCurrentCity(city);
            }
            travellersList = takeTravellers(pageStart, travellers);
        }
            request.setAttribute("travellers", travellersList);
            request.getRequestDispatcher("/WEB-INF/travellers/index.jsp").forward(request, response);
    }

    /**
     * reseive information about travellers for one required page
     * @param pageStart - position in the list from what we need ro receive information
     * @param traveller - list of travellers
     * @return bean of Travellers to print it
     */
    private TravellerCollection takeTravellers(int pageStart, List<Traveller> traveller) {
        int pageEnd;
        int count=0;
        if (pageStart!=0)
            pageStart = (pageStart - 1) * TOTAL;
        pageEnd = pageStart + TOTAL;

        if (traveller == null) {
            TravellerCollection t = new TravellerCollection(traveller, count);
        }
        if (traveller.size() < pageEnd)
            pageEnd = traveller.size();
        List <Traveller> shortList = traveller.subList(pageStart, pageEnd);
        count = traveller.size() % TOTAL != 0 ? traveller.size() / TOTAL + 1 : traveller.size() / TOTAL;
        TravellerCollection t = new TravellerCollection(shortList, count);

        return t;
    }
    }

