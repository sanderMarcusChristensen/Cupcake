package app.persistence;

import app.entities.Toppings;
import app.exceptions.DatabaseException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ToppingsMapper {

    private static ArrayList<Toppings> toppingsList = new ArrayList<>();

    public static List<Toppings> getAllToppings(ConnectionPool connectionPool) throws DatabaseException {

        String sql = "select * from public.toppings";

        try (
                Connection connection = connectionPool.getConnection();
                PreparedStatement ps = connection.prepareStatement(sql)
        ) {

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int topping_id = rs.getInt("topping_id");
                String flavor = rs.getString("flavor");
                int price = rs.getInt("price");
                toppingsList.add(new Toppings(topping_id, flavor, price));
            }
        } catch (SQLException e) {
            throw new DatabaseException("Fejl!!!!", e.getMessage());
        }
        return toppingsList;
    }

    public static Toppings getToppingsById(int top_id, ConnectionPool connectionPool) throws DatabaseException {
        String sql = "select * from toppings where topping_id = ?";
        Toppings cupcakeTop = null;
        try (
                Connection connection = connectionPool.getConnection();
                PreparedStatement ps = connection.prepareStatement(sql)
        ) {
            ps.setInt(1, top_id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int topping_id = rs.getInt("topping_id");
                String flavor = rs.getString("flavor");
                int price = rs.getInt("price");
                cupcakeTop = new Toppings(topping_id, flavor, price);
            }
        } catch (SQLException e) {
            throw new DatabaseException("Fejl i tilgangen til databasen", e.getMessage());
        }
        return cupcakeTop;
    }

}