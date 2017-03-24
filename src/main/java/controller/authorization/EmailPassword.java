package controller.authorization;

import error.WrongData;
import model.Gender;
import model.Traveller;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static model.Traveller.TRAVELLER;

/**
 * if user has entered incorrect data throw exception which
 * is caught and user is redirected to error page
 * if data is right all entered parameters add to user and safe in sesion
 * then user is redirected to jsp to enter email and password
 * @author Ali Yan
 * @version 1.0
 */
@WebServlet("/registration/email")
public class EmailPassword extends HttpServlet {
    private static final Logger log = LogManager.getLogger(EmailPassword.class);
    /**
     * path to error page
     */
    private static final String ERROR_PATH = "/WEB-INF/error/dataError.jsp";
    /**
     * name of error for error page
     */
    private static final String ERROR_NAME = "error";

    @SuppressWarnings("javadoc")
    @Override
      protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.info("User's enterd to registration page");
        String firstName = request.getParameter("first_name");
        String lastName = request.getParameter("last_name");
    /**
     * check if first name and last name are correct and don't contain any numbers
     */
        try {
            if ((firstName.length() < 2)
                    || (lastName.length() < 2)) {
                throw new WrongData();
            }
            Pattern pattern = Pattern.compile("\\d+");
            Matcher matcher = pattern.matcher(firstName);
            if (!matcher.find()) {
                matcher = pattern.matcher(lastName);
                if (matcher.find()) {
                    throw new WrongData();
                }
            } else throw new WrongData();
        } catch (WrongData w) {
            log.error("User's entered wrong information");
            request.setAttribute(ERROR_NAME, "wrongname");
            request.getRequestDispatcher(ERROR_PATH).forward(request, response);
        }
        /**
         * create new object of traveller and add information about user
         */
        Traveller traveller = new Traveller();
        traveller.setFirstName(firstName);
        traveller.setLastName(lastName);
        /**
         * check if date of birthday isn't correct send to Error page
         * otherwise add to traveller
         */
        try {
            int day = Integer.parseInt(request.getParameter("day"));
            int month = Integer.parseInt(request.getParameter("month"));
            int year = Integer.parseInt(request.getParameter("year"));
            traveller.setDateOfBirth(LocalDate.of(year, month, day));
        } catch (NumberFormatException e) {
            log.error("Wrong data format");
            request.setAttribute(ERROR_NAME, "wrongformat");
            request.getRequestDispatcher(ERROR_PATH).forward(request, response);
        }
        /**
         *check if user has'nt chosen gender - send to error page
         * gender could be 0,1 or null
         * otherwise put add information to traveller
         */
        try {
            traveller.setGender(Gender.valueOf(Integer.parseInt
                    (request.getParameter("gender"))).get());
        } catch (NumberFormatException n) {
            log.error("User hasn't chosen a gender");
            request.setAttribute(ERROR_NAME, "wronggender");
            request.getRequestDispatcher(ERROR_PATH).forward(request, response);
        }
        /**
         * if all data correct send user to page for receiving email and password
         * data of user add to session
         */
        System.out.println(traveller);
        request.getSession().setAttribute(TRAVELLER, traveller);
        request.getRequestDispatcher("/WEB-INF/registration/email.jsp").forward(request, response);

    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
