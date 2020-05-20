package data.Person;

import app.Algorithms.IDgenerator;

/**
 * {@code Person} and its subclasses define members of a school
 * @see Student
 * @see Faculty
 * @author Lucas Demchik
 * @version 0.1
 */
public abstract class Person {
    private String firstName;
    private String lastName;
    private String middleName;
    private String email;
    private String phone;
    private String address;
    private String city;
    private String state;
    private String zip;
    protected String id;
    private IDgenerator generatorAlgorithm;

    public Person(){}


    /**
     * {@code Constructor} that initializes a person object with given data
     * @param firstName
     * @param middleName
     * @param lastName
     * @param email
     * @param phone
     * @param address
     * @param city
     * @param state
     * @param zip
     */
    public Person(String firstName, String middleName, String lastName,
                  String email, String phone, String address, String city,
                  String state, String zip){

        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.id = " ";
    }

    /**
     * {@code Constructor} that initializes a person object with given data
     * @param firstName
     * @param middleName
     * @param lastName
     * @param email
     * @param phone
     * @param address
     * @param city
     * @param state
     * @param zip
     */
    public Person(String firstName, String middleName, String lastName,
                  String email, String phone, String address, String city,
                  String state, String zip, String id){

        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.id = id;
    }

    /**
     * Initializes a new person as a copy of another
     * @param p object to be copied
     */
    public Person(Person p){
        this(p.firstName,p.middleName,p.lastName,p.email,p.phone,p.address,p.city,p.state,p.zip,p.id);
    }

    /**
     * Mutator method
     * @param firstName
     */
    public void setFirstName(String firstName) { this.firstName = firstName; }

    /**
     * Mutator method
     * @param middleName
     */
    public void setMiddleName(String middleName) { this.middleName = middleName; }

    /**
     * Mutator method
     * @param lastName
     */
    public void setLastName(String lastName) { this.lastName = lastName; }

    /**
     * Mutator method
     * @param email
     */
    public void setEmail(String email) { this.email = email; }

    /**
     * Mutator method
     * @param phone
     */
    public void setPhone(String phone) { this.phone = phone; }

    /**
     * Mutator method
     * @param address
     */
    public void setAddress(String address) { this.address = address; }

    /**
     * Mutator method
     * @param city
     */
    public void setCity(String city) { this.city = city; }

    /**
     * Mutator method
     * @param state
     */
    public void setState(String state) { this.state = state; }

    /**
     * Mutator method
     * @param zip
     */
    public void setZip(String zip) { this.zip = zip; }

    /**
     * Accessor method
     * @return firstName
     */
    public String getFirstName() { return firstName; }

    /**
     * Accessor method
     * @return middleName
     */
    public String getMiddleName() { return middleName; }

    /**
     * Accessor method
     * @return lastName
     */
    public String getLastName() { return lastName; }

    /**
     * Accessor method
     * @return email
     */
    public String getEmail() { return email; }

    /**
     * Accessor method
     * @return phone
     */
    public String getPhone() { return phone; }

    /**
     * Accessor method
     * @return address
     */
    public String getAddress() { return address; }

    /**
     * Accessor method
     * @return city
     */
    public String getCity() { return city; }

    /**
     * Accessor method
     * @return state
     */
    public String getState() { return state; }

    /**
     * Accessor method
     * @return zip
     */
    public String getZip() { return zip; }

    /**
     * Abstract Mutator for id
     * to be implemented in subclasses of {@link Person}
     * @see Student
     * @see Faculty
     */
    public abstract void makeID();

    /**
     * Accessor Method
     * @return id
     */
    public String getID() { return id; }

    /**
     * Accessor Method
     * @return the id generator algorithm
     */
    public IDgenerator getGeneratorAlgorithm() { return generatorAlgorithm; }

    /**
     * Mutator Method
     * @param generatorAlgorithm the id generator algorithm
     */
    public void setGeneratorAlgorithm(IDgenerator generatorAlgorithm) { this.generatorAlgorithm = generatorAlgorithm; }

    /**
     * Abstract method to be overloaded by subclasses
     * @return an output string
     */
    public abstract String toOutputString();

    /**
     * @return a string representation of the object
     */
    @Override
    public String toString() {
        return firstName + " " + middleName + " " + lastName + "\n"
                + "Email: " + email + "\nPhone#: " + phone + "\n"
                + address + " " + city + ", " + state + " "  + zip
                + "\n";
    }
}
