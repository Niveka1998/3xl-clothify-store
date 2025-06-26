package service.custom.impl;

import dto.Product;
import service.custom.ProductService;
import util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductServiceImpl implements ProductService {

    @Override
    public Boolean addProduct(Product product) {
        try {
            return CrudUtil.execute(
                    "INSERT INTO products (id, product_name, size, price, qty, img_url) VALUES (?, ?, ?, ?, ?, ?)",
                    product.getId(),
                    product.getName(),
                    product.getSize(),
                    product.getPrice(),
                    product.getQuantity(),
                    product.getImage_url()
            );
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Boolean updateProduct(Product product) {
        try {
            return CrudUtil.execute(
                    "UPDATE products SET product_name = ?, size = ?, price = ?, qty = ?, img_url = ? WHERE id = ?",
                    product.getName(),
                    product.getSize(),
                    product.getPrice(),
                    product.getQuantity(),
                    product.getImage_url(),
                    product.getId()
            );
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Boolean deleteProduct(int id) {
        try {
            return CrudUtil.execute("DELETE FROM products WHERE id = ?", id);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Product searchProductById(int id) throws SQLException {
        ResultSet resultSet = CrudUtil.execute("SELECT * FROM products WHERE id = ?", id);
        if (resultSet.next()) {
            return new Product(
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
    }

    @Override
    public List<Product> getAll() {
        List<Product> products = new ArrayList<>();
        try {
            ResultSet rs = CrudUtil.execute("SELECT * FROM products");
            while (rs.next()) {
                products.add(new Product(
                        rs.getInt("id"),
                        rs.getString("product_name"),
                        rs.getString("size"),
                        rs.getDouble("price"),
                        rs.getInt("qty"),
                        rs.getString("img_url")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }
}
