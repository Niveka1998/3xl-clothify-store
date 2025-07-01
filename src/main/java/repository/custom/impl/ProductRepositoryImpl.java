package repository.custom.impl;


import entity.ProductEntity;
import repository.SuperRepository;
import repository.custom.ProductRepository;
import util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductRepositoryImpl implements ProductRepository, SuperRepository {

    @Override
    public Boolean add(ProductEntity entity) {
        try {
            return CrudUtil.execute(
                    "INSERT INTO products (id, product_name, size, price, qty, img_url) VALUES (?, ?, ?, ?, ?, ?)",
                    entity.getId(),
                    entity.getName(),
                    entity.getSize(),
                    entity.getPrice(),
                    entity.getQuantity(),
                    entity.getImage_url()
            );
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Boolean update(ProductEntity entity) {
        try {
            return CrudUtil.execute(
                    "UPDATE products SET product_name = ?, size = ?, price = ?, qty = ?, img_url = ? WHERE id = ?",
                    entity.getName(),
                    entity.getSize(),
                    entity.getPrice(),
                    entity.getQuantity(),
                    entity.getImage_url(),
                    entity.getId()
            );
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Boolean delete(Integer id) {
        try {
            return CrudUtil.execute("DELETE FROM products WHERE id = ?", id);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public ProductEntity searchById(Integer id) {
        try {
            ResultSet resultSet = CrudUtil.execute("SELECT * FROM products WHERE id = ?", id);
            if (resultSet.next()) {
                return new ProductEntity(
                        resultSet.getInt("id"),
                        resultSet.getString("product_name"),
                        resultSet.getString("size"),
                        resultSet.getDouble("price"),
                        resultSet.getInt("qty"),
                        resultSet.getString("img_url")
                );
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }


    @Override
    public List<ProductEntity> getAll() {
        List<ProductEntity> list = new ArrayList<>();
        try {
            ResultSet resultSet = CrudUtil.execute("SELECT * FROM products");
            while (resultSet.next()){
                list.add(new ProductEntity(
                        resultSet.getInt("id"),
                        resultSet.getString("product_name"),
                        resultSet.getString("size"),
                        resultSet.getDouble("price"),
                        resultSet.getInt("qty"),
                        resultSet.getString("img_url")
                ));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return list;
    }
}
