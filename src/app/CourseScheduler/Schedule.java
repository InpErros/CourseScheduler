package app.CourseScheduler;

import app.Algorithms.*;
import data.Course.*;
import data.Person.*;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

/**
 * {@code Schedule} defines a schedule and acts as an output for schedule data
 * @see Scheduler
 */
public class Schedule {
    private IDgenerator generatorAlgorithm;
    private Scheduler schedulerAlgorithm;

    private ArrayList<Session> scheduledCourseSessions;
    private ArrayList<Course> unscheduledCourses;
    private ArrayList<Instructor> faculty;
    private ArrayList<ScheduledStudent> scheduledStudents;
    private ArrayList<Student> unscheduledStudents;

    public Schedule() {}

    /**
     * initializes ArrayLists and creates a schedule using the given scheduling algorithm
     * @param generatorAlgorithm the generating algorithm for session ids
     * @param schedulerAlgorithm the scheduling algorithm
     */
    public Schedule(IDgenerator generatorAlgorithm, Scheduler schedulerAlgorithm){
        this.generatorAlgorithm = generatorAlgorithm;
        this.schedulerAlgorithm = schedulerAlgorithm;

        scheduledCourseSessions = new ArrayList<>();
        unscheduledCourses = new ArrayList<>();
        faculty = new ArrayList<>();
        scheduledStudents =  new ArrayList<>();
        unscheduledStudents = new ArrayList<>();

        this.schedulerAlgorithm.schedule(this);
    }

    /**
     * Accessor Method
     * @return
     */
    public ArrayList<Session> getScheduledCourseSessions() { return scheduledCourseSessions; }

    /**
     * Accessor Method
     * @return
     */
    public ArrayList<Course> getUnscheduledCourses() { return unscheduledCourses; }

    /**
     * Accessor Method
     * @return
     */
    public ArrayList<Instructor> getFaculty() { return faculty; }

    /**
     * Accessor Method
     * @return reference to the ScheduledStudents ArrayList
     */
    public ArrayList<ScheduledStudent> getScheduledStudents() { return scheduledStudents; }

    /**
     * Accessor Method
     * @return reference to the UnscheduledStudents ArrayList
     */
    public ArrayList<Student> getUnscheduledStudents() { return unscheduledStudents; }

    /**
     * Accessor Method
     * @return reference to the id generator algorithm
     */
    public IDgenerator getGeneratorAlgorithm() { return generatorAlgorithm; }

    /**
     * Outputs Schedule data to file in the Output directory
     */
    public void output(){
        String directory = "\\src\\data\\Output\\";
        try{
            printToFile(directory + "ScheduledCourseSessions.txt", scheduledCourseSessionsToString());
            printToFile(directory + "UnscheduledCourseSessions.txt", unScheduledCourseSessionsToString());
            printToFile(directory + "Faculty.txt", facultyToString());
            printToFile(directory + "ScheduledStudents.txt", scheduledStudentsToString());
            printToFile(directory + "UnscheduledStudents.txt", unScheduledStudentsToString());
        }
        catch(IOException ex){
            System.out.println("ONE OR MORE FILES COULD NOT BE PRINTED\n" + ex.getMessage());
        }

    }

    /**
     * Turns the Scheduled Course Sessions ArrayList into and outputable string
     * @return a string
     */
    public String scheduledCourseSessionsToString(){
        StringBuilder str = new StringBuilder();
        for(Session s: scheduledCourseSessions){
            str.append(s.toLongString()).append("\n");
        }
        return str.toString();
    }

    /**
     * Turns the Unscheduled Course Sessions ArrayList into and outputable string
     * @return the string
     */
    public String unScheduledCourseSessionsToString(){
        StringBuilder str = new StringBuilder();
        for(Course c: unscheduledCourses){
            str.append(c.toString()).append("\n");
        }
        return str.toString();
    }

    /**
     * Turns the Faculty ArrayList into and outputable string
     * @return the string
     */
    public String facultyToString(){
        StringBuilder str = new StringBuilder();
        for(Instructor i: faculty){
            str.append(i.toString()).append("\n");
        }
        return str.toString();
    }

    /**
     * Turns the Scheduled Students ArrayList into and outputable string
     * @return the string
     */
    public String scheduledStudentsToString(){
        StringBuilder str = new StringBuilder();
        for(ScheduledStudent ss: scheduledStudents){
            str.append(ss.toString()).append("\n");
        }
        return str.toString();
    }

    /**
     * Turns the Unscheduled StudentsArrayList into and outputable string
     * @return the string
     */
    public String unScheduledStudentsToString(){
        StringBuilder str = new StringBuilder();
        for(Student s: unscheduledStudents){
            str.append(s.toString()).append("\n");
        }
        return str.toString();
    }

    /**
     * Prints a string to a file
     * @param path the file path
     * @param str the string to be output
     * @throws IOException if there is a problem writing to the file
     */
    public void printToFile(String path, String str) throws IOException {
        BufferedWriter output = Files.newBufferedWriter(Paths.get(path));
        output.write(str);
        output.close();
    }
}
