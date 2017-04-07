package tags;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import tags.bean.TravellerCollection;
import model.Traveller;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.util.Collection;

/**
 * Tag for displaying information about the traveller
 * @author Ali Yan
 * @version 1.0
 */
@SuppressWarnings("DanglingJavadoc")
public class TravellersTag extends TagSupport {
    private static final Logger log = LogManager.getLogger(TravellersTag.class);
    private TravellerCollection travellersList;

    @SuppressWarnings("unused")
    public void setTravellersList(TravellerCollection travellersList) {
        this.travellersList = travellersList;
    }

    @Override
    public int doStartTag() throws JspException {
        Collection<Traveller> listOfTravellers = travellersList.getTravellers();
        JspWriter out = pageContext.getOut();
        try {
        for (Traveller traveller : listOfTravellers) {
            if (traveller.getCurrentCity() == null)
                out.write("<tr><td><a href = \"\\travellers?id=" + traveller.getId() + "\">" +
                        traveller.getFirstName() + " " + traveller.getLastName() + "</br></a></td><td></td></tr>");
            else
                out.write("<tr><td><a href = \"\\travellers?id=" + traveller.getId() + "\">" +
                        traveller.getFirstName() + " " + traveller.getLastName() + "</a> </td><td>" + traveller.getCurrentCity().toString() + "</td><br/>");
        }
            out.write("</table>");
                /**
                 * print links for pages if it needs
                 */
                if (travellersList.getAmount()>0) {
                    for (int i = 1; i <= travellersList.getAmount(); i++) {
                        out.write("<a href = \"\\travellers?page=" + i + "\">" + i + "</a>&nbsp&nbsp");
                    }
                }
        }catch(IOException e) {
            log.error(e.getStackTrace());
        }
        return SKIP_BODY;
        }
    }





