package dao;

import model.City;

import java.util.List;

/**
 * interface for DAO layer for City table
 * @author Ali Yan
 * @version 1.0
 */
public interface CityDao {
    /**
     * receive list of city
     * @param name  Country name
     * @return List of cities which Country contains
     */
    List<City> getByCountryName (String name);

    /**
     * receive City
     * @param id - id os City
     * @return City with specified id
     */
    City getCity(int id);

}

