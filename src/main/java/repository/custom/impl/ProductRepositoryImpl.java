package repository.custom.impl;

import dto.Product;
import entity.ProductEntity;
import repository.SuperRepository;
import repository.custom.ProductRepository;

import java.util.List;

public class ProductRepositoryImpl implements ProductRepository, SuperRepository {

    @Override
    public Boolean add(ProductEntity entity) {
        return null;
    }

    @Override
    public Boolean update(ProductEntity entity) {
        return null;
    }

    @Override
    public Boolean delete(Integer integer) {
        return null;
    }

    @Override
    public ProductEntity searchById(Integer integer) {
        return null;
    }

    @Override
    public List<ProductEntity> getAll() {
        return List.of();
    }
}
