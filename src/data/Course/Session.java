package data.Course;

import app.Algorithms.SessionIDGenerator;

public class Session extends Course {
    private String id;

    public Session(String department, String code, String description, int minStudentCount, int maxStudentCount){
        super(department, code, description, minStudentCount, maxStudentCount);
    }

    /**
     * Accessor Method
     * @return id
     */
    public String getID() { return id; }

    /**
     * Overridden Mutator Method using a session specific algorithm to generate a unique ID
     * @see SessionIDGenerator
     */
    public void setID() {
        id = new SessionIDGenerator().generateID();
    }
}
