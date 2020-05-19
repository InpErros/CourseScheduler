package data.Person;

public class Faculty extends Person{
    private String hireDate;
    private String tenured;

    public Faculty(String firstName, String middleName, String lastName,
                   String email, String phone, String address, String city,
                   String state, String zip, String hireDate, String tenured){

        super(firstName, middleName, lastName, email, phone, address, city, state, zip);
        this.hireDate = hireDate;
        this.tenured = tenured;
    }

    public Faculty(String firstName, String middleName, String lastName,
                   String email, String phone, String address, String city,
                   String state, String zip, String hireDate, String tenured,
                   String id){

        super(firstName, middleName, lastName, email, phone, address, city, state, zip);
        this.hireDate = hireDate;
        this.tenured = tenured;
        this.id = id;
    }

    public Faculty() { }

    public String getHireDate() { return hireDate; }
    public void setHireDate(String hireDate) { this.hireDate = hireDate; }
    public String isTenured() { return tenured; }
    public void setTenured(String tenured) { this.tenured = tenured; }

    /**
     * Overridden method using algorithm selected at runtime to generate the ID
     * @see app.Algorithms.IDgenerator
     */
    @Override
    public void makeID() {
        id = "f" + getGeneratorAlgorithm().generateID();
    }

    @Override
    public String toOutputString() {
        return getFirstName() + "," + getMiddleName() + "," + getLastName()
                + "," + getEmail() + "," + getPhone() + "," + getAddress()
                + "," + getCity() + "," + getState() + "," + getZip() + "," + getHireDate()
                + "," + isTenured() + "," + getID() + ",";
    }
}
