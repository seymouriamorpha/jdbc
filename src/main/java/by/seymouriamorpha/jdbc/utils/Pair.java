package by.seymouriamorpha.jdbc.utils;

/**
 * @author Eugene_Kortelyov on 3/30/2017.
 */
public class Pair {

    private int value1;
    private int value2;

    public Pair(int value1, int value2) {
        this.value1 = value1;
        this.value2 = value2;
    }

    public int getValue1() {
        return value1;
    }

    public int getValue2() {
        return value2;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Pair)) return false;

        Pair pair = (Pair) o;

        if (value1 != pair.value1) return false;
        return value2 == pair.value2;

    }

    @Override
    public int hashCode() {
        int result = value1;
        result = 31 * result + value2;
        return result;
    }

}
