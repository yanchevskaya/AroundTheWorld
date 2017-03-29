package tags;

import model.Country;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import tags.bean.CountryCollection;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.util.Collection;

/**
 * Tag for displaying information about countries
 * @author Ali Yan
 * @version 1.0
 */
public class CountryTag extends TagSupport {
    private static final Logger log = LogManager.getLogger(CountryTag.class);
    private CountryCollection countryList;

public void setCountryList(CountryCollection countryList) {
    this.countryList = countryList;
}

    @Override
    public int doStartTag() throws JspException {
        JspWriter out = pageContext.getOut();
        try{
            Collection<Country> listOfCountries = countryList.getCountries();

            for (Country country : listOfCountries) {

                out.write("<tr><td><a href = \"cities?country=" + country.getName() + "\">" +
                        country.getName() + "</br></a></td></tr>");
            }

            out.write("</table>");
            /**
             * print links for pages if it needs
             */
            for (int i = 1; i <= countryList.getAmount(); i++) {
                out.write("<a href = \"countries?page=" + i + "\">" + i + "</a>&nbsp&nbsp");
            }
        }catch (IOException e){
        log.error(e.getMessage());
    }
        return SKIP_BODY;
    }

}


