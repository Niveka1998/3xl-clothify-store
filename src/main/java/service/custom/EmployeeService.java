package service.custom;

import dto.Employee;
import service.SuperService;

import java.sql.SQLException;
import java.util.List;

public interface EmployeeService extends SuperService {
    Boolean addEmployee(Employee employee);
    Boolean updateEmployee(Employee employee);
    Boolean deleteEmployee(int id);
    Employee searchEmployeeById(int id) throws SQLException;
    List<Employee> getAll();
    List<Integer> getEmployeeIds();
}
