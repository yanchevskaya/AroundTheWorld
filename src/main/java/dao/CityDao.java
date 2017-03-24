package dao;

import model.City;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface CityDao {

    String save();
    //    update()

    void remove(City city);

    List<City> getByCountryName (String name);

    City getCity(int id);

}

