package data.Course;

import data.Exception.NotFoundException;
import data.Person.Student;
import data.Database.*;

import java.util.ArrayList;

public class RegistrationRequest {
    private Student student;
    private ArrayList<Course> courses;
    private StudentDatabase sdb = StudentDatabase.getInstance();
    private CourseDatabase cdb = CourseDatabase.getInstance();

    public RegistrationRequest(String[] arr) throws NotFoundException{
        this.student = sdb.findStudent(arr[0]);
        for(int i = 1; i < arr.length; ++i){
            courses.add(cdb.findCourse(arr[i]));
        }
    }


    public Student getStudent() { return student; }

    public ArrayList<Course> getCourses() { return courses; }
}
