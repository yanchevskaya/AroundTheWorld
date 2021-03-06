package controller.giveInfo;

import dao.CountryDao;

import model.Country;

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
 * show information about countries to user
 * @author Ali Yan
 * @version 1.0
 */
@WebServlet("/countries")
public class ListOfCountry extends HttpServlet{
    private CountryDao countryDao;
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
        int count=0;
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
         * get all countries from database divide it into some pieces
         */
        List<Country> countries = countryDao.getAll();

        if (countries.size()>2)
        count = countries.size() % TOTAL != 0 ? countries.size()/TOTAL + 1 : countries.size()/TOTAL;

        List<Country> subCountries = countries.subList(pageStart,pageEnd);
        CountryCollection countryList = new CountryCollection(subCountries, count);

        request.setAttribute("countries", countryList);
        request.getRequestDispatcher("/WEB-INF/countries/index.jsp").forward(request, response);
    }
}
