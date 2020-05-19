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
