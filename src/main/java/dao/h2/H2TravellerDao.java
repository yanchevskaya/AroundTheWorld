package dao.h2;

import dao.TravellerDao;
import lombok.SneakyThrows;
import model.*;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class H2TravellerDao implements TravellerDao {

    private DataSource dataSource;

    public H2TravellerDao(DataSource dataSource){
        this.dataSource = dataSource;
    }


    @Override
    @SneakyThrows
    public int create(Traveller traveller) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "INSERT INTO Traveller (first_name, last_name, gender_id, date_of_birth, current_avatar, current_city, email, password)" +
                             "VALUES (?, ?, ?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setObject(1, traveller.getFirstName());
            preparedStatement.setObject(2, traveller.getLastName());
            preparedStatement.setObject(3, traveller.getGender().ordinal() + 1);//TODO: переделать на спецполе
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
        }
            return traveller.getId();
    }
    /**
     * search email
     * @return true if email was found
     */
    @Override
    @SneakyThrows
    public boolean checkEmail(String email) {
        String sqlChange = "'" + email + "'";
        String selectEmail = "SELECT email FROM Traveller WHERE email = " + sqlChange;

        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(selectEmail)) {
            return resultSet.next();
        }
    }

    @Override
    @SneakyThrows
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
        }
        return traveller;
    }


    @Override
    public void remove(Traveller traveller) {

    }

    @SneakyThrows
    @Override
    public List<Traveller> getSomeRecords(int start, int total, int id) {
        List <Traveller> travellers = new ArrayList<>();
        String sql = "SELECT id, first_name, last_name " +
                "FROM Traveller WHERE id != "+id+ " LIMIT "+(start-1)+", "+total;

        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next())
                travellers.add(new Traveller(
                        resultSet.getInt("id"),
                        resultSet.getString("first_name"),
                        resultSet.getString("last_name")));
                }
        return travellers;
    }

    @Override
    @SneakyThrows
    public Traveller getById(int id) {
        String SELECT_TRAVELLER = "SELECT first_name, last_name, date_of_birth " +
                "FROM Traveller WHERE id = "+id;
        Traveller traveller = new Traveller();

        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(SELECT_TRAVELLER)) {
            while (resultSet.next())
                traveller = new Traveller(id,
                        resultSet.getString("first_name"),
                        resultSet.getString("last_name"),
                        resultSet.getDate("date_of_birth").toLocalDate());  }

        return traveller;
    }



    @Override
    @SneakyThrows
    public List <Traveller> getByName(String firstName, String lastName) {
        List <Traveller> travellers = new ArrayList<>();
        String SELECT_TRAVELLER = "SELECT id, first_name, last_name " +
                "FROM Traveller WHERE first_name LIKE '%"+firstName+"%' AND last_name LIKE '%"+lastName+"%' " +
                "OR first_name LIKE '%"+lastName +"%' OR last_name LIKE '%"+firstName+"%'";

        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(SELECT_TRAVELLER)) {
            while (resultSet.next())
                travellers.add(new Traveller(
                        resultSet.getInt("id"),
                        resultSet.getString("first_name"),
                        resultSet.getString("last_name")));  }

        return travellers;
    }

    @Override
    @SneakyThrows
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
                        Gender.valueOf(resultSet.getInt("gender_id") - 1).orElseThrow(() -> new RuntimeException("No such gender!")),
                        resultSet.getDate("date_of_birth").toLocalDate()));
            }
        }
        return travellers;
    }
 }