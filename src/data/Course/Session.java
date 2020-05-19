package data.Course;

import app.Algorithms.IDgenerator;

public class Session extends Course {
    private String id;
    private IDgenerator generatorAlgorithm;

    public Session(String department, String code, String description, int minStudentCount, int maxStudentCount){
        super(department, code, description, minStudentCount, maxStudentCount);
    }

    /**
     * Accessor Method
     * @return id
     */
    public String getID() { return id; }

    public void setGeneratorAlgorithm(IDgenerator generatorAlgorithm) { this.generatorAlgorithm = generatorAlgorithm; }

    /**
     * Overridden Method using a algorithm selected at runtime to generate an ID
     * @see app.Algorithms.IDgenerator
     */
    public void makeID() {
        id = "c" + generatorAlgorithm.generateID();
    }
}
