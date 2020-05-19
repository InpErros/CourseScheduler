package data.Person;

import data.Course.Session;

import java.util.ArrayList;

public class Instructor extends Faculty{
    private ArrayList<Session> classes;

    public Instructor(Faculty f){
        // Shallow Copy faculty here
        classes = new ArrayList<>();
    }
}
