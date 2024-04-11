package app.persistence;


import app.entities.Order;
import app.entities.Orderline;
import app.exceptions.DatabaseException;

import javax.xml.crypto.Data;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderLineMapper {

    public static void addOrderLine(int orderId, int total_price, int topping_id, int bottom_id, int amount, ConnectionPool connectionPool) throws DatabaseException {
        String sql = "INSERT INTO orderline (order_id, total_price, topping_id, bottom_id, amount) VALUES (?,?,?,?,?); ";

        try (
                Connection connection = connectionPool.getConnection();
                PreparedStatement ps = connection.prepareStatement(sql)
        ) {
            ps.setInt(1, orderId);
            ps.setInt(2, total_price);
            ps.setInt(3, topping_id);
            ps.setInt(4, bottom_id);
            ps.setInt(5, amount);
            int rowsAffected = ps.executeUpdate();

        } catch (SQLException e) {
            throw new DatabaseException("Fejl i tilgangen til databasen", e.getMessage());
        }
    }

}