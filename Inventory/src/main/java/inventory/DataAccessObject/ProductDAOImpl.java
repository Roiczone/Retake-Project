package inventory.DataAccessObject;


import inventory.model.Product;
import inventory.Database.Database;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ProductDAOImpl implements inventory.DataAccessObject.ProductDAO {

    private Database db = new Database();

    @Override
    public long create(Product p) {
        String sql = "INSERT INTO products (sku,name,description,cost_price,sell_price,stock_quantity,reorder_threshold,reorder_amount,created_at,updated_at) VALUES (?,?,?,?,?,?,?,?,?,?)";
        try (Connection c = db.getConnection();
             PreparedStatement ps = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, p.getSku());
            ps.setString(2, p.getName());
            ps.setString(3, p.getDescription());
            ps.setDouble(4, p.getCostPrice());
            ps.setDouble(5, p.getSellPrice());
            ps.setInt(6, p.getStockQuantity());
            ps.setInt(7, p.getReorderThreshold());
            ps.setInt(8, p.getReorderAmount());
            ps.setString(9, LocalDateTime.now().toString());
            ps.setString(10, LocalDateTime.now().toString());

            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    long id = rs.getLong(1);
                    p.setId(id);
                    return id;
                } else {
                    throw new inventory.Exception.DataAccessException("Failed to get generated ID");
                }
            }

        } catch (SQLException e) {
            throw new inventory.Exception.DataAccessException("Error creating product", e);
        }
    }

    @Override
    public List<Product> findAll() {
        List<Product> products = new ArrayList<>();
        String sql = "SELECT * FROM products";
        try (Connection c = db.getConnection();
             PreparedStatement ps = c.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Product p = new Product();
                p.setId(rs.getLong("id"));
                p.setSku(rs.getString("sku"));
                p.setName(rs.getString("name"));
                p.setDescription(rs.getString("description"));
                p.setCostPrice(rs.getDouble("cost_price"));
                p.setSellPrice(rs.getDouble("sell_price"));
                p.setStockQuantity(rs.getInt("stock_quantity"));
                p.setReorderThreshold(rs.getInt("reorder_threshold"));
                p.setReorderAmount(rs.getInt("reorder_amount"));
                products.add(p);
            }

        } catch (SQLException e) {
            throw new inventory.Exception.DataAccessException("Error fetching products", e);
        }
        return products;
    }


@Override
public void update(Product p) {
    String sql = "UPDATE products SET sku=?, name=?, description=?, cost_price=?, sell_price=?, stock_quantity=?, reorder_threshold=?, reorder_amount=?, updated_at=? WHERE id=?";
    try (Connection c = db.getConnection();
         PreparedStatement ps = c.prepareStatement(sql)) {

        ps.setString(1, p.getSku());
        ps.setString(2, p.getName());
        ps.setString(3, p.getDescription());
        ps.setDouble(4, p.getCostPrice());
        ps.setDouble(5, p.getSellPrice());
        ps.setInt(6, p.getStockQuantity());
        ps.setInt(7, p.getReorderThreshold());
        ps.setInt(8, p.getReorderAmount());
        ps.setString(9, LocalDateTime.now().toString());
        ps.setLong(10, p.getId());

        int rows = ps.executeUpdate();
        if (rows == 0) throw new inventory.Exception.DataAccessException("No product found with ID " + p.getId());

    } catch (SQLException e) {
        throw new inventory.Exception.DataAccessException("Error updating product", e);
    }
}

@Override
public void delete(long id) {
    String sql = "DELETE FROM products WHERE id=?";
    try (Connection c = db.getConnection();
         PreparedStatement ps = c.prepareStatement(sql)) {
        ps.setLong(1, id);
        ps.executeUpdate();
    } catch (SQLException e) {
        throw new inventory.Exception.DataAccessException("Error deleting product", e);
    }
}
}
