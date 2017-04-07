package dao.h2;

import dao.RouteDao;
import model.Route;
import model.Traveller;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO layer for database H2 for table Routes
 * @see dao.RouteDao
 * @author Ali Yan
 * @version 1.0
 */
public class H2RouteDao implements RouteDao {
    private static final Logger log = LogManager.getLogger(H2RouteDao.class);
    private static final String SELECT_ALL_ROUTES =
            "SELECT r.id AS route, name, traveller_id, description, first_name, last_name" +
                    " FROM Route r, Traveller t WHERE traveller_id = t.id";

    private DataSource dataSource;

    public H2RouteDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public int create(Route route) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "INSERT INTO Route (name, traveller_id, description) VALUES (?, ?, ?)",
                     Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setObject(1, route.getName());
            preparedStatement.setObject(2, route.getTraveller().getId());
            preparedStatement.setObject(3, route.getDescription());

            preparedStatement.executeUpdate();

            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next())
                    route.setId(generatedKeys.getInt(1));
            }
            }catch (SQLException s){
            log.error (s.toString());
        }
        return route.getId();
    }

    @Override
    public void remove(int id) {
        String sql1 = "DELETE FROM PersonalRoute WHERE route_id =" + id;
        String sql2 = "DELETE FROM Route WHERE id = " + id;

        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {
            statement.executeUpdate(sql1);
            statement.executeUpdate(sql2);
        } catch (SQLException s) {
            log.error(s.toString());
        }
    }

    @Override
    public List<Route> getAll() {
        List<Route> routes = new ArrayList<>();

        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(SELECT_ALL_ROUTES)) {
            while (resultSet.next())
                routes.add(new Route(
                        resultSet.getInt("route"),
                        resultSet.getString("name"),
                        new Traveller(resultSet.getInt("traveller_id"),
                                resultSet.getString("first_name"),
                                resultSet.getString("last_name")),
                        resultSet.getString("description")));
        } catch (SQLException s) {
            log.error(s.toString());
        }
        return routes;
    }

    @Override
    public Route get(int id) {
        Route route = null;
        String sql = SELECT_ALL_ROUTES + " AND r.id=" + id;
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next())
                route = new Route(
                        resultSet.getInt("route"),
                        resultSet.getString("name"),
                        new Traveller(resultSet.getInt("traveller_id"),
                                resultSet.getString("first_name"),
                                resultSet.getString("last_name")),
                        resultSet.getString("description"));
        } catch (SQLException s) {
        log.error(s.getStackTrace());
    }
        return route;
    }

    @Override
    public List<Route> getAll(int id) {
        String sqlById = SELECT_ALL_ROUTES + " AND t.id = " + id;
        List<Route> routes = new ArrayList<>();

        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sqlById)) {

            while (resultSet.next()) {
                routes.add(new Route(
                        resultSet.getInt("route"),
                        resultSet.getString("name"),
                        new Traveller(resultSet.getInt("traveller_id"),
                                resultSet.getString("first_name"),
                                resultSet.getString("last_name")),
                        resultSet.getString("description")));
            }
        } catch (SQLException s) {
        log.error(s.getStackTrace());
    }

        return routes;

    }

    @Override
    public void update(String name, String description, int id) {

        String sql = "UPDATE Route SET name = '" + name + "', description = '" + description +
                "' WHERE id = " + id;
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {
            statement.executeUpdate(sql);
        } catch (SQLException s) {
            log.error(s.getStackTrace());
        }
    }
}