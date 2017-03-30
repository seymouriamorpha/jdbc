package by.seymouriamorpha.jdbc.utils;

import by.seymouriamorpha.jdbc.consts.Configuration;
import by.seymouriamorpha.jdbc.consts.Queries;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Eugene_Kortelyov on 3/30/2017.
 */
public class DataBaseUtil {

    public static void initDB(Connection connection) throws SQLException {
        Statement statement = null;
        try {
            statement = connection.createStatement();
            statement.execute(Queries.CREATE_USERS_TABLE);
            statement.execute(Queries.CREATE_FRIENDSHIPS_TABLE);
            statement.execute(Queries.CREATE_POSTS_TABLE);
            statement.execute(Queries.CREATE_LIKES_TABLE);
        } finally {
            if (statement != null) {
                statement.close();
            }
        }
    }

    public static void createUsers(Connection connection) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(Queries.CREATE_USER);

        int k = 1;

        for (int i = 1; i <= Configuration.USERS_NUMBER; i++) {
            if ( k <= Configuration.USERS_UNIQUE_NAMES) {
                k++;
            } else {
                k = 1;
            }
            preparedStatement.setString(1, "name #" + k);
            preparedStatement.setString(2, "surname #" + i);
            LocalDate date = LocalDate.of(Randomizer.randInt(Configuration.USER_MIN_BIRTH_YEAR, Configuration.USER_MAX_BIRTH_YEAR), Randomizer.randInt(1, 12), Randomizer.randInt(1, 28));
            preparedStatement.setDate(3, Date.valueOf(date));
            preparedStatement.addBatch();
        }

        preparedStatement.executeBatch();
        preparedStatement.close();

    }

    public static void createFriendships(Connection connection) throws SQLException {

        Set<Pair> pairs = new HashSet<>();

        for(int i = 1; i <= Configuration.MAX_FRIENDSHIPS; i++) {
            int userId1 = Randomizer.randInt(1, Configuration.USERS_NUMBER);
            int userId2 = Randomizer.randInt(1, Configuration.USERS_NUMBER);

            while ( userId1 == userId2) {
                userId2 = Randomizer.randInt(1, Configuration.USERS_NUMBER);
            }

            if (!pairs.contains(new Pair(userId2, userId1))) {
                pairs.add(new Pair(userId1, userId2));
            }
        }

        System.out.println("TOTAL Friendships: " + pairs.size());

        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement(Queries.CREATE_FRIENDSHIP);
            for (Pair pair: pairs) {
                preparedStatement.setInt(1, pair.getValue1());
                preparedStatement.setInt(2, pair.getValue2());
                preparedStatement.setTimestamp(3, Timestamp.valueOf(LocalDateTime.now()));
                preparedStatement.addBatch();
            }
            preparedStatement.executeBatch();
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
        }
    }

    public static void createPosts(Connection connection) throws SQLException {

        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement(Queries.CREATE_POST);
            for (int i = 1; i <= Configuration.USERS_NUMBER; i++) {
                int userId = Randomizer.randInt(1, Configuration.USERS_NUMBER);
                preparedStatement.setInt(1, userId);
                preparedStatement.setString(2, "Text from user ID: " + userId + ", post #" + i);
                preparedStatement.setTimestamp(3, Timestamp.valueOf(LocalDateTime.now()));
                preparedStatement.addBatch();
            }
            preparedStatement.executeBatch();
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
        }
    }

    public static void createLikes(Connection connection) throws SQLException {

        Set<Pair> pairs = new HashSet<>();

        for (int i = 1; i <= Configuration.MAX_LIKES; i++) {
            int postId = Randomizer.randInt(1, Configuration.USERS_NUMBER);
            int userId = Randomizer.randInt(1, Configuration.USERS_NUMBER);

            pairs.add(new Pair(postId, userId));
        }

        System.out.println("TOTAL Likes: " + pairs.size());

        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement(Queries.CREATE_LIKE);
            for (Pair pair: pairs) {
                preparedStatement.setInt(1, pair.getValue1());
                preparedStatement.setInt(2, pair.getValue2());
                LocalDate date = LocalDate.of(
                        Randomizer.randInt(Configuration.LIKE_MIN_YEAR, Configuration.LIKE_MAX_YEAR),
                        Randomizer.randInt(Configuration.LIKE_MIN_MONTH, Configuration.LIKE_MAX_MONTH),
                        Randomizer.randInt(1, 28)
                );
                LocalTime time = LocalTime.of(Randomizer.randInt(0, 23), Randomizer.randInt(0, 59), Randomizer.randInt(0, 59));
                Timestamp timestamp = Timestamp.valueOf(LocalDateTime.of(date, time));
                preparedStatement.setTimestamp(3, timestamp);
                preparedStatement.addBatch();
            }
            preparedStatement.executeBatch();
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
        }
    }

}
