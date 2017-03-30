package by.seymouriamorpha.jdbc.dao;

import by.seymouriamorpha.jdbc.entities.User;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * @author Eugene_Kortelyov on 3/30/2017.
 */
public interface ISocialNetworkDAO {

    List<User> getUsersWithFriends(Connection connectionm, int minFriends) throws SQLException;

    int getUserLikesByDate(Connection connection, int userID, int year, int month) throws SQLException;

}
