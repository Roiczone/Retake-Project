package inventory.DataAccessObject;

import inventory.model.Product;
import java.util.List;

public interface ProductDAO {
    long create(Product p);
    List<Product> findAll();
    void update(Product p);
    void delete(long id);
}

