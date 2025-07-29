package service.custom;

import dto.Product;
import service.SuperService;

import java.sql.SQLException;
import java.util.List;

public interface ProductService extends SuperService {
    Boolean addProduct(Product product);
    Boolean updateProduct(Product product);
    Boolean deleteProduct(int id);
    Product searchProductById(int id) throws SQLException;
    List<Product> getAll();
    boolean updateProductQuantity(int productId, int newQty) throws SQLException;
    List<Integer> getProductIds();
}
