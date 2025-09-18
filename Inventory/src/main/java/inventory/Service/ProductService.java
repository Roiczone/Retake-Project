package inventory.Service;

import inventory.DataAccessObject.ProductDAO;
import inventory.DataAccessObject.ProductDAOImpl;
import inventory.model.Product;
import inventory.Exception.ValidationException;

import java.util.List;

public class ProductService {

    private ProductDAO dao = new ProductDAOImpl();

    public long addProduct(Product p) {
        validateProduct(p);
        return dao.create(p);
    }

    public List<Product> getAllProducts() {
        return dao.findAll();
    }

    public void updateProduct(Product p) {
        validateProduct(p);
        dao.update(p);
    }

    public void deleteProduct(long id) {
        dao.delete(id);
    }

    public List<Product> getLowStockProducts() {
        return dao.findAll().stream()
                .filter(p -> p.getStockQuantity() <= p.getReorderThreshold())
                .toList();
    }

    private void validateProduct(Product p) {
        if (p.getName() == null || p.getName().isBlank()) {
            throw new ValidationException("Product name cannot be empty");
        }
        if (p.getSku() == null || p.getSku().isBlank()) {
            throw new ValidationException("SKU cannot be empty");
        }
        if (p.getCostPrice() < 0) {
            throw new ValidationException("Cost price cannot be negative");
        }
        if (p.getSellPrice() < 0) {
            throw new ValidationException("Sell price cannot be negative");
        }
        if (p.getStockQuantity() < 0) {
            throw new ValidationException("Stock quantity cannot be negative");
        }
        if (p.getReorderThreshold() < 0) {
            throw new ValidationException("Reorder threshold cannot be negative");
        }
        if (p.getReorderAmount() < 0) {
            throw new ValidationException("Reorder amount cannot be negative");
        }
    }
}
