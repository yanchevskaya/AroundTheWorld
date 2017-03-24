package tags;
import lombok.SneakyThrows;
import model.Country;
import tags.bean.CountryCollection;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.util.Collection;

/**
 * Tag to show information about counties to user
 * @author Ali Yan
 * @version 1.0
 */
public class CountryTag extends TagSupport {
    private CountryCollection countryList;

public void setCountryList(CountryCollection countryList) {
    this.countryList = countryList;
}

    @SneakyThrows
    @Override
    public int doStartTag() throws JspException {

        JspWriter out = pageContext.getOut();
        Collection<Country> listOfCountries = countryList.getCountries();

        for (Country country : listOfCountries) {
            System.out.println(country.getName());

            out.write("<tr><td><a href = \"countries?country=" + country.getName() + "\">" +
                    country.getName() + "</br></a></td></tr>");
       }

       out.write("</table>");
        for (int i = 1; i<=countryList.getAmount(); i++) {
            out.write("<a href = \"countries?page=" + i + "\">"+i+"</a>&nbsp&nbsp");
        }


        return SKIP_BODY;
    }

}


