package by.seymouriamorpha.jdbc.dao;

import by.seymouriamorpha.jdbc.consts.Queries;
import by.seymouriamorpha.jdbc.entities.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Eugene_Kortelyov on 3/30/2017.
 */
public class SocialNetworkDAO implements ISocialNetworkDAO {

    @Override
    public List<User> getUsersWithFriends(Connection connection, int minFriends) throws SQLException {

        List<User> users = new ArrayList<>();

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {

            preparedStatement = connection.prepareStatement(Queries.USERS_WITH_FRIENDS);
            preparedStatement.setInt(1, minFriends);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String surname = resultSet.getString("surname");
                Date date = resultSet.getDate("birthdate");
                User user = new User(id, name, surname, date);
                users.add(user);
            }

        } finally {

            try {
                if(resultSet!=null) {
                    resultSet.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            try {
                if(preparedStatement!=null) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return users;
    }

    @Override
    public int getUserLikesByDate(Connection connection, int userID, int year, int month) throws SQLException {

        int likes = 0;

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {

            preparedStatement = connection.prepareStatement(Queries.LIKES_BY_USER);
            preparedStatement.setInt(1, userID);
            preparedStatement.setInt(2, year);
            preparedStatement.setInt(3, month);
            resultSet = preparedStatement.executeQuery();

            if(resultSet.next()) {
                likes = resultSet.getInt("count");
            }

        } finally {

            try {
                if(resultSet!=null) {
                    resultSet.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            try {
                if(preparedStatement!=null) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return likes;
    }

}
