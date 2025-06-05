package repository.custom;

import dto.Product;

import java.util.List;

public interface ProductRepository{
    Boolean addProduct(Product product);
    Boolean updateProduct(Product product);
    Boolean deleteProduct(int id);
    Product searchProductById(int id);
    List<Product> getAll();
}
