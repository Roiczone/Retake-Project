package inventory.DataAccessObject;

import inventory.Database.Database;
import inventory.Exception.DataAccessException;
import inventory.model.Sale;
import inventory.model.Product;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class SaleDAOImpl implements SaleDAO {

    private Database db = new Database();

    @Override
    public long create(Sale s) {
        String sql = "INSERT INTO sales (product_id, quantity, sale_price, total, sale_date) VALUES (?,?,?,?,?)";
        try (Connection c = db.getConnection();
             PreparedStatement ps = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setLong(1, s.getProduct().getId());
            ps.setInt(2, s.getQuantity());
            ps.setDouble(3, s.getSalePrice());
            ps.setDouble(4, s.getTotal());
            ps.setString(5, LocalDateTime.now().toString());
            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    s.setId(rs.getLong(1));
                    return s.getId();
                } else throw new DataAccessException("Failed to get sale ID");
            }
        } catch (SQLException e) {
            throw new DataAccessException("Error creating sale", e);
        }
    }

    @Override
    public List<Sale> findAll() {
        List<Sale> sales = new ArrayList<>();
        String sql = "SELECT s.id, s.quantity, s.sale_price, s.total, s.sale_date, p.id as product_id, p.name as product_name " +
                "FROM sales s JOIN products p ON s.product_id = p.id";

        try (Connection c = db.getConnection();
             PreparedStatement ps = c.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Sale s = new Sale();
                Product p = new Product();
                p.setId(rs.getLong("product_id"));
                p.setName(rs.getString("product_name"));

                s.setId(rs.getLong("id"));
                s.setProduct(p);
                s.setQuantity(rs.getInt("quantity"));
                s.setSalePrice(rs.getDouble("sale_price"));
                s.setTotal(rs.getDouble("total"));
                s.setSaleDate(LocalDateTime.parse(rs.getString("sale_date")));
                sales.add(s);
            }
        } catch (SQLException e) {
            throw new DataAccessException("Error fetching sales", e);
        }
        return sales;
    }
}
