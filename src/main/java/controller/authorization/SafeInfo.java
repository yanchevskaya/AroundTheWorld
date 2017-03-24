package controller.authorization;

import dao.TravellerDao;
import error.WrongData;
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
 * safe data of user to database
 * add User's to session
 * redirect to main page
 * @author Ali Yan
 * @version 1.0
 */
@WebServlet("/registration/safeInfo")
public class SafeInfo extends HttpServlet {
    private TravellerDao travellerDao;
    private static final String  ERROR_PATH = "/WEB-INF/error/emailError.jsp";
    private static final String  ERROR_NAME = "error";
    private static final Logger log = LogManager.getLogger(SafeInfo.class);

    @Override
        public void init(ServletConfig config) throws ServletException {
        travellerDao = (TravellerDao) config.getServletContext().getAttribute("TravellerDao");
        }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.info ("User's entered to registration page with email and password");
        String password = request.getParameter("j_password");
        String email = request.getParameter("j_username");
    /**
    * pattern to check if enail is correct if it isn't user is redirected to error page
    */
        try {
            if (!email.matches("^(?:\\w+[-.!#$%&'*+/=?^_`{|}~]*\\w+)@(?:\\w+[.!#$%&'*+/=?^_`{|}~-]*)\\.(?:\\w+[-]*)$"))
                throw new WrongData();
        } catch (WrongData w){
            log.error("Incorrect email");
            request.setAttribute(ERROR_NAME, "wrongemail");
            request.getRequestDispatcher(ERROR_PATH).forward(request, response);
        }
        /**
         * if password contains less then 5 symbols send to error page
         */
        try {
            if (password.length() < 5)
                throw new WrongData();
        }catch(WrongData w){
            log.error("User put too small password");
            request.setAttribute(ERROR_NAME, "wrongpassword");
            request.getRequestDispatcher(ERROR_PATH).forward(request, response);
        }
        /**
         * if password 1 and password 2 are different user's redirected to error page
         */
        try {
            if (!password.trim().equals(request.getParameter("j_password2").trim()))
                throw new WrongData();
        }catch(WrongData w){
            log.error("User's entered different passwords");
            request.setAttribute(ERROR_NAME, "differentpassword");
            request.getRequestDispatcher(ERROR_PATH).forward(request, response);
        }
        /**
         * check if email is already in database user is redirected to error page
         */

        try {
            if (travellerDao.checkEmail(email.trim()))
                throw new WrongData();
        }catch(WrongData w){
            log.error("User's added email which already exists");
            request.setAttribute("error", "emailexist");
            request.getRequestDispatcher("/WEB-INF/error/emailError.jsp").forward(request, response);
        }

        /**
         * if all data is correct
         * encode password and add info to traveller, session and database
         */
        String hashPassword = password.trim().hashCode()*73+"adgjlnc";
        Traveller traveller = (Traveller) request.getSession().getAttribute(TRAVELLER);
        traveller.setEmail(email.trim());
        traveller.setHashPassword(hashPassword);

        int id = travellerDao.create(traveller);
        traveller.setId(id);
        request.getSession().setAttribute(TRAVELLER, traveller);

        response.sendRedirect("/");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost (request,response);
    }


}
