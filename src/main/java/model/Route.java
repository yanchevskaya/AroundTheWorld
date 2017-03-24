package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Route {
//    id INT AUTO_INCREMENT PRIMARY KEY,
    private int id;
//    name VARCHAR(500) NOT NULL,
    private String name;
    private Traveller traveller;
    private String description;
}
