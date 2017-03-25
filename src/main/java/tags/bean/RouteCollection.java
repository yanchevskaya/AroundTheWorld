package tags.bean;

import model.Route;

import java.io.Serializable;
import java.util.Collection;

/**
 * Bean for RoutesTag
 * @author Ali Yan
 * @version 1.0
 */
public class RouteCollection implements Serializable {
        private Collection<Route> routes;
        private int amount;

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
        @SuppressWarnings("unused")
        public void setAmount(int amount) {
            this.amount = amount;
        }
    }
