package data.Database;

import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;

import data.Course.*;
import data.Exception.CourseNotFoundException;

/**
 * {@code CourseDatabase} defines a static database that reads in and holds all information about the courses
 * @see Database
 * @see Course
 * @author Lucas Demchik
 * @version 0.1
 */
public class CourseDatabase implements Database {
    private final static CourseDatabase cdb = new CourseDatabase();
    private ArrayList<Course> db;

    /**
     * Initializes the database from a file
     */
    private CourseDatabase(){
        db = new ArrayList<>();
        try{
            loadDatabase();
        }
        catch(FileNotFoundException ex) {
            System.out.println("COURSE FILE NOT FOUND");
        }
    }

    /**
     * @return a reference to the CourseDatabase instance
     */
    public static CourseDatabase getInstance() { return cdb; }

    /**
     * @return a reference to the ArrayList
     */
    public ArrayList<Course> getDatabase() { return db; }

    /**
     * Loads database with {@code Courses} by reading from an input file
     * @throws FileNotFoundException if the file cannot be found
     * @see Course
     */
    private void loadDatabase() throws FileNotFoundException {
        File file = new File("src\\data\\Input\\course.txt");
        Scanner input = new Scanner(file);

        String[] s;
        while(input.hasNextLine()){
            s = input.nextLine().split(",");
            db.add(new Course(s[0], s[1], s[2], Integer.parseInt(s[3]),  Integer.parseInt(s[4])));
        }
        input.close();
    }

    /**
     * @param id a Course ID made of its Department and Code
     * @return a reference to the Course in the database
     * @throws CourseNotFoundException if the Course cannot be found
     */
    public Course findCourse(String id) throws CourseNotFoundException{
        String department = id.substring(0,id.length()-2);
        String code = id.substring(id.length()-2);
        Course result = new Course();
        boolean found = false;

        for(Course c: db){
            if(c.getDepartment().equals(department)){
                if(c.getCode().equals(code)){
                    result = c;
                    found = true;
                }
            }
        }
        if(!found){
            throw new CourseNotFoundException("Course Not Found");
        }
        return result;
    }

}
