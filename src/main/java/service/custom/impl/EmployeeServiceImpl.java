package service.custom.impl;

import dto.Employee;
import entity.EmployeeEntity;
import org.modelmapper.ModelMapper;
import repository.DAOFactory;
import repository.custom.EmployeeRepository;
import service.custom.EmployeeService;

import util.RepositoryType;

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
    public Employee searchEmployeeById(int id) {
        EmployeeEntity entity = repository.searchById(id);
        return (entity != null) ? mapper.map(entity, Employee.class) : null;
    }

    @Override
    public List<Employee> getAll() {
        List<EmployeeEntity> employeeEntities = repository.getAll();
        List<Employee> employeeList = new ArrayList<>();
        for(EmployeeEntity entity : employeeEntities){
            employeeList.add(mapper.map(entity, Employee.class));
        }
        return employeeList;
    }


}
