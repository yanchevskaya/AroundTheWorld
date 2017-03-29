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
    private static final Logger log = LogManager.getLogger(SafeInfo.class);
    private TravellerDao travellerDao;
    private static final String  ERROR = "error";

    @Override
        public void init(ServletConfig config) throws ServletException {
        travellerDao = (TravellerDao) config.getServletContext().getAttribute("TravellerDao");
        }

    @SuppressWarnings("DanglingJavadoc")
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.debug("Get parameters of email and password");
        String password = request.getParameter("j_password");
        String email = request.getParameter("j_username");
    /**
    * pattern to check if email is correct if it isn't user is redirected to error page
    */
        try {
            log.debug("Check email using pattern");
            if (!email.matches("^(?:\\w+[-.!#$%&'*+/=?^_`{|}~]*\\w+)@(?:\\w+[.!#$%&'*+/=?^_`{|}~-]*)\\.(?:\\w+[-]*)$")) {
                request.setAttribute(ERROR, "wrong.email");
                throw new WrongData("Incorrect email");
            }

        /**
         * if password contains less then 5 symbols send to error page
         */
            log.debug("Check password's length");
            if (password.length() < 5){
                request.setAttribute(ERROR, "wrong.password");
                throw new WrongData("User put too small password");
                }
        /**
         * if password 1 and password 2 are different user's redirected to error page
         */
            log.debug("Check passwords' similarities");
               if (!password.trim().equals(request.getParameter("j_password2").trim())) {
                   request.setAttribute(ERROR, "different.password");
                   throw new WrongData("User's entered different passwords");
               }

        /**
         * check if email is already in database user is redirected to error page
         */
            log.debug("Searching email in data base to prevent adding duplication");
            if (travellerDao.checkEmail(email.trim())) {
                request.setAttribute(ERROR, "email.exists");
                throw new WrongData("User's added email which already exists");
            }
        /**
         * if all data is correct"
         * encode password and add info to traveller, session and database
         */
        log.debug("Encode password");
        @SuppressWarnings("SpellCheckingInspection")
        String hashPassword = password.trim().hashCode()*73+"adgjlnc";
        log.debug("Get information about user's data from session");
        Traveller traveller = (Traveller) request.getSession().getAttribute(TRAVELLER);
        log.debug("Set information about email and password into Traveller object from session");
        traveller.setEmail(email.trim());
        traveller.setHashPassword(hashPassword);

        log.debug("Get information about user's id from data base");
        int id = travellerDao.create(traveller);
        log.debug("Set id into Traveller's object from session");
        traveller.setId(id);

        log.debug("Redirect user to main page");
        response.sendRedirect("/");
        }catch(WrongData w){
            log.info(w.getMessage());
            request.getRequestDispatcher("/WEB-INF/error/emailError.jsp").forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost (request,response);
    }


}
