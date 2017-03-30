package by.seymouriamorpha.jdbc.consts;

/**
 * @author Eugene_Kortelyov on 3/30/2017.
 */
public final class Configuration {

    public static final String DB_URL = "jdbc:derby:memory:social-network;create=true";
    public static final int USERS_NUMBER = 1000;
    public static final int USERS_UNIQUE_NAMES = 50;
    public static final int USER_MIN_BIRTH_YEAR = 1950;
    public static final int USER_MAX_BIRTH_YEAR = 2016;
    public static final int MAX_FRIENDSHIPS = 80_000;
    public static final int MAX_LIKES = 400_000;
    public static final int LIKE_MIN_YEAR = 2015;
    public static final int LIKE_MAX_YEAR = 2015;
    public static final int LIKE_MIN_MONTH = 1;
    public static final int LIKE_MAX_MONTH = 12;

}
