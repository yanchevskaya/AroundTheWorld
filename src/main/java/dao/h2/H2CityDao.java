package dao.h2;

import dao.CityDao;
import model.City;
import model.Country;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO layer for database H2 for table City 
 * @author Ali Yan
 * @version 1.0
 */
public class H2CityDao implements CityDao{
    private static final Logger log = LogManager.getLogger(H2CityDao.class);
    private DataSource dataSource;

    public H2CityDao(DataSource dataSource){
        this.dataSource = dataSource;
    }

    @Override
    public City getCity (int id){
        City city = null;
        String sql = "SELECT current_city, c.name as city_name, country_id, co.name AS country_name " +
                "  FROM Traveller t, City c, Country co " +
                "WHERE t.current_city = c.id AND c.country_id = co.id AND t.id=" +id;

        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next())
                city = new City(
                        resultSet.getInt("current_city"),
                        resultSet.getString("city_name"),
                        new Country(
                                resultSet.getInt("country_id"),
                                resultSet.getString("country_name")));
        }catch(SQLException s){
            log.error(s.getStackTrace());
        }

        return city;
    }

    @Override
    public List<City> getByCountryName(String name) {
        String sql = "SELECT c.id, c.name, country_id, co.id, co.name" +
                " FROM City c, Country co WHERE country_id = co.id AND co.name = '"+name+"'";

        List<City> cities = new ArrayList<>();

        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)){
            while (resultSet.next())
                cities.add(new City(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        new Country(resultSet.getInt("id"),
                                resultSet.getString ("name"))));

        }catch(SQLException s){
        log.error(s.getMessage());
    }
        return cities;
    }
}


