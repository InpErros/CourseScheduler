package data.Course;

import app.Algorithms.IDgenerator;
import data.Person.Faculty;
import data.Person.Instructor;
import data.Person.Student;

import java.util.ArrayList;

/**
 * {@code Session} defines a session of a course
 * @see Course
 * @author Lucas Demchik
 * @version 0.1
 */
public class Session extends Course {
    private String id;
    private IDgenerator generatorAlgorithm;
    private ArrayList<Student> students;
    private Faculty instructor;

    /**
     * Initializes object with given data
     * @param department
     * @param code
     * @param description
     * @param minStudentCount
     * @param maxStudentCount
     */
    public Session(String department, String code, String description, int minStudentCount, int maxStudentCount){
        super(department, code, description, minStudentCount, maxStudentCount);
    }

    /**
     * Initializes a new Session as a copy of a Course.
     * @param c Course to be copied
     * @param generatorAlgorithm id generation algorithm
     */
    public Session(Course c, IDgenerator generatorAlgorithm){
        super(c);
        this.generatorAlgorithm = generatorAlgorithm;
        makeID();
    }

    /**
     * Initializes a new Session as a copy of a given Session
     * @param s Session to be copied
     */
    public Session(Session s){
        this(s.getDepartment(),s.getCode(),s.getDescription(),s.getMinStudentCount(),s.getMaxStudentCount(),s.id,s.generatorAlgorithm, s.instructor);
        this.students = new ArrayList<>();
        for(Student student: s.students){
            this.students.add(new Student(student));
        }
    }

    public Session(String department, String code, String description, int minStudentCount, int maxStudentCount, String id, IDgenerator generatorAlgorithm, Faculty instructor){
        this.setDepartment(department);
        this.setCode(code);
        this.setDescription(description);
        this.setMinStudentCount(minStudentCount);
        this.setMaxStudentCount(maxStudentCount);
        this.id = id;
        this.generatorAlgorithm = generatorAlgorithm;
        this.instructor = new Faculty(instructor);
    }

    /**
     * Initializes a Session with Blank Strings
     */
    public Session(){
        super();
    }


    /**
     * Accessor Method
     * @return reference to the id
     */
    public String getID() { return id; }

    /**
     * Overridden Method using a algorithm selected at runtime to generate an ID
     * @see app.Algorithms.IDgenerator
     */
    public void makeID() {
        id = "c" + generatorAlgorithm.generateID();
    }

    /**
     * Accessor Method
     * @return a reference to the students ArrayList
     */
    public ArrayList<Student> getStudents() { return students; }

    /**
     * Accessor method
     * @return a reference to the instructor
     */
    public Faculty getInstructor() { return instructor; }

    /**
     * Mutator method
     * @param instructor the instructor assigned to the session
     */
    public void setInstructor(Faculty instructor) { this.instructor = instructor; }

    /**
     * Transforms the data held in the object to a string format to be outputted
     * @return a string
     */
    public String toLongString(){
        StringBuilder str = new StringBuilder(super.toString() + "Session ID: " + id
                + instructor.toShortString()
                + "Student Count: " + students.size()
                + "\n");
        for(Student s: students){
            str.append(s.toShortString());
        }
        return str.toString();
    }
    public void addStudent(Student s){
        students.add(s);
    }
}
