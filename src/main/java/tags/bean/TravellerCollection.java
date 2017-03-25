package tags.bean;

import model.Traveller;

import java.io.Serializable;
import java.util.Collection;

/**
 * Bean for TravellersTag
 * @author Ali Yan
 * @version 1.0
 */
public class TravellerCollection implements Serializable {
    private Collection<Traveller> travellers;
    private int amount;

    public TravellerCollection(Collection<Traveller> travellers, int amount) {
        this.amount = amount;
        this.travellers = travellers;
    }
    @SuppressWarnings("unused")
    public void setTravellers(Collection<Traveller> travellers) {
        this.travellers = travellers;
    }

    public Collection<Traveller> getTravellers() {
        return travellers;
    }

    public int getAmount() {
        return amount;
    }
    @SuppressWarnings("unused")
    public void setAmount(int amount) {
        this.amount = amount;
    }
}