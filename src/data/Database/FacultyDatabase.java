package data.Database;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.ArrayList;
import app.Algorithms.IDgenerator;
import data.Person.*;

public class FacultyDatabase implements Database{
    private final static FacultyDatabase fdb = new FacultyDatabase();
    private ArrayList<Faculty> db;

    private FacultyDatabase(){
        db = new ArrayList<Faculty>();
        try{
            loadDatabase();
        }
        catch(FileNotFoundException ex){
            System.out.println("FACULTY FILE NOT FOUND");        }
    }

    public static FacultyDatabase getInstance() { return fdb; }

    public ArrayList<Faculty> getDatabase(){ return db; }

    private void loadDatabase() throws FileNotFoundException {
        File file = new File("src\\data\\Input\\faculty.txt");
        Scanner input = new Scanner(file);

        String[] s;
        while(input.hasNextLine()){
            s = input.nextLine().split(",");
            if(!(s[11].equals(" "))){
                db.add(new Faculty(s[0], s[1], s[2], s[3], s[4], s[5], s[6], s[7], s[8], s[9], s[10], s[11]));
            }
            else{
                db.add(new Faculty(s[0], s[1], s[2], s[3], s[4], s[5], s[6], s[7], s[8], s[9], s[10]));
            }
        }
        input.close();
    }

    public void checkIds(IDgenerator generatorAlgorithm){
        for(Faculty f: db){
            if(f.getID().equals(" ")){
                f.setGeneratorAlgorithm(generatorAlgorithm);
                f.makeID();
            }
        }
    }

    public void close() throws IOException {
        BufferedWriter output = Files.newBufferedWriter(Paths.get("src\\data\\Input\\faculty.txt"));
        for(Faculty f: db){
            output.write(f.toOutputString() + "\n");
        }
        output.close();
    }
}
