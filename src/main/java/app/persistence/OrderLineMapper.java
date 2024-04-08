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

    private static List<Orderline> orderLineList = new ArrayList<>();

    public static List<Orderline> getAllOrderLines(ConnectionPool connectionPool) throws DatabaseException {
        String sql = "select * from orderline;";

        try (
                Connection connection = connectionPool.getConnection();
                PreparedStatement ps = connection.prepareStatement(sql)
        ) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int orderline_id = rs.getInt("orderline_id");
                int order_id = rs.getInt("order_id");
                int total_price = rs.getInt("total_price");
                int topping_id = rs.getInt("topping_id");
                int bottom_id = rs.getInt("bottom_id");

                orderLineList.add(new Orderline(orderline_id, order_id, total_price, topping_id, bottom_id));
            }

        } catch (SQLException e) {
            throw new DatabaseException("Fejl i tilgangen til databasen", e.getMessage());
        }
        return orderLineList;
    }

    public static Orderline getOrderLineById(int ord_id, ConnectionPool connectionPool) throws DatabaseException {

        String sql = "select * from orderline WHERE order_id = ?;";
        Orderline cupCakeOrder = null;

        try (
                Connection connection = connectionPool.getConnection();
                PreparedStatement ps = connection.prepareStatement(sql)
        ) {

            ps.setInt(1, ord_id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int orderline_id = rs.getInt("orderline_id");
                int order_id = rs.getInt("order_id");
                int total_price = rs.getInt("total_price");
                int topping_id = rs.getInt("topping_id");
                int bottom_id = rs.getInt("bottom_id");

                cupCakeOrder = new Orderline(orderline_id, order_id, total_price, topping_id, bottom_id);
            }

        } catch (SQLException e) {
            throw new DatabaseException("Fejl i tilgangen til databasen", e.getMessage());
        }
        return cupCakeOrder;
    }

    public static void addOrderLine(int topping_id, int bottom_id, int amount, ConnectionPool connectionPool) throws DatabaseException {
        String sql = "INSERT INTO orderline (topping_id, bottom_id, amount) VALUES (?,?,?); ";

        try (
                Connection connection = connectionPool.getConnection();
                PreparedStatement ps = connection.prepareStatement(sql)
        ) {
            ps.setInt(1, topping_id);
            ps.setInt(2, bottom_id);
            ps.setInt(3, amount);
            int rowsAffected = ps.executeUpdate();

        } catch (SQLException e) {
            throw new DatabaseException("Fejl i tilgangen til databasen", e.getMessage());
        }
    }

    public static void removeOrderLineById(int orderline_id, ConnectionPool connectionPool) throws DatabaseException {

        String sql = "DELETE FORM orderline WHERE orderline_id = ?";

        try (
                Connection connection = connectionPool.getConnection();
                PreparedStatement ps = connection.prepareStatement(sql)
        ) {
            ps.setInt(1, orderline_id);
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new DatabaseException("Fejl i tilgangen til databasen", e.getMessage());
        }


    }
}