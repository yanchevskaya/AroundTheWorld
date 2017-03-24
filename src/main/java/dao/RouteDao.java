package dao;

import model.Country;
import model.Route;

import java.util.List;
import java.util.Optional;

public interface RouteDao {


    int create(Route route);

    //    update()

    void remove(int id);

    Route get (int id);

    List<Route> getAll();

    List<Route> getAll(int id);


    void update(String name, String description, int id);
}
