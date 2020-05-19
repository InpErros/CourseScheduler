package app.Algorithms;

import app.CourseScheduler.Schedule;
import data.Course.RegistrationRequest;
import data.Database.SessionWishlist;

import java.util.ArrayList;

public class FirstComeFirstServeScheduler implements Scheduler {
    private final SessionWishlist swl = SessionWishlist.getInstance();

    @Override
    public void schedule(Schedule schedule) {
        for(RegistrationRequest rr: swl.getWishlist()){

        }
    }
}


/**
 * Schedule(){
 * Take first Request ->for loop
 * First Course Does it have a session ->for loop
 * hasSession() checks for session and creates one if there isn't one. ->assign Faculty -> add to Unscheduled if no faculty available
 * And/ Or add student to the session
 * Add Student to the scheduled list
 *
 */