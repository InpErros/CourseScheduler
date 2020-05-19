package data.Course;

import data.Exception.NotFoundException;
import data.Person.Student;
import data.Database.*;

import java.util.ArrayList;

/**
 * {@code RegistrationRequest} defines a request from a student to register for classes
 * @see SessionWishlist
 * @author Lucas Demchik
 * @version 0.1
 */
public class RegistrationRequest {
    private Student student;
    private ArrayList<Course> courses;
    private StudentDatabase sdb = StudentDatabase.getInstance();
    private CourseDatabase cdb = CourseDatabase.getInstance();

    /**
     * Initializes a registration request given data read in from a file
     * that is used to find the objects in the appropriate databases
     * @param arr String array read from a file
     * @throws NotFoundException if object is not found in its database
     * @see CourseDatabase
     * @see StudentDatabase
     */
    public RegistrationRequest(String[] arr) throws NotFoundException{
        this.student = sdb.findStudent(arr[0]);
        courses = new ArrayList<>();
        for(int i = 1; i < arr.length; ++i){
            courses.add(cdb.findCourse(arr[i]));
        }
    }


    /**
     * @return a reference to the student
     */
    public Student getStudent() { return student; }

    /**
     * @return a reference to the course list
     */
    public ArrayList<Course> getCourses() { return courses; }
}
