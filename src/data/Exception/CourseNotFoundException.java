package data.Exception;

/**
 * {@code CourseNotFoundException} defines an Exception that
 * is used when a Course cannot be found in the {@code CourseDatabase}
 * @see NotFoundException
 * @see data.Database.CourseDatabase
 * @author Lucas Demchik
 * @version 0.1
 */
public class CourseNotFoundException extends NotFoundException {
    public CourseNotFoundException(String s){
        super(s);
    }
}
