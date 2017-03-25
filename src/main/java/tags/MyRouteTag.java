package tags;

import model.Route;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import tags.bean.RouteCollection;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.util.Collection;

/**
 * Tag for displaying information about the user's routes
 * @author Ali Yan
 * @version 1.0
 */
public class MyRouteTag extends TagSupport {
    private static final Logger log = LogManager.getLogger(MyRouteTag.class);

    private RouteCollection routeList;

    public void setRouteList(RouteCollection routeList) {
        this.routeList = routeList;
    }

    @Override
    public int doStartTag() throws JspException {
        JspWriter out = pageContext.getOut();
        try {
        if (routeList != null) {
            Collection<Route> listOfRoutes = routeList.getRoutes();

            for (Route route : listOfRoutes) {

                out.write("<tr><td><a href = \"myroutes?id=" + route.getId() + "\">" +
                        route.getName() + "</a><td>" + route.getDescription() + "</td>" +
                        "<td><input type = \"checkbox\" name = \"id\" value = \"" + route.getId() + "\">" +
                        "</td></br></td></tr>");
            }

            out.write("</table>");
            /**
             * print links for pages if it needs
             */
            for (int i = 1; i <= routeList.getAmount(); i++) {
                out.write("<a href = \"myroutes?page=" + i + "\">" + i + "</a>&nbsp&nbsp");
            }
        }
        }catch (IOException e){
                log.error(e.getMessage());
            }
        return SKIP_BODY;
        }
    }