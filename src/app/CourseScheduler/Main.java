package app.CourseScheduler;

import app.Algorithms.*;
import data.Database.*;
import data.Person.Student;

import java.io.IOException;
import java.util.Scanner;


public class Main {



    public static void main(String[] args) {
        StudentDatabase sdb = StudentDatabase.getInstance();
        CourseDatabase cdb = CourseDatabase.getInstance();
        Configuration config = Configuration.getInstance();
        FacultyDatabase fdb = FacultyDatabase.getInstance();
        SessionWishlist swl = SessionWishlist.getInstance();
        Scanner input = new Scanner(System.in);

        try {
            Schedule schedule = new Schedule(new BasicRngId(), new FirstComeFirstServeScheduler());
        }
        catch(NullPointerException ex){
            System.out.println(ex.getMessage());
        }
        boolean validInput = false;
        int choice = 0;
        boolean cont = true;

        while (cont) {
            choice = 0;
            validInput = false;
            while (!validInput) {
                try {
                    displayMenu();
                    choice = input.nextInt();
                    if ((choice > 0) && (choice < 8)) {
                        validInput = true;
                    }
                } catch (NumberFormatException ex) {
                    System.out.println("YOU DID NOT ENTER AN INTEGER");
                }
            }
            try {
                switch (choice) {
                    case 1:
                        displayDatabaseStats(sdb, fdb, cdb);
                        break;
                    case 2:
                        addStudent(sdb);
                        break;
                    case 3:
                        addFaculty(fdb);
                        break;
                    case 4:
                        addCourse(cdb);
                        break;
                    case 5:
                        System.out.println("this button does nothing :)");
                        break;
                    case 6:
                        //displayScheduleStats(schedule);
                    case 7:
                        //schedule.output();
                        cont = false;
                        break;
                }
            }
            catch(Exception ex){
                System.out.println(ex.getMessage());
            }
            input.nextLine();
        }

        System.out.println("Exited WHILE loop");


        try {
            sdb.close();
            fdb.close();
        }
        catch(IOException ex){
            System.out.println(ex.getMessage());
        }
    }

    public static void displayMenu(){
        System.out.println("\n------------------------------");
        System.out.println("   Welcome to the Scheduler!");
        System.out.println("------------------------------");
        System.out.println("   Please Select an Option: ");
        System.out.println(" 1) View Database Statistics");
        System.out.println(" 2) Add a Student");
        System.out.println(" 3) Add a Faculty");
        System.out.println(" 4) Add a Course");
        System.out.println(" 5) Generate Schedule");
        System.out.println(" 6) View Schedule Statistics");
        System.out.println(" 7) Print Schedule and Exit");
        System.out.println("------------------------------");
        System.out.println(" Enter an Integer (1-7): ");
    }

    public static void displayDatabaseStats(StudentDatabase sdb, FacultyDatabase fdb, CourseDatabase cdb){
        System.out.println("------------------------------");
        System.out.println("      DATABASE STATS:");
        System.out.println(" Number of Students: " + sdb.getDatabase().size());
        System.out.println(" Number of Faculty : " + fdb.getDatabase().size());
        System.out.println(" Number of Courses : " + cdb.getDatabase().size());
        System.out.println("------------------------------\n");
    }

    public static void addStudent(StudentDatabase sdb) throws  Exception{
        Scanner input = new Scanner(System.in);
        System.out.println("-----------------------------------------");
        System.out.println("Enter student details as displayed below");
        System.out.println("-----------------------------------------");
        System.out.println("firstname,middlename,lastname,email,phone#");
        String[] s = input.nextLine().split(",");
        System.out.println("123 address road,city,ST,zip,birthDate,todaysDate");
        String[] s2 = input.nextLine().split(",");

        for(String str: s){
            if(str.length() < 1){
                throw new Exception("ERROR READING FROM CONSOLE");
            }
        }
        sdb.getDatabase().add(new Student(s[0],s[1],s[2],s[3],s[4],s2[0],s2[1],s2[2],s2[3],s2[4],"4.0",s2[5]));
        sdb.getDatabase().get(sdb.getDatabase().size()-1).makeID();

        System.out.println(" STUDENT HAS BEEN REGISTERED! \n");
    }

    public static void addFaculty(FacultyDatabase fdb){
        System.out.println("NOT YET IMPLEMENTED\n");
    }

    public static void addCourse(CourseDatabase cdb){
        System.out.println("NOT YET IMPLEMENTED\n");
    }

    public static void displayScheduleStats(Schedule schedule){
        System.out.println("------------------------------");
        System.out.println("      SCHEDULE STATS:");
        System.out.println(" Sessions Scheduled     : " + schedule.getScheduledStudents().size());
        System.out.println(" Courses Not Scheduled  : " + schedule.getUnscheduledCourses().size());
        System.out.println(" Students Not Scheduled : " + schedule.getUnscheduledStudents().size());
        System.out.println("------------------------------\n");
    }




}
