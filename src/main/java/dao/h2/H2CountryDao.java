package dao.h2;

import dao.CountryDao;
import model.*;
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
public class H2CountryDao implements CountryDao{
    private static final Logger log = LogManager.getLogger(H2CityDao.class);
    private static final String SELECT_ALL_SQL =
            "SELECT id, name FROM Country";

    private DataSource dataSource;

    public H2CountryDao(DataSource dataSource){
        this.dataSource = dataSource;
    }

    @Override
    public List<Country> getAll() {
        List <Country> countries = new ArrayList<>();

        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(SELECT_ALL_SQL)){
            while (resultSet.next())
                countries.add(new Country(
                        resultSet.getInt("id"),
                        resultSet.getString("name")));
        } catch (SQLException s){
            log.error(s.getStackTrace());
        }

        return countries;
    }
}
