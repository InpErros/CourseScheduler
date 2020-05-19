package app.CourseScheduler;

import app.Algorithms.BasicRngId;
import data.Database.*;
import data.Course.*;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        CourseDatabase cdb = CourseDatabase.getInstance();
        cdb.findCourse("CS1A");
        cdb.findCourse("BIO2B");

    }
}
