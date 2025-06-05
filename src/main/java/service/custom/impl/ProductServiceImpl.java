package service.custom.impl;

import dto.Product;
import entity.ProductEntity;
import org.modelmapper.ModelMapper;
import service.custom.ProductService;

import java.util.List;

public class ProductServiceImpl implements ProductService {
    @Override
    public Boolean addProduct(Product product) {
        new ModelMapper().map(product, ProductEntity.class);
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
