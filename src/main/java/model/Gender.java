package model;

import java.util.Arrays;

/**
 * Enumeration for choosing gender of user
 * @author Ali Yan
 * @version 1.0
 */
public enum Gender {
    MALE, FEMALE;

    /**
     *
     * @param id - id og Gender 0 = male, 1 = female
     * @return Optional of Gender
     */
    public static Gender valueOf(int id) {

        return Arrays.stream(values())
                .filter(gender -> gender.ordinal() == id)
                .findAny().orElse(null);
    }
}
