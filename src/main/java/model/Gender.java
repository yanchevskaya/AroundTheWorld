package model;

import java.util.Arrays;
import java.util.Optional;

/**
 * Enumeration for choosing gender of user
 * @author Ali Yan
 * @version 1.0
 */
public enum Gender {
    MALE, FEMALE;

    public static Optional<Gender> valueOf(int id) {
        return Arrays.stream(values())
                .filter(gender -> gender.ordinal() == id)
                .findAny(); 
    }
}
