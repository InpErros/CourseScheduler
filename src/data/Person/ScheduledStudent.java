package data.Person;

import data.Course.Session;

import java.util.ArrayList;

public class ScheduledStudent {
    private ArrayList<Session> classes;

    public ScheduledStudent(Student s){
        // Shallow copy the student here
        classes = new ArrayList<>();
    }
}
