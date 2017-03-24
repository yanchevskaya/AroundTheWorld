package tags;

import controller.authorization.Authorization;
import lombok.SneakyThrows;
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
 * show information about user o
 */
public class ProfileTag extends TagSupport {
    private Traveller traveller;
    private static final Logger log = LogManager.getLogger(TagSupport.class);

    public Traveller getTraveller() {
        return traveller;
    }

    public void setTraveller(Traveller traveller) {
        this.traveller = traveller;
    }

    @Override
    public int doStartTag() throws JspException {
        Formatter formatter = new Formatter();
        LocalDate birth = traveller.getDateOfBirth();
        formatter.format("%td %tB",birth,birth);

        try {
            JspWriter out = pageContext.getOut();
            out.write("<h3>" + traveller.getFirstName() + " " + traveller.getLastName() +
                    "</br>" + formatter + "</h3><br/>");

            if (traveller.getCurrentCity() != null)
                out.write("<h3>"+traveller.getCurrentCity().toString() + "</h3><br/>");
         }catch (IOException e){
            log.error(e.getMessage());
        } finally {
             formatter.close();
        }

        return SKIP_BODY;
    }
}
