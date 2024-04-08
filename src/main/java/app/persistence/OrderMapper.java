package app.persistence;

import app.entities.Order;
import app.exceptions.DatabaseException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderMapper {

    public static List<Order> orderList = new ArrayList<>();

    public static List<Order> getAllOrder(ConnectionPool connectionPool) throws DatabaseException {
        String sql = "select * from order;";

        try (
                Connection connection = connectionPool.getConnection();
                PreparedStatement ps = connection.prepareStatement(sql)
        ) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int order_id = rs.getInt("order_id");
                int total_price = rs.getInt("total_price");
                String user_name = rs.getString("user_name");
                int order_amount = rs.getInt("order_amount");
                int user_id = rs.getInt("user_id");
                orderList.add(new Order(order_id, total_price, user_name, order_amount, user_id));
            }

        } catch (SQLException e) {
            throw new DatabaseException("Fejl i tilgangen til databasen", e.getMessage());
        }
        return orderList;

    }


}
