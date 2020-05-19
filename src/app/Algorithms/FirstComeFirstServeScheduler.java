package app.Algorithms;

import app.CourseScheduler.Schedule;
import data.Course.*;
import data.Database.*;
import data.Person.*;

import java.util.ArrayList;

public class FirstComeFirstServeScheduler implements Scheduler {
    private final SessionWishlist swl = SessionWishlist.getInstance();
    private ArrayList<Session> tempSessions;
    private ArrayList<ScheduledStudent> tempScheduledStudents;
    private ArrayList<Instructor> tempFaculty;
    private ArrayList<Course> tempUnSessions;
    private ArrayList<Student> tempUnStudents;

    public FirstComeFirstServeScheduler(){
        tempSessions = new ArrayList<>();
        tempScheduledStudents = new ArrayList<>();
        tempFaculty = new ArrayList<>();
        tempUnSessions = new ArrayList<>();
        tempUnStudents = new ArrayList<>();
    }

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
                    currentSession = findSession(requestedCourse, schedule.getGeneratorAlgorithm()); // Other wise find the NotFullSession
                }
                addStudent(currentSession, request.getStudent()); // Add the student to the session
            }
        } // Should have gone through all requests and created as many session as it could.

        parseSessions();
        parseInstructors();
        parseStudents();
        sortAndMoveScheduledSessions(schedule);
        sortAndMoveUnscheduledSessions(schedule);
        moveFaculty(schedule);
        moveScheduledStudents(schedule);
        moveUnscheduledStudents(schedule);
        // Check if any sessions don't have enough students. Move them to unscheduled. Unschedule the instructors and students.
        // Check if any session don't have an instructor, add them to the unscheduled list and remove them from students course loads.
        // Check if any students are not on the scheduled students list. add them to the unscheduled list
        // Sort ScheduledSessions list by course and move it to the Schedule
        // Sort Unscheduled Sessions list by course and move it to the schedule
        // Move Faculty list to schedule
        // Move scheduled student list to schedule
        // move unscheduled student list to schedule
    }

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

    public Session makeSession(Course requestedCourse, IDgenerator generatorAlgorithm){
        tempSessions.add(new Session(requestedCourse, generatorAlgorithm));
        return tempSessions.get(tempSessions.size() - 1);
    }

    public Session findSession(Course requestedCourse, IDgenerator generatorAlgorithm){
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
            currentSession.getStudents().add(student); // Add the student to the session.
        }
    }

    public void parseSessions(){
        for(Session s: tempSessions){
            if(s.getStudents().size() < s.getMinStudentCount()){
                tempSessions.remove(s); // If it doesn't have enough students cut it.
                unAssignInstructor(s);
                unScheduleStudents(s);
            }
        }
    }

    public void parseInstructors(){
        for(Session s: tempSessions){
            if(s.getInstructor().getID().equals(" ")){
                tempSessions.remove(s);
                unScheduleStudents(s);
            }
        }
    }

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

    public void parseStudents(){
        StudentDatabase sdb = StudentDatabase.getInstance();
        for(Student s: sdb.getDatabase()){ // For every Student
            for(ScheduledStudent st: tempScheduledStudents) { // Look for them on the scheduled students list
                if(!s.getID().equals(st.getID())){ // If student is not on the list
                    tempUnStudents.add(s); // add them to the unscheduled list
                }
            }
        }
    }

    public void sortAndMoveScheduledSessions(Schedule schedule){
        tempSessions.sort(Session.courseComparator);
        for(Session s: tempSessions){
            schedule.getScheduledCourseSessions().add(s);
        }
    }

    public void sortAndMoveUnscheduledSessions(Schedule schedule){
        tempUnSessions.sort(Course.courseComparator);
        for(Course c: tempUnSessions){
            schedule.getUnscheduledCourses().add(c);
        }
    }

    public void moveFaculty(Schedule schedule){
        for(Instructor i: tempFaculty){
            schedule.getFaculty().add(i);
        }
    }

    public void moveScheduledStudents(Schedule schedule){
        for(ScheduledStudent ss: tempScheduledStudents){
            schedule.getScheduledStudents().add(ss);
        }
    }

    public void moveUnscheduledStudents(Schedule schedule){
        for(Student s: tempUnStudents){
            schedule.getUnscheduledStudents().add(s);
        }
    }

}
