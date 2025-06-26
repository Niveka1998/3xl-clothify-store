package service.custom.impl;

import dto.Employee;
import entity.EmployeeEntity;
import org.modelmapper.ModelMapper;
import repository.DAOFactory;
import repository.custom.EmployeeRepository;
import service.custom.EmployeeService;
import util.CrudUtil;
import util.RepositoryType;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeServiceImpl implements EmployeeService {
    EmployeeRepository repository = DAOFactory.getInstance().getRepositoryType(RepositoryType.EMPLOYEE);
    ModelMapper mapper = new ModelMapper();

    @Override
    public Boolean addEmployee(Employee employee) {
        EmployeeEntity entity = mapper.map(employee, EmployeeEntity.class);
        return repository.add(entity);
    }

    @Override
    public Boolean updateEmployee(Employee employee) {
        EmployeeEntity entity = mapper.map(employee, EmployeeEntity.class);
        return repository.update(entity);
    }

    @Override
    public Boolean deleteEmployee(int id) {
        return repository.delete(id);
    }

    @Override
    public Employee searchEmployeeById(int id) throws SQLException {
        ResultSet resultSet = CrudUtil.execute("SELECT * FROM employee WHERE id = ?", id);

        if (resultSet.next()) {
            return new Employee(
                    resultSet.getInt("id"),
                    resultSet.getString("name"),
                    resultSet.getString("email"),
                    resultSet.getString("employee_password")
            );
        } else {
            return null;
        }
    }

    @Override
    public List<Employee> getAll() {
        List<Employee> employees = new ArrayList<>();
        try {
            ResultSet resultSet = CrudUtil.execute("SELECT * FROM employee");
            while (resultSet.next()) {
                employees.add(new Employee(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("email"),
                        resultSet.getString("employee_password")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employees;
    }
}
