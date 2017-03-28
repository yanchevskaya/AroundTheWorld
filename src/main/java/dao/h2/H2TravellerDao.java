package dao.h2;

import dao.TravellerDao;
import lombok.SneakyThrows;
import model.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO layer for database H2 for table Travellers
 * @author Ali Yan
 * @version 1.0
 */
public class H2TravellerDao implements TravellerDao {
    private static final Logger log = LogManager.getLogger(H2RouteDao.class);

    private DataSource dataSource;

    public H2TravellerDao(DataSource dataSource){
        this.dataSource = dataSource;
    }

    @Override
    public int create(Traveller traveller) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "INSERT INTO Traveller (first_name, last_name, gender_id, date_of_birth, current_avatar, current_city, email, password)" +
                             "VALUES (?, ?, ?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setObject(1, traveller.getFirstName());
            preparedStatement.setObject(2, traveller.getLastName());
            preparedStatement.setObject(3, traveller.getGender().ordinal() + 1);
            preparedStatement.setObject(4, traveller.getDateOfBirth());
            if (traveller.getAvatar()!=null)
            preparedStatement.setObject(5, traveller.getAvatar());
            else preparedStatement.setObject(5, null);
            if (traveller.getCurrentCity()!=null)
            preparedStatement.setObject(6, traveller.getCurrentCity().getId());
            else preparedStatement.setObject(6, null);
            preparedStatement.setObject(7, traveller.getEmail());
            preparedStatement.setObject(8, traveller.getHashPassword());

            preparedStatement.executeUpdate();

            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {

                if (generatedKeys.next())
                    traveller.setId(generatedKeys.getInt(1));
                }
        }catch (SQLException s){
            log.error(s.getStackTrace());
        }
            return traveller.getId();
    }

    @Override
       public boolean checkEmail(String email) {
        boolean result = false;
        String sqlChange = "'" + email + "'";
        String selectEmail = "SELECT email FROM Traveller WHERE email = " + sqlChange;

        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(selectEmail)) {
            result =  resultSet.next();
        }catch (SQLException s){
        log.error(s.getStackTrace());
    }
        return result;
    }

    @Override
    public Traveller getIfExist(String email, String hashPassword) {

        String sqlEmail = "'"+email+"'";
        String sqlHashPassword = "'"+hashPassword+"'";

        String selectTraveller = "SELECT id, first_name, last_name, date_of_birth, current_city id, current_avatar " +
                "FROM Traveller WHERE email = "+sqlEmail +" AND password = "+sqlHashPassword;
        Traveller traveller = null;

        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(selectTraveller)) {

            while (resultSet.next()) {
                traveller = new Traveller(
                        resultSet.getInt("id"),
                        resultSet.getString("first_name"),
                        resultSet.getString("last_name"),
                        resultSet.getDate("date_of_birth").toLocalDate());
            }
        }catch (SQLException s){
        log.error(s.getStackTrace());
    }
        return traveller;
    }

    @Override
    public Traveller getById(int id) {
        String SELECT_TRAVELLER = "SELECT first_name, last_name, date_of_birth " +
                "FROM Traveller WHERE id = " + id;
        Traveller traveller = new Traveller();

        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(SELECT_TRAVELLER)) {
            while (resultSet.next())
                traveller = new Traveller(id,
                        resultSet.getString("first_name"),
                        resultSet.getString("last_name"),
                        resultSet.getDate("date_of_birth").toLocalDate());
        } catch (SQLException s) {
            log.error(s.getStackTrace());
        }
        return traveller;
    }

    @Override
    public List <Traveller> getByName(String name) {
        List <Traveller> travellers = new ArrayList<>();
        String SELECT_TRAVELLER = "SELECT id, first_name, last_name " +
                "FROM Traveller WHERE first_name LIKE '%"+name+"%' OR last_name LIKE '%"+name+"%' "; 


        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(SELECT_TRAVELLER)) {
            while (resultSet.next())
                travellers.add(new Traveller(
                        resultSet.getInt("id"),
                        resultSet.getString("first_name"),
                        resultSet.getString("last_name")));
        } catch (SQLException s) {
            log.error(s.getStackTrace());
        }

        return travellers;
    }

    @Override
    public List <Traveller> getByName(String firstName, String lastName) {
        List <Traveller> travellers = new ArrayList<>();
        String SELECT_TRAVELLER = "SELECT id, first_name, last_name " +
                "FROM Traveller WHERE first_name LIKE '%"+firstName+"%' AND last_name LIKE '%"+lastName+"%' " +
                "OR first_name LIKE '%"+lastName +"%' AND last_name LIKE '%"+firstName+"%'";

        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(SELECT_TRAVELLER)) {
            while (resultSet.next())
                travellers.add(new Traveller(
                        resultSet.getInt("id"),
                        resultSet.getString("first_name"),
                        resultSet.getString("last_name")));
        } catch (SQLException s) {
            log.error(s.getStackTrace());
        }

        return travellers;
    }

    @Override
    public List<Traveller> getAll(int id) {
        String sql = "SELECT t.id, first_name, last_name, gender_id, date_of_birth " +
                "FROM Traveller t, Gender g " +
                "WHERE t.gender_id = g.id AND t.id!="+id;
        List<Traveller> travellers = new ArrayList<>();

        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)){
            while (resultSet.next()) {
                travellers.add(new Traveller(
                        resultSet.getInt("id"),
                        resultSet.getString("first_name"),
                        resultSet.getString("last_name"),
                        Gender.valueOf(resultSet.getInt("gender_id") - 1),
                        resultSet.getDate("date_of_birth").toLocalDate()));
            }
        } catch (SQLException s) {
        log.error(s.getStackTrace());
    }
        return travellers;
    }
 }