package data.Database;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.ArrayList;

import app.Algorithms.IDgenerator;
import data.Exception.StudentNotFoundException;
import data.Person.*;

public class StudentDatabase implements Database{
    private final static StudentDatabase sdb = new StudentDatabase();
    private ArrayList<Student> db;

    private StudentDatabase(){
        db = new ArrayList<>();
        try{
            loadDatabase();
        }
        catch(FileNotFoundException ex) {
            System.out.println("STUDENT FILE NOT FOUND");
        }
        catch(NumberFormatException ex){
            System.out.println(ex.getMessage());
        }
    }

    public static StudentDatabase getInstance(){ return sdb; }

    public ArrayList<Student> getDatabase() { return db; }

    private void loadDatabase() throws FileNotFoundException, NumberFormatException {

        File file = new File("src\\data\\Input\\student.txt");
        Scanner input = new Scanner(file);

        String[] s;
        while(input.hasNextLine()){
            s = input.nextLine().split(",");
            if(!(s[12].equals(" "))){
                db.add(new Student(s[0], s[1], s[2], s[3], s[4], s[5], s[6], s[7], s[8], s[9], s[10], s[11], s[12]));
            }
            else{
                db.add(new Student(s[0], s[1], s[2], s[3], s[4], s[5], s[6], s[7], s[8], s[9], s[10], s[11]));
            }
        }
        input.close();
    }

    public void checkIds(IDgenerator generatorAlgorithm){
        for(Student s: db){ // For Each Student in the Database
            if(s.getID().equals(" ")){ // If the ID has not been made yet
                s.setGeneratorAlgorithm(generatorAlgorithm);
                s.makeID();
            }
        }
    }

    public void close() throws  IOException {
        BufferedWriter output = Files.newBufferedWriter(Paths.get("src\\data\\Input\\student.txt"));
        for(Student s: db){
            output.write(s.toOutputString() + "\n");
        }
        output.close();
    }

    public Student findStudent(String id) throws StudentNotFoundException{
        Student result = new Student();
        boolean found = false;

        for(Student s: db){
            if(s.getID().equals(id)){
                result = s;
                found = true;
            }
        }

        if(!found){
            throw new StudentNotFoundException("STUDENT WAS NOT FOUND");
        }
        return result;
    }
}
