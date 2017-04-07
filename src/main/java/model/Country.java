package model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@SuppressWarnings("DanglingJavadoc")
@AllArgsConstructor
@NoArgsConstructor

/**
 * Bean for saving information about Countries
 * @author Ali Yan
 * @version 1.0
 */
public class Country {
    /**
     * amount of id - autoincrement in data base
     */
    private int id;
    private String name;

    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    @SuppressWarnings("all")
    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof Country)) return false;
        final Country other = (Country) o;
        if (!other.canEqual((Object) this)) return false;
        if (this.getId() != other.getId()) return false;
        final Object this$name = this.getName();
        final Object other$name = other.getName();
        if (this$name == null ? other$name != null : !this$name.equals(other$name)) return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        result = result * PRIME + this.getId();
        final Object $name = this.getName();
        result = result * PRIME + ($name == null ? 43 : $name.hashCode());
        return result;
    }

    private boolean canEqual(Object other) {
        return other instanceof Country;
    }

    @Override
    public String toString() {
        return this.getName();
    }
}
