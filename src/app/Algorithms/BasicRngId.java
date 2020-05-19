package app.Algorithms;

/**
 * {@code BasicRngId} is an algorithm that generates a unique 7 digit ID using a Random Number Generator
 * This algorithm can be swapped out at runtime because of its implementation of {@code IDgenerator}
 * @see app.Algorithms.IDgenerator
 * @author Lucas Demchik
 * @version 0.1
 */
public class BasicRngId implements IDgenerator {
    /**
     * {@code generateID()} creates a 7 digit unique ID using {@code Math.random()}
     * @return id string
     */
    @Override
    public String generateID() {
        StringBuilder s = new StringBuilder();
        for(int i = 0; i < 7; ++i) {
            s.append((int) (Math.random() * 9)); // RNG 0-9
        }
        return s.toString();
    }
}
