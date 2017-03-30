package by.seymouriamorpha.jdbc.consts;

/**
 * @author Eugene_Kortelyov on 3/30/2017.
 */
public final class Queries {

    public static final String CREATE_USERS_TABLE = "CREATE TABLE users (id INT NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1), name VARCHAR(255), surname VARCHAR(255), birthdate DATE, PRIMARY KEY(id))";
    public static final String CREATE_FRIENDSHIPS_TABLE = "CREATE TABLE friendships (user_id1 INT, user_id2 INT, timestamp TIMESTAMP, FOREIGN KEY(user_id1) REFERENCES users(id), FOREIGN KEY(user_id2) REFERENCES users(id))";
    public static final String CREATE_POSTS_TABLE = "CREATE TABLE posts (id INT NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1), user_id INT, text VARCHAR(255), timestamp TIMESTAMP, FOREIGN KEY(user_id) REFERENCES users(id), PRIMARY KEY(id))";
    public static final String CREATE_LIKES_TABLE = "CREATE TABLE likes (post_id INT, user_id INT, timestamp TIMESTAMP, FOREIGN KEY(post_id) REFERENCES posts(id), FOREIGN KEY(user_id) REFERENCES users(id))";

    public static final String CREATE_USER = "INSERT INTO users (name, surname, birthdate) VALUES(?, ?, ?)";
    public static final String CREATE_FRIENDSHIP = "INSERT INTO friendships VALUES(?, ?, ?)";
    public static final String CREATE_POST = "INSERT INTO posts (user_id, text, timestamp) VALUES (?, ?, ?)";
    public static final String CREATE_LIKE = "INSERT INTO likes VALUES (?, ?, ?)";

    public static final String USERS_WITH_FRIENDS = "SELECT users.id, users.name, users.surname, users.birthdate FROM users JOIN friendships ON users.id=friendships.user_id1 OR users.id=friendships.user_id2 GROUP BY users.id, users.name, users.surname, users.birthdate HAVING COUNT(users.id) > ?";
    public static final String LIKES_BY_USER = "SELECT COUNT(*) as count FROM likes WHERE post_id IN (SELECT id FROM posts WHERE user_id=?) AND YEAR(timestamp) = ? AND MONTH(timestamp) = ?";

}
