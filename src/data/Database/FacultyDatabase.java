package data.Database;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.ArrayList;
import app.Algorithms.IDgenerator;
import data.Person.*;

/**
 * {@code FacultyDatabase} defines a static database that reads, writes, and holds all information for Faculty
 * @see Database
 * @see Faculty
 * @author Lucas Demchik
 * @version 0.1
 */
public class FacultyDatabase implements Database{
    private final static FacultyDatabase fdb = new FacultyDatabase();
    private ArrayList<Faculty> db;

    /**
     * Initializes the database from a file
     */
    private FacultyDatabase(){
        db = new ArrayList<Faculty>();
        try{
            loadDatabase();
        }
        catch(FileNotFoundException ex){
            System.out.println("FACULTY FILE NOT FOUND");        }
    }

    /**
     * @return a reference to the FacultyDatabase instance
     */
    public static FacultyDatabase getInstance() { return fdb; }

    /**
     * @return a reference to the ArrayList
     */
    public ArrayList<Faculty> getDatabase(){ return db; }

    /**
     * Loads database with {@code Faculty} by reading from an input file
     * @throws FileNotFoundException if the file cannot be found
     * @see Faculty
     */
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

    /**
     * Checks IDs of each Faculty member in the database and generates them if one has not been generated.
     * @param generatorAlgorithm the id generation algorithm
     * @see IDgenerator
     */
    public void checkIds(IDgenerator generatorAlgorithm){
        for(Faculty f: db){
            if(f.getID().equals(" ")){
                f.setGeneratorAlgorithm(generatorAlgorithm);
                f.makeID();
            }
        }
    }

    /**
     * Overwrites the database to the input file to update with any changes made during runtime
     * Should be called at the end of the program
     * @throws IOException if file fails to be written
     */
    public void close() throws IOException {
        BufferedWriter output = Files.newBufferedWriter(Paths.get("src\\data\\Input\\faculty.txt"));
        for(Faculty f: db){
            output.write(f.toOutputString() + "\n");
        }
        output.close();
    }
}
