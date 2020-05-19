package data.Exception;

import data.Exception.NotFoundException;

public class StudentNotFoundException extends NotFoundException {
    public StudentNotFoundException(String s){
        super(s);
    }
}
