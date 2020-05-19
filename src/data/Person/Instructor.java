package data.Person;

import data.Course.Course;

import java.util.ArrayList;

public class Instructor extends Faculty{
    private ArrayList<Course> classes;

    public Instructor(Faculty f){
        super(f);
        classes = new ArrayList<>();
    }

    public Instructor(){
        super();
    }

    public ArrayList<Course> getClasses() { return classes; }
}
