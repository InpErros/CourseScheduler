package app.CourseScheduler;

import app.Algorithms.*;
import data.Course.*;
import data.Person.*;

import java.util.ArrayList;

public class Schedule {
    private IDgenerator generatorAlgorithm;
    private Scheduler schedulerAlgorithm;

    private ArrayList<Session> scheduledCourseSessions;
    private ArrayList<Course> unscheduledCourses;
    private ArrayList<Faculty> faculty;
    private ArrayList<Student> scheduledStudents;
    private ArrayList<Student> unscheduledStudents;

    public Schedule(IDgenerator generatorAlgorithm, Scheduler schedulerAlgorithm){
        this.generatorAlgorithm = generatorAlgorithm;
        this.schedulerAlgorithm = schedulerAlgorithm;
        schedulerAlgorithm.schedule(this);
    }

    public void setSchedulerAlgorithm(Scheduler schedulerAlgorithm) { this.schedulerAlgorithm = schedulerAlgorithm; }

    public void setGeneratorAlgorithm(IDgenerator generatorAlgorithm) { this.generatorAlgorithm = generatorAlgorithm; }

    public ArrayList<Session> getScheduledCourseSessions() { return scheduledCourseSessions; }

    public void setScheduledCourseSessions(ArrayList<Session> scheduledCourseSessions) { this.scheduledCourseSessions = scheduledCourseSessions; }

    public ArrayList<Course> getUnscheduledCourses() { return unscheduledCourses; }

    public void setUnscheduledCourses(ArrayList<Course> unscheduledCourses) { this.unscheduledCourses = unscheduledCourses; }

    public ArrayList<Faculty> getFaculty() { return faculty; }

    public void setFaculty(ArrayList<Faculty> faculty) { this.faculty = faculty; }

    public ArrayList<Student> getScheduledStudents() { return scheduledStudents; }

    public void setScheduledStudents(ArrayList<Student> scheduledStudents) { this.scheduledStudents = scheduledStudents; }

    public ArrayList<Student> getUnscheduledStudents() { return unscheduledStudents; }

    public void setUnscheduledStudents(ArrayList<Student> unscheduledStudents) { this.unscheduledStudents = unscheduledStudents; }

}
