package data.Database;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * {@code Configuration} defines a database that reads and holds the config data from a file
 * @see Database
 * @author Lucas Demchik
 * @version 0.1
 */
public class Configuration implements Database {
    private final static Configuration config = new Configuration();
    private int maxSessionsPerStudent;
    private int maxCoursesPerInstructor;

    /**
     * Initializes the config settings from a file
     */
    private Configuration(){
        try{
            loadData();
        }
        catch(FileNotFoundException ex){
            System.out.println("CONFIG FILE NOT FOUND");
        }
    }

    /**
     * @return a reference to the Configuration database instance
     */
    public static Configuration getInstance() { return config; }

    /**
     * @return a reference to the MaxSessionsPerStudent config setting
     */
    public int getMaxSessionsPerStudent() { return maxSessionsPerStudent; }

    /**
     * @return a reference to the MaxCoursesPerInstructor config setting
     */
    public int getMaxCoursesPerInstructor() { return maxCoursesPerInstructor; }

    /**
     * Loads Data from a file
     * @throws FileNotFoundException if the file cannot be found
     */
    private void loadData() throws FileNotFoundException {
        File file = new File("src\\data\\Input\\config.txt");
        Scanner input = new Scanner(file);

        maxSessionsPerStudent = input.nextInt();
        maxCoursesPerInstructor = input.nextInt();

        input.close();
    }
}
