package app.persistence;

import app.entities.Bottoms;
import app.exceptions.DatabaseException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BottomsMapper {

    private static ArrayList<Bottoms> bottomsList = new ArrayList<>();

    public static List<Bottoms> getAllBottoms(ConnectionPool connectionPool) throws DatabaseException {

        String sql = "select * from public.bottoms";

        try (
                Connection connection = connectionPool.getConnection();
                PreparedStatement ps = connection.prepareStatement(sql)
        ) {

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int bottom_id = rs.getInt("bottom_id");
                String flavor = rs.getString("flavor");
                int price = rs.getInt("price");
                bottomsList.add(new Bottoms(bottom_id, flavor, price));
            }
        } catch (SQLException e) {
            throw new DatabaseException("Fejl!!!!", e.getMessage());
        }
        return bottomsList;
    }

    public static Bottoms getBottomsById(int bot_id, ConnectionPool connectionPool) throws DatabaseException {

        String sql = "select * from public.bottoms WHERE bottom_id = ?";
        Bottoms cupCakeBottom = null;

        try (
                Connection connection = connectionPool.getConnection();
                PreparedStatement ps = connection.prepareStatement(sql)
        ) {

            ps.setInt(1, bot_id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int bottom_id = rs.getInt("bottom_id");
                String flavor = rs.getString("flavor");
                int price = rs.getInt("price");
                cupCakeBottom = new Bottoms(bottom_id, flavor, price);
            }
        } catch (SQLException e) {
            throw new DatabaseException("Fejl!!!!", e.getMessage());
        }
        return cupCakeBottom;
    }

}