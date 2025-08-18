package inventory.Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {

    private static final String URL = "jdbc:sqlite:inventory.db";

    public Database() {
        try (Connection conn = getConnection(); Statement stmt = conn.createStatement()) {
            String productTable = "CREATE TABLE IF NOT EXISTS products (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "sku TEXT NOT NULL," +
                    "name TEXT NOT NULL," +
                    "description TEXT," +
                    "cost_price REAL," +
                    "sell_price REAL," +
                    "stock_quantity INTEGER," +
                    "reorder_threshold INTEGER," +
                    "reorder_amount INTEGER," +
                    "created_at TEXT," +
                    "updated_at TEXT)";
            stmt.execute(productTable);

            String salesTable = "CREATE TABLE IF NOT EXISTS sales (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "product_id INTEGER," +
                    "quantity INTEGER," +
                    "sale_price REAL," +
                    "total REAL," +
                    "sale_date TEXT," +
                    "FOREIGN KEY(product_id) REFERENCES products(id))";
            stmt.execute(salesTable);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL);
    }
}
