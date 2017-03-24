package tags;

import lombok.SneakyThrows;
import tags.bean.TravellerCollection;
import model.Traveller;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.util.Collection;

public class TravellersTag extends TagSupport {
    private TravellerCollection travellersList;

    public void setTravellersList(TravellerCollection travellersList) {
        this.travellersList = travellersList;
    }

    @SneakyThrows
    @Override
    public int doStartTag() throws JspException {

        JspWriter out = pageContext.getOut();
        Collection<Traveller> listOfTravellers = travellersList.getTravellers();

        for (Traveller traveller : listOfTravellers) {
            System.out.println(traveller.getFirstName());
            if (traveller.getCurrentCity() == null)
                out.write("<tr><td><a href = \"travellers?id=" + traveller.getId() + "\">" +
                        traveller.getFirstName() + " " + traveller.getLastName() + "</br></a></td><td></td></tr>");
            else
                out.write("<tr><td><a href = \"travellers?id=" + traveller.getId() + "\">" +
                        traveller.getFirstName() + " " + traveller.getLastName() + "</a> </td><td>" + traveller.getCurrentCity().toString() + "</td><br/>");
        }
        out.write("</table>");

        for (int i = 1; i<=travellersList.getAmount(); i++) {
            out.write("<a href = \"travellers?page=" + i + "\">"+i+"</a>&nbsp&nbsp");
        }


        return SKIP_BODY;
    }
}




