package tags;

import model.City;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import tags.bean.CityCollection;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.util.Collection;

/**
 * Tag for displaying information about cities
 * @author Ali Yan
 * @version 1.0
 */
public class CityTag extends TagSupport{
    private static final Logger log = LogManager.getLogger(CityTag.class);
    private CityCollection cityList;

    public void setCityList(CityCollection cityList) {
        this.cityList = cityList;
    }

    @Override
    public int doStartTag() throws JspException {
        JspWriter out = pageContext.getOut();
        try{
            Collection<City> listOfCities = cityList.getCities();

            for (City city : listOfCities) {
                out.write("<tr><td><a href = \"city=" + city.getName() + "\">" +
                        city.getName() + "</br></a></td></tr>");
            }

            out.write("</table>");
            if (cityList.getAmount()!=0)
            for (int i = 1; i <= cityList.getAmount(); i++) {
                out.write("<a href = \"cities?country=" + cityList.getCountryName() +
                        "&page=" + i + "\">" + i + "</a>&nbsp&nbsp");
            }
        }catch (IOException e){
        log.error(e.getMessage());
    }
        return SKIP_BODY;
    }


}
