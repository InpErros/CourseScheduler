package data.Person;

import app.Algorithms.FacultyIDGenerator;

import java.util.Date;

public class Faculty extends Person{
    private Date hireDate;
    private boolean tenured;

    public Faculty(String firstName, String middleName, String lastName,
                   String email, String phone, String address, String city,
                   String state, String zip, Date hireDate, boolean tenured){

        super(firstName, middleName, lastName, email, phone, address, city, state, zip);
        this.hireDate = hireDate;
        this.tenured = tenured;
    }

    public Date getHireDate() { return hireDate; }
    public void setHireDate(Date hireDate) { this.hireDate = hireDate; }
    public boolean isTenured() { return tenured; }
    public void setTenured(boolean tenured) { this.tenured = tenured; }

    /**
     * Overridden Mutator method using a faculty specific algorithm to generate a unique ID
     * @see FacultyIDGenerator
     */
    @Override
    public void setID() {
        id = new FacultyIDGenerator().generateID();
    }
}
