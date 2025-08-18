package inventory.DataAccessObject;

import inventory.model.Sale;
import java.util.List;

public interface SaleDAO {
    long create(Sale s);
    List<Sale> findAll();
}
