package tags.bean;

import model.Route;

import java.util.Collection;

/**
 * Bean for list of Routes
 * @author Ali Yan
 * @version 1.0
 */
public class RouteCollection {

        private Collection<Route> routes;
        private int amount;

        public RouteCollection(){}

        public RouteCollection(Collection<Route> routes, int amount) {
            this.amount = amount;
            this.routes = routes;
                    }
        public Collection<Route> getRoutes() {
            return routes;
        }
        public void setRoutes(Collection<Route> routes) {
            this.routes = routes;
        }

        public int getAmount() {
            return amount;
        }

        public void setAmount(int amount) {
            this.amount = amount;
        }


    }
