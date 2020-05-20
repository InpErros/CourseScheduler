package data.Person;

import app.Algorithms.*;


/**
 * {@code Student } defines a student who attends the college.
 * @see Person
 * @see ScheduledStudent
 * @see data.Database.StudentDatabase
 * @author Lucas Demchik
 * @version 0.1
 */
public class Student extends Person{
    private String dateOfBirth;
    private String gpa;
    private String registrationDate;

    /**
     * Initializes a new Student object with blank strings
     */
    public Student(){
        super(" ", " ", " ", " ", " ", " ", " ", " ", " ");
        this.dateOfBirth = " ";
        this.gpa = " ";
        this.registrationDate = " ";
        this.id = " ";
    }

    /**
     * Initiializes a new Student with the given data
     * @param firstName
     * @param middleName
     * @param lastName
     * @param email
     * @param phone
     * @param address
     * @param city
     * @param state
     * @param zip
     * @param dateOfBirth
     * @param gpa
     * @param registrationDate
     */
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

    /**
     * Initializes a new Student with tht given data
     * @param firstName
     * @param middleName
     * @param lastName
     * @param email
     * @param phone
     * @param address
     * @param city
     * @param state
     * @param zip
     * @param dateOfBirth
     * @param gpa
     * @param registrationDate
     * @param id
     */
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
     * Initializes a new Student as a copy of another
     * @param s Student object to be copied
     */
    public Student(Student s){
        this(s.getFirstName(),s.getMiddleName(),s.getLastName(),s.getEmail(),s.getPhone(),s.getAddress(),s.getCity(),s.getState(),s.getZip(), s.dateOfBirth,s.gpa,s.registrationDate, s.id);
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

    /**
     * @return an outputtable string for file IO
     */
    @Override
    public String toOutputString(){
        return getFirstName() + "," + getMiddleName() + "," + getLastName()
                + "," + getEmail() + "," + getPhone() + "," + getAddress()
                + "," + getCity() + "," + getState() + "," + getZip() + "," + dateOfBirth
                + "," + gpa  + "," + registrationDate + "," + getID() + ",";
    }

    /**
     * @return a string representing the data from the oject
     */
    public String toString(){
        return super.toString() + "Date of Birth: " + dateOfBirth
                + "\n Registration Date: " + registrationDate
                + "\nGPA: " + gpa + "\n";
    }

    /**
     * @return a string containing the name and id of the student
     */
    public String toShortString(){
        return getFirstName() + " " + getLastName() +
                "\nStudent ID: " + getID() + "\n";
    }

}
