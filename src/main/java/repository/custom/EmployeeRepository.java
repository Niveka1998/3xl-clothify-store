package repository.custom;

import dto.Employee;
import entity.EmployeeEntity;
import repository.CrudRepository;

import java.util.List;

public interface EmployeeRepository extends CrudRepository<EmployeeEntity, Integer> {

}
//used to special functions of Employee (not CRUD)