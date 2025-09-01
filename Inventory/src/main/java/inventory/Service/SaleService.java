package inventory.Service;

import inventory.DataAccessObject.SaleDAO;
import inventory.DataAccessObject.SaleDAOImpl;
import inventory.DataAccessObject.ProductDAO;
import inventory.DataAccessObject.ProductDAOImpl;
import inventory.model.Sale;
import inventory.model.Product;

import java.util.List;

    public class SaleService {

        private SaleDAO saleDAO = new SaleDAOImpl();
        private ProductDAO productDAO = new ProductDAOImpl();

        public long recordSale(Product product, int quantity) {
            if (quantity > product.getStockQuantity()) throw new RuntimeException("Not enough stock!");

            double total = product.getSellPrice() * quantity;
            Sale s = new Sale();
            s.setProduct(product);
            s.setQuantity(quantity);
            s.setSalePrice(product.getSellPrice());
            s.setTotal(total);


            product.setStockQuantity(product.getStockQuantity() - quantity);
            productDAO.update(product);

            return saleDAO.create(s);
        }

        public List<Sale> getAllSales() {
            return saleDAO.findAll();
        }

        public double getTotalRevenue() {
            return getAllSales().stream().mapToDouble(Sale::getTotal).sum();
        }

        public double getTotalProfit() {
            return getAllSales().stream().mapToDouble(s -> (s.getSalePrice() - s.getProduct().getCostPrice()) * s.getQuantity()).sum();
        }
    }

}
