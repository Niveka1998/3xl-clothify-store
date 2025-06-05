package service.custom;

import dto.Employee;
import service.SuperService;

import java.util.List;

public interface EmployeeService extends SuperService {
    Boolean addEmployee(Employee employee);
    Boolean updateEmployee(Employee employee);
    Boolean deleteEmployee(int id);
    Employee searchEmployeeById(int id);
    List<Employee> getAll();
}
