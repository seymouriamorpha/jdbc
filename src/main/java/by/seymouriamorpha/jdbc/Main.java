package by.seymouriamorpha.jdbc;

import by.seymouriamorpha.jdbc.consts.Configuration;
import by.seymouriamorpha.jdbc.dao.SocialNetworkDAO;
import by.seymouriamorpha.jdbc.entities.User;
import by.seymouriamorpha.jdbc.utils.DataBaseUtil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * @author Eugene_Kortelyov on 3/30/2017.
 */
public class Main {

    public static void main(String[] args) {
        long startTime = System.nanoTime();
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(Configuration.DB_URL);
            DataBaseUtil.initDB(connection);
            DataBaseUtil.createUsers(connection);
            DataBaseUtil.createFriendships(connection);
            DataBaseUtil.createPosts(connection);
            DataBaseUtil.createLikes(connection);

            SocialNetworkDAO dao = new SocialNetworkDAO();
            List<User> usersWithMoreThan100Friends = dao.getUsersWithFriends(connection, 100);
            Set<String> usersWithMoreThan100Likes = new TreeSet<>();

            for (User user: usersWithMoreThan100Friends) {
                int likes = dao.getUserLikesByDate(connection, user.getId(), 2015, 3);
                if (likes > 100) {
                    usersWithMoreThan100Likes.add(user.getName());
                }
            }

            System.out.println("LIST OF USER NAMES (distinct) who has more when 100 friends and 100 likes in March 2017: ");
            System.out.println(usersWithMoreThan100Likes);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        long endTime = System.nanoTime();
        long duration = (endTime - startTime);

        System.out.println("Time: " + duration/1000000000 + " seconds");
    }

}
