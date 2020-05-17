package data.Person;

import java.util.Date;
import app.Algorithms.*;


/**
 * <h1>Student</h1>
 * extends {@link Person}
 * Defines a student who attends the school.
 * @see Faculty
 * @author Lucas Demchik
 * @version 0.1
 * @since May 2020
 */
public class Student extends Person{
    private Date dateOfBirth;
    private double gpa;
    private Date registrationDate;

    public Student(String firstName, String middleName, String lastName,
                   String email, String phone, String address, String city,
                   String state, String zip, Date dateOfBirth, double gpa,
                   Date registrationDate){
        super(firstName, middleName, lastName, email, phone, address, city, state, zip);
        this.dateOfBirth = dateOfBirth;
        this.gpa = gpa;
        this.registrationDate = registrationDate;
    }

    /**
     * Accessor Method
     * @return dateOfBirth
     */
    public Date getDateOfBirth() { return dateOfBirth; }

    /**
     * Mutator Method
     * @param dateOfBirth
     */
    public void setDateOfBirth(Date dateOfBirth) { this.dateOfBirth = dateOfBirth; }

    /**
     * Accessor Method
     * @return gpa
     */
    public double getGpa() { return gpa; }

    /**
     * Mutator Method
     * @param gpa
     */
    public void setGpa(double gpa) { this.gpa = gpa; }

    /**
     * Accessor Method
     * @return registrationDate
     */
    public Date getRegistrationDate() { return registrationDate; }

    /**
     * Mutator Method
     * @param registrationDate
     */
    public void setRegistrationDate(Date registrationDate) { this.registrationDate = registrationDate; }

    /**
     * Overridden Mutator Method using a student specific algorithm to generate a unique ID
     * @see StudentIDGenerator
     */
    @Override
    public void setID() {
        id = new StudentIDGenerator().generateID();
    }


}
