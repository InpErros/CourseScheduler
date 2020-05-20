package app.Algorithms;

import app.CourseScheduler.Schedule;
import data.Course.*;
import data.Database.*;
import data.Person.*;

import java.util.ArrayList;

/**
 * {@code FirstComeFirstServeScheduler} is an algorithm that schedules sessions in a first come first serve manner.
 * This algorithm can be swapped out at runtime due to its implementation of {@code Scheduler}
 * @see Scheduler
 * @author Lucas Demchik
 * @version 0.1
 */
public class FirstComeFirstServeScheduler implements Scheduler {
    private final SessionWishlist swl = SessionWishlist.getInstance();
    private ArrayList<Session> tempSessions;
    private ArrayList<ScheduledStudent> tempScheduledStudents;
    private ArrayList<Instructor> tempFaculty;
    private ArrayList<Course> tempUnSessions;
    private ArrayList<Student> tempUnStudents;

    /**
     * Initializes the ArrayLists
     */
    public FirstComeFirstServeScheduler(){
        tempSessions = new ArrayList<>();
        tempScheduledStudents = new ArrayList<>();
        tempFaculty = new ArrayList<>();
        tempUnSessions = new ArrayList<>();
        tempUnStudents = new ArrayList<>();
    }

    /**
     * Takes in a {@code Schedule} object and fills its ArrayLists with the corresponding data
     * @param schedule the Schedule object
     */
    @Override
    public void schedule(Schedule schedule) {
        Session currentSession;

        for(RegistrationRequest request: swl.getWishlist()){ // For Each Request
            for(Course requestedCourse: request.getCourses()){ // For Each Requested Course
                if(!hasNotFullSession(requestedCourse)){ // If there is a not full session for the given course
                    currentSession = makeSession(requestedCourse, schedule.getGeneratorAlgorithm()); // Make a session
                    addInstructor(currentSession); // and assign it an instructor
                }
                else {
                    currentSession = findSession(requestedCourse); // Other wise find the NotFullSession
                }
                addStudent(currentSession, request.getStudent()); // Add the student to the session
            }
        } // Should have gone through all requests and created as many session as it could.

        parseSessions();
        parseInstructors();
        parseStudents();
        parseCourses();
        sortAndMoveScheduledSessions(schedule);
        sortAndMoveUnscheduledSessions(schedule);
        moveFaculty(schedule);
        moveScheduledStudents(schedule);
        moveUnscheduledStudents(schedule);

    }

    /**
     * checks if the requestedCourse has an available session to assign a student too
     * @param requestedCourse the course requested to have a session
     */
    public boolean hasNotFullSession(Course requestedCourse){
        boolean found = false;
        for(Session session: tempSessions){
            if(session.getDepartment().equals(requestedCourse.getDepartment())){
                if(session.getCode().equals(requestedCourse.getCode())){
                    if(!(session.getStudents().size() >= session.getMaxStudentCount())){
                        found = true;
                    }
                }
            }
        }
        return found;
    }

    /**
     * @param requestedCourse the course requested to have a session
     * @param generatorAlgorithm the algorithm for generating the sessions id
     * @return a Session of the requestedCourse with an ID
     */
    public Session makeSession(Course requestedCourse, IDgenerator generatorAlgorithm){
        tempSessions.add(new Session(requestedCourse, generatorAlgorithm));
        return tempSessions.get(tempSessions.size() - 1);
    }

    /**
     * @param requestedCourse the course requested to have a session
     * @return returns a "Not Full" session to assign a student too
     */
    public Session findSession(Course requestedCourse){
        Session foundSession = new Session();
        for(Session session: tempSessions){ // For every temp session
            if(session.getDepartment().equals(requestedCourse.getDepartment())){ // Check department
                if(session.getCode().equals(requestedCourse.getCode())){ // Check code
                    if(!(session.getStudents().size() >= session.getMaxStudentCount())){ // If this course has a NotFullSession return the session
                        foundSession = new Session(session);
                    }
                }
            }
        }
        return foundSession;
    }

