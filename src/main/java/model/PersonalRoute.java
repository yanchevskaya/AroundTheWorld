package model;

import lombok.*;

import java.time.LocalDate;
import java.time.ZonedDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class PersonalRoute {
//    id INT AUTO_INCREMENT PRIMARY KEY,
    private int id;
//    city_id INT,
    private City city;
//    traveller_id INT,
    private Route route;
//    start DATE,
    private String description;

}
