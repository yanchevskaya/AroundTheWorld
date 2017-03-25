package dao;

import model.Route;

import java.util.List;

/**
 * DAO layer for table Route
 * @author Ali Yan
 * @version 1.0
 */
public interface RouteDao {

    /**
     * add Route to database
     * @param route - object Route which was created by user
     * @return autoincrement id of route
     */
    int create(Route route);

    /**
     * remove Route from database
     * @param id - id of Route
     */
    void remove(int id);

    /**
     * get information about root
     * @param id - if of route
     * @return object Route
     */
    Route get (int id);

    /**
     * receive information about all roots
     * @return list of all routes
     */
    List<Route> getAll();

    /**
     * receive information about all roots which user has
     * @param id - id of user
     * @return list of routes which user has
     */
    List<Route> getAll(int id);

    /**
     * change information about user's route
     * @param name - name of route
     * @param description - description of route
     * @param id - id of user
     */
    void update(String name, String description, int id);
}
