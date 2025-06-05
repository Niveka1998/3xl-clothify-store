package repository.custom.impl;

import dto.Product;
import repository.custom.ProductRepository;

import java.util.List;

public class ProductRepositoryImpl implements ProductRepository {
    @Override
    public Boolean addProduct(Product product) {
        return null;
    }

    @Override
    public Boolean updateProduct(Product product) {
        return null;
    }

    @Override
    public Boolean deleteProduct(int id) {
        return null;
    }

    @Override
    public Product searchProductById(int id) {
        return null;
    }

    @Override
    public List<Product> getAll() {
        return List.of();
    }
}
