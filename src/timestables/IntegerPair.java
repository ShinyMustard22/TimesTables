package timestables;

public class IntegerPair {

    private int integer1;
    private int integer2;

    public IntegerPair(int integer1, int integer2) {
        this.integer1 = integer1;
        this.integer2 = integer2;
    }

    public int getInteger1() {
        return integer1;
    }

    public int getInteger2() {
        return integer2;
    }

    public int correctAnswer() { return integer1 * integer2; }

    public String toString() { return integer1 + " * " + integer2 + " = "; }
}
