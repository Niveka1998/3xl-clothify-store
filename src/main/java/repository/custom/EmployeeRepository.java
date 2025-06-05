package repository.custom;

import dto.Employee;
import entity.EmployeeEntity;

import java.util.List;

public interface EmployeeRepository {
    Boolean addEmployee(EmployeeEntity employee);
    Boolean updateEmployee(EmployeeEntity employee);
    Boolean deleteEmployee(int id);
    EmployeeEntity searchEmployeeById(int id);
    List<EmployeeEntity> getAll();
}
