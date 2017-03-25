package model;

import lombok.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter

/**
 * Bean for saving information about Countries
 * @author Ali Yan
 * @version 1.0
 */
public class Route {
    /**
     * amount of id - autoincrement in data base
     */
    private int id;
    private String name;
    /**
     * Traveller who created route
     */
    private Traveller traveller;
    private String description;
}
