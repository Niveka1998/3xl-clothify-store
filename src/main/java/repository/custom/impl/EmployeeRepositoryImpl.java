package repository.custom.impl;

import dto.Employee;
import entity.EmployeeEntity;
import repository.SuperRepository;
import repository.custom.EmployeeRepository;
import util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeRepositoryImpl implements EmployeeRepository, SuperRepository {

    @Override
    public Boolean add(EmployeeEntity entity) {
        try {
            return CrudUtil.execute(
                    "INSERT INTO employee (id, name, email, employee_password) VALUES (?, ?, ?, ?)",
                    entity.getId(),
                    entity.getName(),
                    entity.getEmail(),
                    entity.getPassword()
            );
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Boolean update(EmployeeEntity entity) {
        try {
            return CrudUtil.execute(
                    "UPDATE employee SET name = ?, email = ?, employee_password = ? WHERE id = ?",
                    entity.getName(),
                    entity.getEmail(),
                    entity.getPassword(),
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
            return CrudUtil.execute("DELETE FROM employee WHERE id = ?", id);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public EmployeeEntity searchById(Integer id) {
        try {
            ResultSet resultSet = CrudUtil.execute("SELECT * FROM employee WHERE id = ?", id);
            if (resultSet.next()) {
                return new EmployeeEntity(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("email"),
                        resultSet.getString("employee_password")
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
    public List<EmployeeEntity> getAll() {
        List<EmployeeEntity> list = new ArrayList<>();
        try {
            ResultSet resultSet = CrudUtil.execute("SELECT * FROM employee");
            while (resultSet.next()){
                list.add(new EmployeeEntity(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("email"),
                        resultSet.getString("employee_password")
                ));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return list;
    }
}
