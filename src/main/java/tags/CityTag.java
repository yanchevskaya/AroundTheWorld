package tags;

import lombok.SneakyThrows;
import model.City;
import tags.bean.CityCollection;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.util.Collection;

/**
 * Tag to show information about cities
 * @author Ali Yan
 * @version 1.0
 */
public class CityTag extends TagSupport{
    private CityCollection cityList;

    public void setCityList(CityCollection cityList) {
        this.cityList = cityList;
    }

    @SneakyThrows
    @Override
    public int doStartTag() throws JspException {

        JspWriter out = pageContext.getOut();
        Collection<City> listOfCities = cityList.getCities();

        for (City city : listOfCities) {
            System.out.println(city.getName());

            out.write("<tr><td><a href = \"city=" + city.getName() + "\">" +
                    city.getName() + "</br></a></td></tr>");
        }

        out.write("</table>");
        for (int i = 1; i<=cityList.getAmount(); i++) {
            out.write("<a href = \"countries?country=" + cityList.getCountryName() +
                    "&page="+i+ "\">"+i+"</a>&nbsp&nbsp");
        }
        return SKIP_BODY;
    }


}
