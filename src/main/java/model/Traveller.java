package model;

import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;

@SuppressWarnings("DanglingJavadoc")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

/**
 * Bean for saving information about Countries
 * @author Ali Yan
 * @version 1.0
 */
public class Traveller implements Serializable{
    /**
     * parameter for saving user in session
     */
    public static String TRAVELLER = "traveller";
    /**
     * autoincrement in DB
     */
    private int id;
    /**
     * can't be null in DB
     */
    private String firstName;
    /**
     *can't be null in DB
     */
    private String lastName;
    private Gender gender;
    private LocalDate dateOfBirth;
    private City currentCity;
    private Photo avatar;
    /**
     *can't be null in DB
     */
    private String email;
    /**
     *can't be null in DB
     */
    private String hashPassword;

    public Traveller(int id, String firstName, String lastName){
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        }

    public Traveller(int id, String firstName, String lastName, LocalDate dateOfBirth){
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
    }

    public Traveller(int id, String firstName, String lastName, Gender gender,
                     LocalDate dateOfBirth, City currentCity, Photo avatar){
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
        this.currentCity = currentCity;
        this.avatar = avatar;
    }

    public Traveller(int id, String firstName, String lastName, Gender gender,
                     LocalDate dateOfBirth, Photo avatar){
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
        this.avatar = avatar;
    }

    public Traveller(int id, String firstName, String lastName, Gender gender,
                     LocalDate dateOfBirth, City currentCity){
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
        this.currentCity = currentCity;
        }

    public Traveller(int id, String firstName, String lastName, Gender gender,LocalDate dateOfBirth){
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
           }
}
