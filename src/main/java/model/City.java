package model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class City extends Country{
//    id INT AUTO_INCREMENT PRIMARY KEY,
    private int id;
//    name VARCHAR(100) NOT NULL,
    private String name;
//    country_id INT,
    private Country country;

    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public Country getCountry() {
        return this.country;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof City)) return false;
        final City other = (City) o;
        if (!other.canEqual((Object) this)) return false;
        if (this.getId() != other.getId()) return false;
        final Object this$name = this.getName();
        final Object other$name = other.getName();
        if (this$name == null ? other$name != null : !this$name.equals(other$name)) return false;
        final Object this$country = this.getCountry();
        final Object other$country = other.getCountry();
        if (this$country == null ? other$country != null : !this$country.equals(other$country)) return false;
        return true;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        result = result * PRIME + this.getId();
        final Object $name = this.getName();
        result = result * PRIME + ($name == null ? 43 : $name.hashCode());
        final Object $country = this.getCountry();
        result = result * PRIME + ($country == null ? 43 : $country.hashCode());
        return result;
    }

    protected boolean canEqual(Object other) {
        return other instanceof City;
    }

    public String toString() {
        return this.getCountry().toString() + " - " +this.getName();
    }
}
