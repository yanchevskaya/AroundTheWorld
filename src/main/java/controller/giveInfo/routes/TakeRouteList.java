package controller.giveInfo.routes;

import model.Route;
import tags.bean.RouteCollection;

import java.util.List;

/**
 * class for receiving information about routes for required page
 * @author Ali Yan
 * @version 1.0
 */
class TakeRouteList {

    /**
     * @param total - number of things to print
     * @param pageStart - from which element to print the information
     * @param route -list of routes
     * @return bean of routes
     */
    @SuppressWarnings("ConstantConditions")
     RouteCollection takeRoutes(int total, int pageStart, List<Route> route) {
        int pageEnd;
        int count=0;

        if (pageStart!=0)
        pageStart = (pageStart - 1) * total;
        pageEnd = pageStart + total;

        if (route == null)
             return new RouteCollection(route, count);

        if (route.size()>total)
        count = route.size() % total != 0 ? route.size() / total + 1 : route.size() / total;

        if (route.size() < pageEnd)
            pageEnd = route.size();

        List<Route> shortList = route.subList(pageStart, pageEnd);

        return new RouteCollection(shortList, count);
    }
}
