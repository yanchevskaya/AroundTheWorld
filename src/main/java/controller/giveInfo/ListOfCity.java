package controller.giveInfo;

import dao.CityDao;
import model.City;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import tags.bean.CityCollection;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * show information about cities to user
 * @author Ali Yan
 * @version 1.0
 */
@WebServlet("/cities")
public class ListOfCity extends HttpServlet {
    private static final Logger log = LogManager.getLogger(ListOfCity.class);
    private CityDao cityDao;
     /**
     * number of element which need to show on one page
     */
    private static final int TOTAL = 2;

    /**
     * initialized JDBC driver for country and city
     */
    @Override
    public void init(ServletConfig config) throws ServletException {
        cityDao = (CityDao) config.getServletContext().getAttribute("CityDao");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @SuppressWarnings("DanglingJavadoc")
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /**
         * divide all information into some pages
         */
        int count;
        /**
         * from which element show information
         */
        int pageStart = 0;
        /**
         *  till which element show information
         */
        int pageEnd = 2;
        log.debug ("Get information about page");
        String page = request.getParameter("page");
        log.debug("Request from page "+page);
        if (page != null) {
            pageStart = Integer.parseInt(page);
            pageStart = (pageStart - 1) * TOTAL;
            pageEnd = pageStart + TOTAL;
        }
        /**
         * show information about city
         */
        log.debug("Get information about country");
        if (request.getParameter("country") != null) {
            String countryName = request.getParameter("country");
            /**
             * receive information about city from database
             */
            log.debug("Get information about list of cities");
            List<City> cities = cityDao.getByCountryName(countryName);

            /**
             * divide city into some pieces to show on different pages
             */
            log.debug("Divide information into needed part");
            List<City> subCities = cities.subList(pageStart, pageEnd);
            /**
             * receive amount of pages
             */
            count = cities.size() % TOTAL != 0 ? cities.size() / TOTAL + 1 : cities.size() / TOTAL;
            CityCollection cityList = new CityCollection(subCities, count, countryName);

            log.debug("Set information about country and its Cities");
            request.setAttribute("cities", cityList);
            request.setAttribute("country", countryName);
            log.debug("Redirect to country/city.jsp");
            request.getRequestDispatcher("/WEB-INF/countries/city.jsp").forward(request, response);
        }

    }
}