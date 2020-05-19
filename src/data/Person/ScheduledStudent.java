package data.Person;

import data.Course.Session;

import java.util.ArrayList;

public class ScheduledStudent extends Student{
    private ArrayList<Session> classes;

    public ScheduledStudent(Student s){
        super(s);
        classes = new ArrayList<>();
    }

    public ScheduledStudent(){
        super();
    }

    public ArrayList<Session> getClasses() { return classes; }
}
