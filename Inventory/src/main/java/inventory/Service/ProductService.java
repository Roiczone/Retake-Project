package inventory.Service;

import inventory.DataAccessObject.ProductDAO;
import inventory.DataAccessObject.ProductDAOImpl;
import inventory.model.Product;

import java.util.List;

public class ProductService {

    private ProductDAO dao = new ProductDAOImpl();

    public long addProduct(Product p) {
        return dao.create(p);
    }

    public List<Product> getAllProducts() {
        return dao.findAll();
    }

    public void updateProduct(Product p) {
        dao.update(p);
    }

    public void deleteProduct(long id) {
        dao.delete(id);
    }

    // Check low-stock products
    public List<Product> getLowStockProducts() {
        return dao.findAll().stream()
                .filter(p -> p.getStockQuantity() <= p.getReorderThreshold())
                .toList();
    }
}