    /**
     * assigns a Faculty to the {@code currentSession} and adds the Corresponding {@code Course} to the Instructors Course List
     * @param currentSession the current session being filled
     */
    public void addInstructor(Session currentSession){
        Configuration config = Configuration.getInstance();
        FacultyDatabase fdb = FacultyDatabase.getInstance();
        Faculty assignedInstructor = new Faculty();
        if(tempFaculty.size() > 0){ // There are assigned instructors
            for(Instructor i: tempFaculty){ // Check each one
                if(i.getClasses().size() < config.getMaxCoursesPerInstructor()){ // if they can take on another course
                    i.getClasses().add(currentSession);
                    currentSession.setInstructor(i);
                }
                else{ // If they can't the session gets no instructor so it can later be canceled.
                    currentSession.setInstructor(new Instructor());
                }
            }
        }
        else{ // There are no Instructors assigned
            assignedInstructor = new Instructor(fdb.getDatabase().get(0)); // Grab the first Faculty
            tempFaculty.add(new Instructor(assignedInstructor)); // Add them to the instructor list
            tempFaculty.get(0).getClasses().add(currentSession); // Assign them the course
            currentSession.setInstructor(tempFaculty.get(0)); // Assign them to the session
        }


    }

    /**
     * adds a student to the {@code currentSession} and adds the corresponding {@code Course} to the Students Course List
     * @param currentSession the current session being filled
     * @param student the student being added to the session
     */
    public void addStudent(Session currentSession, Student student){
        Configuration config = Configuration.getInstance();

        if(tempScheduledStudents.size() > 0){ // If the list of scheduled students isn't empty
            for(ScheduledStudent ss: tempScheduledStudents){ // For every scheduled student
                if(student.getID().equals(ss.getID())) { // Find the matching ID if they are on the list
                    if (ss.getClasses().size() < config.getMaxSessionsPerStudent()) { // if they have space in course load then they can register
                        ss.getClasses().add(currentSession);
                        currentSession.getStudents().add(student);
                    }
                }
                else{ // If not on the list
                    tempScheduledStudents.add(new ScheduledStudent(student)); // Add the student to the list of scheduled students
                    tempScheduledStudents.get(tempScheduledStudents.size()-1).getClasses().add(currentSession); // add the current session to their course load
                }
            }
        }
        else{ // List is empty
            tempScheduledStudents.add(new ScheduledStudent(student)); // Add student to the list;
            tempScheduledStudents.get(0).getClasses().add(currentSession); // add the current session to their course load
            //currentSession.getStudents().add(new Student(student)); // Add the student to the session.
            currentSession.addStudent(student);
        }
    }

    /**
     * Removes Sessions which do not have enough students from the scheduled sessions list
     */
    public void parseSessions(){
        for(Session s: tempSessions){
            if(s.getStudents().size() < s.getMinStudentCount()){
                tempSessions.remove(s); // If it doesn't have enough students cut it.
                unAssignInstructor(s);
                unScheduleStudents(s);
            }
        }
    }

    /**
     * Removes Sessions which do not have an Instructor from the scheduled sessions list
     */
    public void parseInstructors(){
        for(Session s: tempSessions){
            if(s.getInstructor().getID().equals(" ")){
                tempSessions.remove(s);
                unScheduleStudents(s);
            }
        }
    }

