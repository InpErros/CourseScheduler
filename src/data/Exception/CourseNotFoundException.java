package data.Exception;

import data.Exception.NotFoundException;

public class CourseNotFoundException extends NotFoundException {
    public CourseNotFoundException(String s){
        super(s);
    }
}
