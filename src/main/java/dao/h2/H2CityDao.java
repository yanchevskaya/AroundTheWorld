package dao.h2;

import dao.CityDao;
import lombok.SneakyThrows;
import model.City;
import model.Country;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class H2CityDao implements CityDao{

    private DataSource dataSource;

    public H2CityDao(DataSource dataSource){
        this.dataSource = dataSource;
    }

    @SneakyThrows
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
        }

        return city;
    }

    @Override
    public String save() {
        return null;
    }

    @Override
    public void remove(City city) {

    }

    @Override
    @SneakyThrows
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
                                resultSet.getString ("name"))
                ));
        }

        return cities;
    }


}


