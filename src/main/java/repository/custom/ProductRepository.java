package repository.custom;

import dto.Product;
import entity.ProductEntity;
import repository.CrudRepository;

import java.sql.SQLException;
import java.util.List;

public interface ProductRepository extends CrudRepository<ProductEntity, Integer> {
    boolean updateQuantity(int productId, int newQty) throws SQLException;

}

