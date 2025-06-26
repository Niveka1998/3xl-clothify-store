package repository.custom.impl;

import dto.Employee;
import entity.EmployeeEntity;
import repository.SuperRepository;
import repository.custom.EmployeeRepository;

import java.util.List;

public class EmployeeRepositoryImpl implements EmployeeRepository, SuperRepository {

    @Override
    public Boolean add(EmployeeEntity entity) {
        return null;
    }

    @Override
    public Boolean update(EmployeeEntity entity) {
        return null;
    }

    @Override
    public Boolean delete(Integer integer) {
        return null;
    }

    @Override
    public EmployeeEntity searchById(Integer integer) {
        return null;
    }

    @Override
    public List<EmployeeEntity> getAll() {
        return List.of();
    }
}
