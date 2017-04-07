package controller.giveInfo.travellers;

import dao.TravellerDao;
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

/**
 * show information about travellers to user
 * @author Ali Yan
 * @version 1.0
 */
@WebServlet("/travellers/find")
public class SearchingByName extends HttpServlet {
    private TravellerDao travellerDao;
     /**
     * number of element which need to show on one page
     */
    private static final int TOTAL = 2;

    @Override
    public void init(ServletConfig config) throws ServletException {
        travellerDao = (TravellerDao) config.getServletContext().getAttribute("TravellerDao");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @SuppressWarnings({"ControlFlowStatementWithoutBraces", "DanglingJavadoc"})
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
         /**
         * get information about page from which user ask about information
         */
        int pageStart = 0;
        String page = request.getParameter("page");
        if (page != null)
            pageStart = Integer.parseInt(page);
        /**
         * find information about travellers by name
         */
        if (request.getParameter("traveller") != null) {
            TravellerCollection travellersList;
            String name = request.getParameter("traveller");
            String[] travellerNames = name.split("\\s");
            /**
             * if request has space, divide it and search using name and last name
             */
            TakeTravellerList t = new TakeTravellerList();
            if (travellerNames.length > 1) {
                List<Traveller>travellers = travellerDao.getByName(travellerNames[0], travellerNames[1]);
                travellersList = t.takeTravellers(TOTAL, pageStart, travellers);
            } else {
                List<Traveller>travellers = travellerDao.getByName(travellerNames[0]);
                travellersList = t.takeTravellers(TOTAL, pageStart, travellers);
            }
            request.setAttribute("travellers", travellersList);

            request.getRequestDispatcher("/WEB-INF/travellers/index.jsp").forward(request, response);
        }
    }
}