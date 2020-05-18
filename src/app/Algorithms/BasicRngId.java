package app.Algorithms;

public class BasicRngId implements IDgenerator {
    @Override
    public String generateID() {
        String s = "";
        for(int i = 0; i < 7; ++i) {
            s += (int) (Math.random() * 9); // RNG 0-9
        }
        return s;
    }
}
