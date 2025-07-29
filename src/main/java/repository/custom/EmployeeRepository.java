package repository.custom;

import dto.Employee;
import entity.EmployeeEntity;
import repository.CrudRepository;

import java.sql.SQLException;
import java.util.List;

public interface EmployeeRepository extends CrudRepository<EmployeeEntity, Integer> {
    List<Integer> getEmployeeIds() throws SQLException;
}
//used to special functions of Employee (not CRUD)