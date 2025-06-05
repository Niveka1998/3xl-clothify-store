package repository.custom.impl;

import dto.Employee;
import entity.EmployeeEntity;
import repository.custom.EmployeeRepository;

import java.util.List;

public class EmployeeRepositoryImpl implements EmployeeRepository {
    @Override
    public Boolean addEmployee(EmployeeEntity employee) {
        return null;
    }

    @Override
    public Boolean updateEmployee(EmployeeEntity employee) {
        return null;
    }

    @Override
    public Boolean deleteEmployee(int id) {
        return null;
    }

    @Override
    public EmployeeEntity searchEmployeeById(int id) {
        return null;
    }

    @Override
    public List<EmployeeEntity> getAll() {
        return List.of();
    }
}
