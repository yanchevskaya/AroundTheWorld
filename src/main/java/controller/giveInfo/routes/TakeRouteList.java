package controller.giveInfo.routes;

import model.Route;
import tags.bean.RouteCollection;
import tags.bean.TravellerCollection;

import java.util.List;

public class TakeRouteList {
    RouteCollection r;
    TravellerCollection t;


    RouteCollection takeRoutes(int TOTAL, int pageStart, List<Route> route) {
        int pageEnd;
        int count=0;
        if (pageStart!=0)
        pageStart = (pageStart - 1) * TOTAL;
        pageEnd = pageStart + TOTAL;

        if (route == null) {
             r = new RouteCollection(route, count);
        }
        if (route.size() < pageEnd)
            pageEnd = route.size();
            List <Route> shortList = route.subList(pageStart, pageEnd);
            count = route.size() % TOTAL != 0 ? route.size() / TOTAL + 1 : route.size() / TOTAL;
            r = new RouteCollection(shortList, count);

        return r;
    }


}
