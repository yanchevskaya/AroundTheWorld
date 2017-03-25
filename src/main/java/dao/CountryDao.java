package dao;

import model.Country;

import java.util.List;
import java.util.Optional;

/**
 * interface for DAO layer for Country table
 * @author Ali Yan
 * @version 1.0
 */
public interface CountryDao {

    /**
     * receive list of all countries
     * @return list of countries
     */
    List<Country> getAll();

    /**
     * default method
     * @param id - id of Country
     * @return Optional of Country
     */
    default Optional<Country> get(int id){
        return getAll().stream()
                .filter(country -> country.getId()==id)
                .findAny();
    }
}
