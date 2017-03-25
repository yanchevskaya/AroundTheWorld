package controller.giveInfo;


import controller.authorization.Authorization;
import dao.CityDao;
import dao.CountryDao;
import model.City;
import model.Country;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import tags.bean.CityCollection;
import tags.bean.CountryCollection;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * show information about contries to user
 * @author Ali Yan
 * @version 1.0
 */
@WebServlet("/countries")
public class ListOfCountry extends HttpServlet{
    private static final Logger log = LogManager.getLogger(ListOfCountry.class);
    private CountryCollection countryList;
    private CityCollection cityList;
    private CountryDao countryDao;
    private CityDao cityDao;
    private List<Country> countries;
    private List<Country> subCountries;
    private List<City>cities;
    private List<City>subCities;
    /**
     * number of element which need to show on one page
     */
    private static final int TOTAL = 2;

    /**
     * initialized JDBC driver for country and city
     */
    @Override
    public void init(ServletConfig config) throws ServletException {
        countryDao = (CountryDao) config.getServletContext().getAttribute("CountryDao");
        cityDao = (CityDao) config.getServletContext().getAttribute("CityDao");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /**
         * divide all information into some pages
         */
        int count = 0;
        /**
         * from which element show information
         */
        int pageStart = 0;
        /**
         *  till which element show information
         */
        int pageEnd = 2;
        String page = request.getParameter("page");
        if (page != null) {
            pageStart = Integer.parseInt(page);
            pageStart = (pageStart - 1) * TOTAL;
            pageEnd = pageStart+TOTAL;
        }
        /**
         * show information about city
         */
        if (request.getParameter("country") != null) {
            log.info("user wants to receive information about city");
            String countryName = request.getParameter("country");
        /**
        * receive information about city from database
        */
            cities = cityDao.getByCountryName(countryName);
        /**
        * divide city into some pieces to show on different pages
        */
            subCities = cities.subList(pageStart,pageEnd);
        /**
        * receive amount of pages
        */
            count = cities.size() % TOTAL != 0 ? cities.size()/TOTAL + 1 : cities.size()/TOTAL;
            cityList = new CityCollection(subCities, count, countryName);

            request.setAttribute("cities", cityList);
            request.setAttribute("country", countryName);
            request.getRequestDispatcher("/WEB-INF/countries/city.jsp").forward(request, response);
        }

        /**
         * get all countries from database divide it into some pieces
         */
        countries = countryDao.getAll();
        subCountries = countries.subList(pageStart,pageEnd);
        count = countries.size() % TOTAL != 0 ? countries.size()/TOTAL + 1 : countries.size()/TOTAL;
        countryList = new CountryCollection(subCountries, count);

        request.setAttribute("countries", countryList);
        request.getRequestDispatcher("/WEB-INF/countries/index.jsp").forward(request, response);
    }
}
