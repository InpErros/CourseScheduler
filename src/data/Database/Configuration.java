package data.Database;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Configuration implements Database {
    private final static Configuration config = new Configuration();
    private int maxSessionsPerStudent;
    private int maxCoursesPerInstructor;

    private Configuration(){
        try{
            loadData();
        }
        catch(FileNotFoundException ex){
            System.out.println("CONFIG FILE NOT FOUND");
        }
    }

    public static Configuration getInstance() { return config; }
    public int getMaxSessionsPerStudent() { return maxSessionsPerStudent; }
    public int getMaxCoursesPerInstructor() { return maxCoursesPerInstructor; }

    private void loadData() throws FileNotFoundException {
        File file = new File("src\\data\\Input\\config.txt");
        Scanner input = new Scanner(file);

        maxSessionsPerStudent = input.nextInt();
        maxCoursesPerInstructor = input.nextInt();

        input.close();
    }
}
