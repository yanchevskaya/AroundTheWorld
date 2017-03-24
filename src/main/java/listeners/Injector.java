package listeners;

import dao.h2.H2CityDao;
import dao.h2.H2CountryDao;
import dao.h2.H2RouteDao;
import dao.h2.H2TravellerDao;

import javax.sql.DataSource;
import javax.annotation.Resource;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class Injector implements ServletContextListener {

    @Resource(name = "jdbc/TestDB")
    private DataSource dataSource;

    @Override
    public void contextInitialized (ServletContextEvent sce){
        H2TravellerDao h2TravellerDao = new H2TravellerDao(dataSource);
        H2CountryDao h2CountryDao = new H2CountryDao(dataSource);
        H2CityDao h2CityDao = new H2CityDao(dataSource);
        H2RouteDao h2RouteDao = new H2RouteDao(dataSource);
        sce.getServletContext().setAttribute("TravellerDao", h2TravellerDao);
        sce.getServletContext().setAttribute("CountryDao", h2CountryDao);
        sce.getServletContext().setAttribute("CityDao", h2CityDao);
        sce.getServletContext().setAttribute("RouteDao", h2RouteDao);
    }
}
