package app.persistence;

import app.entities.Bottoms;
import app.entities.User;
import app.exceptions.DatabaseException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import io.javalin.http.Context;

public class UserMapper
{
    private static final List<User> userList = new ArrayList<>();
    static User currentUser;

    public static User login(String userName, String password, ConnectionPool connectionPool) throws DatabaseException
    {
        String sql = "select * from public.\"users\" where user_name=? and user_password=?";

        try (
                Connection connection = connectionPool.getConnection();
                PreparedStatement ps = connection.prepareStatement(sql)
        )
        {
            ps.setString(1, userName);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();
            if (rs.next())
            {
                int id = rs.getInt("user_id");
                String role = rs.getString("user_role");
                int wallet =rs.getInt("user_wallet");
                return new User(id, userName, password, wallet, role);
            } else
            {
                throw new DatabaseException("Fejl i login. Prøv igen");
            }
        }
        catch (SQLException e)
        {
            throw new DatabaseException("DB fejl", e.getMessage());
        }
    }

    public static void createuser(String userName, String password, ConnectionPool connectionPool) throws DatabaseException
    {
        String sql = "insert into users (user_name, user_password, user_wallet, user_role) values (?,?, ?, ?)";

        try (
                Connection connection = connectionPool.getConnection();
                PreparedStatement ps = connection.prepareStatement(sql)
        )
        {
            ps.setString(1, userName);
            ps.setString(2, password);
            ps.setInt(3, 0);
            ps.setString(4, "user");

            int rowsAffected = ps.executeUpdate();
            if (rowsAffected != 1)
            {
                throw new DatabaseException("Fejl ved oprettelse af ny bruger");
            }
        }
        catch (SQLException e)
        {
            String msg = "Der er sket en fejl. Prøv igen";
            if (e.getMessage().startsWith("ERROR: duplicate key value "))
            {
                msg = "Brugernavnet findes allerede. Vælg et andet";
            }
            throw new DatabaseException(msg, e.getMessage());
        }
    }

    public static List<User> getAllUsers(Context ctx, ConnectionPool connectionPool) throws DatabaseException {
        String sql = "select * from public.users";

        try (
                Connection connection = connectionPool.getConnection();
                PreparedStatement ps = connection.prepareStatement(sql)
        ) {

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int user_id = rs.getInt("user_id");
                String user_name = rs.getString("user_name");
                String user_password = rs.getString("user_password");
                int user_wallet = rs.getInt("user_wallet");
                String user_role = rs.getString("user_role");
                userList.add(new User(user_id, user_name, user_password, user_wallet, user_role));
            }
        } catch (SQLException e) {
            throw new DatabaseException("Could not get all users", e.getMessage());
        }
        return userList;
    }

    public static User getSpecificUser(int u_id, ConnectionPool connectionPool) throws DatabaseException {
        String sql = "SELECT * FROM users WHERE user_id=?";

        try (
                Connection connection = connectionPool.getConnection();
                PreparedStatement ps = connection.prepareStatement(sql)
        ) {

            ps.setInt(1, u_id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int user_id = rs.getInt("user_id");
                String user_name = rs.getString("user_name");
                String user_password = rs.getString("user_password");
                int user_wallet = rs.getInt("user_wallet");
                String user_role = rs.getString("user_role");
                currentUser = (new User(user_id, user_name, user_password, user_wallet, user_role));
            }
        } catch (SQLException e) {
            throw new DatabaseException("Fejl!!!!", e.getMessage());
        }
        return currentUser;
    }

    public static void editUserBalance(Context ctx, ConnectionPool connectionPool) throws DatabaseException{

        String sql = "SELECT * FROM users WHERE user_id=" + currentUser;

        try (
                Connection connection = connectionPool.getConnection();
                PreparedStatement ps = connection.prepareStatement(sql)
        )
        {
            ps.setString(1, "user_wallet");

            int rowsAffected = ps.executeUpdate();
            if (rowsAffected != 1)
            {
                throw new DatabaseException("Fejl ved oprettelse af ny bruger");
            }
        }
        catch (SQLException e) {
            throw new DatabaseException("Could not get all users", e.getMessage());
        }
    }
}