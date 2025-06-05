package service.custom.impl;

import dto.Employee;
import entity.EmployeeEntity;
import org.modelmapper.ModelMapper;
import repository.DAOFactory;
import repository.custom.EmployeeRepository;
import service.custom.EmployeeService;
import util.RepositoryType;


import java.util.List;

public class EmployeeServiceImpl implements EmployeeService {
    EmployeeRepository repository = DAOFactory.getInstance().getRepositoryType(RepositoryType.EMPLOYEE);

    @Override
    public Boolean addEmployee(Employee employee) {
        EmployeeEntity entity = new ModelMapper().map(employee, EmployeeEntity.class);
        return repository.addEmployee(entity);
    }

    @Override
    public Boolean updateEmployee(Employee employee) {
        return null;
    }

    @Override
    public Boolean deleteEmployee(int id) {
        return null;
    }

    @Override
    public Employee searchEmployeeById(int id) {
        return null;
    }

    @Override
    public List<Employee> getAll() {
        return List.of();
    }
}
