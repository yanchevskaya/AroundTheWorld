package dao;

import model.Country;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface CountryDao {


    String save();
    //    update()

    void remove(Country country);

    List<Country> getAll();

    default Optional<Country> get(int id){
        return getAll().stream()
                .filter(country -> country.getId()==id)
                .findAny();
    }
}
