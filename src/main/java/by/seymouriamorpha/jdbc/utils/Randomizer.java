package by.seymouriamorpha.jdbc.utils;

import java.util.Random;

/**
 * @author Eugene_Kortelyov on 3/30/2017.
 */
public class Randomizer {

    public static int randInt(int min, int max) {
        return new Random().nextInt((max - min) + 1) + min;
    }

}
