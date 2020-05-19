package data.Exception;

/**
 * {@code StudentNotFoundException} defines an Exception that
 * is used when a Student cannot be found in the {@code StudentDatabase}
 * @see NotFoundException
 * @see data.Database.StudentDatabase
 * @author Lucas Demchik
 * @version 0.1
 */
public class StudentNotFoundException extends NotFoundException {
    public StudentNotFoundException(String s){
        super(s);
    }
}
