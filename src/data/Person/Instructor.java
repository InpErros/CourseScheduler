package data.Person;

import data.Course.Course;

import java.util.ArrayList;

/**
 * {@code Instructor} defines a Faculty member who is assigned to teach Courses
 * @see Faculty
 * @see Course
 * @author Lucas Demchik
 * @version 0.1
 */
public class Instructor extends Faculty{
    private ArrayList<Course> classes;

    /**
     * Initializes an Instructor as a Copy of a given Faculty object
     * @param f Faculty object to be copied
     */
    public Instructor(Faculty f){
        super(f);
        classes = new ArrayList<>();
    }

    /**
     * Initializes a new Instructor made of blank strings
     */
    public Instructor(){
        super();
    }

    /**
     * @return a reference to the classes ArrayList
     */
    public ArrayList<Course> getClasses() { return classes; }

    /**
     * @return a string that represents the data of the object
     */
    public String toString(){
        String s = super.toString();
        for(Course c: classes){
            s += "\n" + c.toString() + "\n";
        }
        return s;
    }
}
