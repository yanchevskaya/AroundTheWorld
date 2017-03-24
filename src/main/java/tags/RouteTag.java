package tags;

import lombok.SneakyThrows;
import model.Country;
import model.Route;
import tags.bean.CountryCollection;
import tags.bean.RouteCollection;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.util.Collection;


public class RouteTag extends TagSupport{

        private RouteCollection routeList;

        public void setRouteList(RouteCollection routeList) {
            this.routeList = routeList;
        }

        @SneakyThrows
        @Override
        public int doStartTag() throws JspException {

            JspWriter out = pageContext.getOut();
            if (routeList != null) {
                    Collection<Route> listOfRoutes = routeList.getRoutes();

                    for (Route route : listOfRoutes) {

                        out.write("<tr><td><a href = \"routes?id=" + route.getId() + "\">" +
                                route.getName() + "</a><td>" + route.getDescription() + "</td>" +
                                "<td>" + route.getTraveller().getFirstName() + " " + route.getTraveller().getLastName() +
                                "</td></br></td></tr>");
                    }

                    out.write("</table>");
                    for (int i = 1; i <= routeList.getAmount(); i++) {
                        out.write("<a href = \"routes?page=" + i + "\">" + i + "</a>&nbsp&nbsp");
                    }
                }

                return SKIP_BODY;
            }
        }



