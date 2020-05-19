package data.Exception;

/**
 * {@code NotFoundException} defines a custom Exception for error
 * checking when searching through databases
 * @see data.Database.Database
 * @see SessionNotFoundException
 * @see StudentNotFoundException
 * @see CourseNotFoundException
 * @author Lucas Demchik
 * @version 0.1
 */
public class NotFoundException extends Exception {
    public NotFoundException(String s){
        super(s);
    }
}
