package model;

import lombok.Value;

@Value
public class Photo {
//    id INT AUTO_INCREMENT PRIMARY KEY,
    private int id;
    private String photoName;
//    photo BLOB NOT NULL
    private String photoLink; //link for cloud?
}
