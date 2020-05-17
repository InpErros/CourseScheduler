package data.Database;

import java.util.ArrayList;
import data.Person.*;

public class StudentDatabase implements Database{
    private static StudentDatabase sdb = new StudentDatabase();
    private ArrayList<Student> db;

    private StudentDatabase(){
        db = new ArrayList<Student>();
        loadDatabase();
    }

    public static StudentDatabase getInstance(){ return sdb; }

    public ArrayList<Student> getDatabase() { return db; }

    private void loadDatabase(){
        
    }
}
