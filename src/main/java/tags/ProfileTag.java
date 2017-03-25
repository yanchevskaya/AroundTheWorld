package tags;

import model.Traveller;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Formatter;

/**
 * Tag for displaying information about user
 * @author Ali Yan
 * @version 1.0
 */
public class ProfileTag extends TagSupport {
    private Traveller traveller;
    private static final Logger log = LogManager.getLogger(ProfileTag.class);

    public void setTraveller(Traveller traveller) {
        this.traveller = traveller;
    }

    @Override
    public int doStartTag() throws JspException {
        /**
         * get new format of date of birth
         */
        Formatter formatter = new Formatter();
        LocalDate birth = traveller.getDateOfBirth();
        formatter.format("%td %tB",birth,birth);
        JspWriter out = pageContext.getOut();
        try {
            out.write("<h3>" + traveller.getFirstName() + " " + traveller.getLastName() +
                     "<br/>"+formatter + "</h3>");

            if (traveller.getCurrentCity() != null)
                out.write("<h4>"+traveller.getCurrentCity().toString() + "</h4>");
        }catch (IOException e){
            log.error(e.getMessage());
        } finally {
             formatter.close();
        }

        return SKIP_BODY;
    }
}
