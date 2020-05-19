package app.Algorithms;

import app.CourseScheduler.Schedule;

/**
 * {@code Scheduler} is an interface designed to allow swapping of scheduling algorithms.
 * Require implementation of {@code schedule()} method
 * @see FirstComeFirstServeScheduler
 * @author Lucas Demchik
 * @version 0.1
 */
public interface Scheduler {
    /**
     * Method to be implemented by different scheduling algorithms
     * @param schedule the schedule object
     */
    void schedule(Schedule schedule);
}