    /**
     * Removes an Instructor from the {@code Session}
     * and removes the {@code Course} from the Instructors Course List if it was the only session of the given course they taught
     * @param session the session being unscheduled
     */
    public void unAssignInstructor(Session session){
        boolean remove = true;

        for(Instructor i: tempFaculty){ // Look through all instructors
            if(i.getID().equals(session.getInstructor().getID())){ // Find the assigned instructor on the list
                for(Session s: tempSessions){ // For all the sessions
                    if(s.getInstructor().getID().equals(i.getID())){ // If there is a second Session the instructor is assigned to
                        if(session.getDepartment().equals(s.getDepartment())){
                            if(session.getCode().equals(s.getCode())){ // If it is the same course
                                if(!session.getID().equals(s.getID())){
                                    if(session.getInstructor().getID().equals(s.getInstructor().getID())){ // and has the same instructor
                                        remove = false;
                                    }
                                }
                            }
                        }
                    }
                }
                if(remove){
                    tempFaculty.remove(i);
                }
            }
        }
    }

    /**
     * Removes Students from the {@code Session} and removes the {@code Session} from the Students course list
     * @param session the session being unscheduled
     */
    public void unScheduleStudents(Session session){
        for(Student s: session.getStudents()){ // for all the students assigned to the course
            for(ScheduledStudent st: tempScheduledStudents){
                if(s.getID().equals(st.getID())){ // Find them on the scheduled list
                    st.getClasses().remove(session); // Remove the class from their course load
                    if(st.getClasses().size() < 1){ // if they don't have anymore courses
                        tempScheduledStudents.remove(st); // take them off the scheduled list
                    }
                }
            }
        }
    }

    /**
     * Adds all students not taking courses to the unscheduled students list
     */
    public void parseStudents(){
        StudentDatabase sdb = StudentDatabase.getInstance();
        boolean found = false;

        for(Student s: sdb.getDatabase()){ // For every Student
            for(ScheduledStudent st: tempScheduledStudents) { // Look for them on the scheduled students list
                if(s.getID().equals(st.getID())){ // If student is on the list
                    found = true;
                    break;
                }
            }
            if(!found){
                tempUnStudents.add(s); // add them to the unscheduled list
            }
        }
    }

    /**
     * Adds all courses without any session to the unscheduled sessions list
     */
    public void parseCourses(){
        CourseDatabase cdb = CourseDatabase.getInstance();
        boolean found = false;
        for(Course c: cdb.getDatabase()){
            for(Session s: tempSessions){
                if( (c.getDepartment().equals(s.getDepartment())) && (c.getCode().equals(s.getCode())) ){ // If Course is Scheduled
                    found = true;
                    break;
                }
            }
            if(!found){
                tempUnSessions.add(c);
            }
        }
    }

    /**
     * Sorts the scheduled sessions in alphabetic order based of the course id and then moves them to the schedule object
     * @param schedule the schedule object
     */
    public void sortAndMoveScheduledSessions(Schedule schedule){
        tempSessions.sort(Session.courseComparator);
        for(Session s: tempSessions){
            schedule.getScheduledCourseSessions().add(s);
        }
    }

    /**
     * Sorts the unscheduled session in alphabetic order based of the course id and the moves them to the schedule object
     * @param schedule the schedule object
     */
    public void sortAndMoveUnscheduledSessions(Schedule schedule){
        tempUnSessions.sort(Course.courseComparator);
        for(Course c: tempUnSessions){
            schedule.getUnscheduledCourses().add(c);
        }
    }

    /**
     * Moves the faculty list to the schedule object
     * @param schedule the schedule object
     */
    public void moveFaculty(Schedule schedule){
        for(Instructor i: tempFaculty){
            schedule.getFaculty().add(i);
        }
    }

    /**
     * Moves the scheduled students list to the schedule object
     * @param schedule the schedule object
     */
    public void moveScheduledStudents(Schedule schedule){
        for(ScheduledStudent ss: tempScheduledStudents){
            schedule.getScheduledStudents().add(ss);
        }
    }

    /**
     * Moves the unscheduled students list to the schedule object
     * @param schedule the schedule object
     */
    public void moveUnscheduledStudents(Schedule schedule){
        for(Student s: tempUnStudents){
            schedule.getUnscheduledStudents().add(s);
        }
    }



}
