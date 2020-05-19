package data.Person;

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
    private String dateOfBirth;
    private String gpa;
    private String registrationDate;

    public Student(){
        super(" ", " ", " ", " ", " ", " ", " ", " ", " ");
        this.dateOfBirth = " ";
        this.gpa = " ";
        this.registrationDate = " ";
    }

    public Student(String firstName, String middleName, String lastName,
                   String email, String phone, String address, String city,
                   String state, String zip, String dateOfBirth, String gpa,
                   String registrationDate){
        super(firstName, middleName, lastName, email, phone, address, city,
                   state, zip);
        this.dateOfBirth = dateOfBirth;
        this.gpa = gpa;
        this.registrationDate = registrationDate;
    }

    public Student(String firstName, String middleName, String lastName,
                   String email, String phone, String address, String city,
                   String state, String zip, String dateOfBirth, String gpa,
                   String registrationDate, String id){
        super(firstName, middleName, lastName, email, phone, address, city,
                   state, zip);
        this.dateOfBirth = dateOfBirth;
        this.gpa = gpa;
        this.registrationDate = registrationDate;
        this.id = id;
    }

    /**
     * Accessor Method
     * @return dateOfBirth
     */
    public String getDateOfBirth() { return dateOfBirth; }

    /**
     * Mutator Method
     * @param dateOfBirth new value
     */
    public void setDateOfBirth(String dateOfBirth) { this.dateOfBirth = dateOfBirth; }

    /**
     * Accessor Method
     * @return gpa
     */
    public String getGpa() { return gpa; }

    /**
     * Mutator Method
     * @param gpa new value
     */
    public void setGpa(String gpa) { this.gpa = gpa; }

    /**
     * Accessor Method
     * @return registrationDate
     */
    public String getRegistrationDate() { return registrationDate; }

    /**
     * Mutator Method
     * @param registrationDate new value
     */
    public void setRegistrationDate(String registrationDate) { this.registrationDate = registrationDate; }

    /**
     * Overridden Method using a algorithm selected at runtime to generate the ID
     * @see IDgenerator
     */
    @Override
    public void makeID() {
        id = "s" + getGeneratorAlgorithm().generateID();
    }

    @Override
    public String toOutputString(){
        return getFirstName() + "," + getMiddleName() + "," + getLastName()
                + "," + getEmail() + "," + getPhone() + "," + getAddress()
                + "," + getCity() + "," + getState() + "," + getZip() + "," + dateOfBirth
                + "," + gpa  + "," + registrationDate + "," + getID() + ",";
    }
}
