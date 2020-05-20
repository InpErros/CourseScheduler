package data.Course;

import java.util.Comparator;

/**
 * {@code Course} defines a college course
 * @see Session
 * @see data.Database.CourseDatabase
 * @author Lucas Demchik
 * @version 0.1
 */
public class Course {
    private String department;
    private String code;
    private String description;
    private int minStudentCount;
    private int maxStudentCount;

    /**
     * Initializes data with blank strings
     */
    public Course() {
        this.department = " ";
        this.code = " ";
        this.description = " ";
        this.maxStudentCount = 0;
        this.minStudentCount = 0;
    }

    /**
     * Initializes course with the appropriate data
     * @param department
     * @param code
     * @param description
     * @param minStudentCount
     * @param maxStudentCount
     */
    public Course(String department, String code, String description, int minStudentCount, int maxStudentCount) {
        this.department = department;
        this.code = code;
        this.description = description;
        this.minStudentCount = minStudentCount;
        this.maxStudentCount = maxStudentCount;
    }

    /**
     * Initializes a new Course object as a copy of another
     * @param c Course object to be copied
     */
    public Course(Course c){
        this(c.department,c.code,c.description,c.minStudentCount,c.maxStudentCount);
    }

    /**
     * Accessor Method
     * @return department
     */
    public String getDepartment() { return department; }

    /**
     * Mutator Method
     * @param department
     */
    public void setDepartment(String department) { this.department = department; }

    /**
     * Accessor Method
     * @return code
     */
    public String getCode() { return code; }

    /**
     * Mutator Method
     * @param code
     */
    public void setCode(String code) { this.code = code; }

    /**
     * Accessor Method
     * @return description
     */
    public String getDescription() { return description; }

    /**
     * Mutator Method
     * @param description
     */
    public void setDescription(String description) { this.description = description; }

    /**
     * Accessor Method
     * @return minStudentCount
     */
    public int getMinStudentCount() { return minStudentCount; }

    /**
     * Mutator Method
     * @param minStudentCount
     */
    public void setMinStudentCount(int minStudentCount) { this.minStudentCount = minStudentCount; }

    /**
     * Accessor Method
     * @return maxStudentCount
     */
    public int getMaxStudentCount() { return maxStudentCount; }

    /**
     * Mutator Method
     * @param maxStudentCount
     */
    public void setMaxStudentCount(int maxStudentCount) { this.maxStudentCount = maxStudentCount; }

    /**
     * @return a string that represents the objects data
     */
    @Override
    public String toString() {
        return "Course ID: " + getDepartment() + getCode() + "\n" + getDescription() + "\nMin. Students: " + getMinStudentCount() + " Max. Students: " + getMaxStudentCount() + "\n";
    }

    public static Comparator<Course> courseComparator = new Comparator<Course>() {
        /**
         * Compares to Courses based of their Course id
         * @param s1 the first Course
         * @param s2 the second Course
         * @return the result of the comparison
         */
        @Override
        public int compare(Course s1, Course s2) {
            String course1 = s1.getDepartment() + s1.getCode();
            String course2 = s2.getDepartment() + s2.getCode();

            return course1.compareTo(course2);
        }
    };


}
