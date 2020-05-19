package data.Person;

/**
 * {@code Faculty} defines a Faculty member at the college
 * @see Person
 * @see Instructor
 * @see data.Database.FacultyDatabase
 * @author Lucas Demchik
 * @version 0.1
 */
public class Faculty extends Person{
    private String hireDate;
    private String tenured;

    /**
     * Initializes a Faculty Object with the given data
     * @param firstName
     * @param middleName
     * @param lastName
     * @param email
     * @param phone
     * @param address
     * @param city
     * @param state
     * @param zip
     * @param hireDate
     * @param tenured
     */
    public Faculty(String firstName, String middleName, String lastName,
                   String email, String phone, String address, String city,
                   String state, String zip, String hireDate, String tenured){

        super(firstName, middleName, lastName, email, phone, address, city, state, zip);
        this.hireDate = hireDate;
        this.tenured = tenured;
    }

    /**
     * Initialies a Faculty object with the given data
     * @param firstName
     * @param middleName
     * @param lastName
     * @param email
     * @param phone
     * @param address
     * @param city
     * @param state
     * @param zip
     * @param hireDate
     * @param tenured
     * @param id
     */
    public Faculty(String firstName, String middleName, String lastName,
                   String email, String phone, String address, String city,
                   String state, String zip, String hireDate, String tenured,
                   String id){

        super(firstName, middleName, lastName, email, phone, address, city, state, zip);
        this.hireDate = hireDate;
        this.tenured = tenured;
        this.id = id;
    }

    /**
     * Initializes a Faculty object filled with blank strings
     */
    public Faculty() {
        super(" ", " ", " ", " ", " ", " ", " ", " ", " ");
        hireDate = " ";
        tenured = " ";
        id = " ";
    }

    /**
     * Initializes a new Faculty object as a copy of another
     * @param f Faculty object to be copied
     */
    public Faculty(Faculty f){
        super(f);
        this.hireDate = f.hireDate;
        this.tenured = f.tenured;
    }

    /**
     * @return a reference to the hire date
     */
    public String getHireDate() { return hireDate; }

    /**
     * @return a reference to the tenured status
     */
    public String isTenured() { return tenured; }

    /**
     * Overridden method using algorithm selected at runtime to generate the ID
     * @see app.Algorithms.IDgenerator
     */
    @Override
    public void makeID() {
        id = "f" + getGeneratorAlgorithm().generateID();
    }

    /**
     * @return an outputtable string for file IO
     */
    @Override
    public String toOutputString() {
        return getFirstName() + "," + getMiddleName() + "," + getLastName()
                + "," + getEmail() + "," + getPhone() + "," + getAddress()
                + "," + getCity() + "," + getState() + "," + getZip() + "," + getHireDate()
                + "," + isTenured() + "," + getID() + ",";
    }

    /**
     * @return a string representing the data from the object
     */
    public String toString(){
        return super.toString() + "Hired: " + hireDate +
                ( tenured.equals("true") ? " Tenured\n" : " Not Tenured\n" );
    }

    /**
     * @return a string containing the name and id of the faculty member
     */
    public String toShortString(){
        return getFirstName() + " " + getLastName() +
                "\nFaculty ID: " + getID() + "\n";
    }
}
