package dao;

import model.City;
import model.Traveller;

import java.util.List;
import java.util.Optional;

public interface TravellerDao {

    int create(Traveller traveller);

    Traveller getIfExist (String email, String hashPassword);


    //    update()
    void remove(Traveller traveller);

    List<Traveller> getAll(int id);

    Traveller getById(int id);



//    default Optional<Traveller> get(int id){
//        return getAll().stream()
//                .filter(traveller -> traveller.getId()==id)
//                .findAny();
//    }

    List<Traveller> getByName(String firstName, String lastName);

    List<Traveller> getSomeRecords(int start, int total, int id);


//    default Optional<Traveller> getByName(String firstName){
//        return getAll().stream()
//                .filter(traveller -> traveller.getFirstName().equals(firstName))
//                .findAny();
//    }

    boolean checkEmail(String email);

}
