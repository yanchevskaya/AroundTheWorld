package tags.bean;
import model.Country;
import model.Traveller;

import java.util.Collection;

public class TravellerCollection {
    private Collection<Traveller> travellers;
    private Collection<Country> countries;
    private int amount;

    public TravellerCollection(){}

    public TravellerCollection(Collection<Traveller> travellers, int amount) {
        this.amount = amount;
        this.travellers = travellers;
    }

    public void setTravellers(Collection<Traveller> travellers) {
        this.travellers = travellers;
    }

    public Collection<Traveller> getTravellers() {
        return travellers;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}