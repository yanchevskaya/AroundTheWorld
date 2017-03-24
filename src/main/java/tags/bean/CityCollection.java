package tags.bean;

import model.City;

import java.util.Collection;

/**
 * Bean for list of Cities
 * @author Ali Yan
 * @version 1.0
 */
public class CityCollection {
    private Collection<City> cities;
    private int amount;
    private String countryName;


    public CityCollection(Collection<City> cities, int amount, String countryName) {
        this.amount = amount;
        this.cities = cities;
        this.countryName = countryName;
    }

    public Collection<City> getCities() {
        return cities;
    }

    public void setCountries(Collection<City> cities) {
        this.cities = cities;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }
}