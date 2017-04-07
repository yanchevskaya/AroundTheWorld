package dao;

import model.Traveller;

import java.util.List;

/**
 * DAO layer for table Traveller
 * @author Ali Yan
 * @version 1.0
 */
public interface TravellerDao {

    /**
     * add traveller to database
     * @param traveller - object Traveller which was created after registration
     * @return autoincrement id of traveller
     */
    int create(Traveller traveller);

    /**
     * check if traveller with entered email and password exists
     * @param email - email of Traveller
     * @param hashPassword - hashPassword of traveller
     * @return object traveller if exists or null
     */
    Traveller getIfExist (String email, String hashPassword);

    /**
     * get list of all travellers except user who wants to receive the information
     * @param id - id of user
     * @return list of traveller
     */
    List<Traveller> getAll(int id);

    /**
     * receive information about traveller
     * @param id - id of traveller
     * @return object traveller
     */
    Traveller getById(int id);

    /**
     * receive list of Travellers if their name contains parameters
     * @param one - first name or last name try to find concurrences  with this parameter
     * @param two - first name or last name try to find concurrences  with this parameter
     * @return List of Travellers if exists, otherwise - null
     */
    List<Traveller> getByName(String one, String two);

    /**
     * receive list of Travellers if their name contains parameters
     * @param name - first name or last name try to find concurrences  with this parameter
     * @return List of Travellers if exists, otherwise - null
     */
    List<Traveller> getByName(String name);

    /**
     * chek if email already exists
     * @param email - email of user
     * @return tru if email exists in database, otherwise return false
     */
    boolean checkEmail(String email);
}
