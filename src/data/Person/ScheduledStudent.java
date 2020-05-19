package data.Person;

import data.Course.Session;

import java.util.ArrayList;

/**
 * {@code ScheduledStudent} defines a Student schedules do take certain courses
 * @see Student
 * @see Session
 * @author Lucas Demchik
 * @version 0.1
 */
public class ScheduledStudent extends Student{
    private ArrayList<Session> classes;

    /**
     * Initializes a ScheduledStudent as a copy of a given Student
     * @param s Student object to be copied
     */
    public ScheduledStudent(Student s){
        super(s);
        classes = new ArrayList<>();
    }

    /**
     * Initializes a new ScheduledStudent made of blank strings
     */
    public ScheduledStudent(){
        super();
    }

    /**
     * @return a reference to the classes ArrayList
     */
    public ArrayList<Session> getClasses() { return classes; }

    /**
     * @return a string that represents the data of the object
     */
    public String toString(){
        StringBuilder str = new StringBuilder(super.toString());
        for(Session s: classes){
            str.append("\n").append(s.toString()).append("Session ID").append(s.getID()).append("\n").append("\n");
        }
        return str.toString();
    }
}
