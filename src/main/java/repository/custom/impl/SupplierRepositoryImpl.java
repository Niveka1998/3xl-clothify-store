package repository.custom.impl;

import entity.SupplierEntity;
import repository.SuperRepository;
import util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SupplierRepositoryImpl implements repository.custom.SupplierRepository,SuperRepository {
    @Override
    public Boolean add(SupplierEntity entity) {
        try {
            return CrudUtil.execute(
                    "INSERT INTO supplier (id,name,company,email,item) VALUES (?, ?, ?, ?, ?)",
                    entity.getId(),
                    entity.getName(),
                    entity.getCompany(),
                    entity.getEmail(),
                    entity.getItem()
            );
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Boolean update(SupplierEntity entity) {
        try {
            return CrudUtil.execute(
                    "UPDATE supplier SET name = ?, company = ?, item = ?, email = ? WHERE id = ?",
                    entity.getName(),
                    entity.getCompany(),
                    entity.getItem(),
                    entity.getEmail(),
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
            return CrudUtil.execute("DELETE FROM supplier WHERE id = ?", id);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public SupplierEntity searchById(Integer id) {
        try {
            ResultSet resultSet = CrudUtil.execute("SELECT * FROM supplier WHERE id = ?", id);
            if (resultSet.next()) {
                return new SupplierEntity(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("company"),
                        resultSet.getString("email"),
                        resultSet.getString("item")
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
    public List<SupplierEntity> getAll() {
        List<SupplierEntity> list = new ArrayList<>();
        try {
            ResultSet resultSet = CrudUtil.execute("SELECT * FROM supplier");
            while (resultSet.next()){
                list.add(new SupplierEntity(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("company"),
                        resultSet.getString("email"),
                        resultSet.getString("item")
                ));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return list;
    }
}
