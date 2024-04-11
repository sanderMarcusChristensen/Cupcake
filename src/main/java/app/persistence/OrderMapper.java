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


    public static List<Order> getAllOrder(ConnectionPool connectionPool) throws DatabaseException {

        List<Order> orderList = new ArrayList<>();

        String sql = "select o.order_id, o.total_price, o.orderline_amount, o.user_id, u.user_name " +
                "from public.order o inner join users u using(user_id)";

        try (
                Connection connection = connectionPool.getConnection();
                PreparedStatement ps = connection.prepareStatement(sql)
        ) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {

                int order_id = rs.getInt("order_id");
                int total_price = rs.getInt("total_price");
                int orderline_amount = rs.getInt("orderline_amount");
                int user_id = rs.getInt("user_id");
                String user_name = rs.getString("user_name");
                orderList.add(new Order(order_id, total_price, orderline_amount, user_id, user_name));
            }
        } catch (SQLException e) {
            throw new DatabaseException("Error while retrieving orders: " + e.getMessage());
        }

        return orderList;
    }

    public static int addOrderToDatabase(Order order, ConnectionPool connectionPool) throws DatabaseException {
        String sql = "INSERT INTO \"order\" (total_price, orderline_amount, user_id) VALUES (?, ?, ?);";

        try (
                Connection connection = connectionPool.getConnection();
                PreparedStatement ps = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)
        ) {
            ps.setInt(1, order.getTotal_price());
            ps.setInt(2, order.getOrderline_amount());
            ps.setInt(3, order.getUser_id());

            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                return rs.getInt(1); // Return the generated order_id
            } else {
                throw new SQLException("Creating order failed, no ID obtained.");
            }
        } catch (SQLException e) {
            throw new DatabaseException("Fejl i tilgangen til databasen", e.getMessage());
        }
    }
}