package dao.h2;

import dao.CountryDao;
import lombok.SneakyThrows;
import model.*;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class H2CountryDao implements CountryDao{

    public static final String SELECT_ALL_SQL =
            "SELECT id, name FROM Country";

    private DataSource dataSource;

    public H2CountryDao(DataSource dataSource){
        this.dataSource = dataSource;
    }

    @Override
    public String save() {
        return null;
    }

    @Override
    public void remove(Country country) {

    }

    @Override
    @SneakyThrows
    public List<Country> getAll() {
        List <Country> countries = new ArrayList<>();

        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(SELECT_ALL_SQL)){
            while (resultSet.next())
                countries.add(new Country(
                        resultSet.getInt("id"),
                        resultSet.getString("name")));
        }

        return countries;
    }
}
