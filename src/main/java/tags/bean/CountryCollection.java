package tags.bean;

import model.Country;

import java.util.Collection;

/**
 * Bean for list of Countries
 * @author Ali Yan
 * @version 1.0
 */
public class CountryCollection {
    private Collection<Country> countries;
    private int amount;

    public CountryCollection(){}

    public CountryCollection(Collection<Country> countries, int amount) {
        this.amount = amount;
        this.countries = countries;
    }
    public Collection<Country> getCountries() {
        return countries;
    }
    public void setCountries(Collection<Country> countries) {
        this.countries = countries;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }


}