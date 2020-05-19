package app.Algorithms;

/**
 * {@code IDgenerator} is an interface designed to allow swapping of generating algorithms.
 * Requires implementation of {@code generateID()} method
 * @see BasicRngId
 * @author Lucas Demchik
 * @version 0.1
 */
public interface IDgenerator {
    /**
     * Method to be implemented by different generating algorithms
     * @return a generated id
     */
    String generateID();
}
