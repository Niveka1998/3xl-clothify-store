package repository;

import repository.custom.impl.EmployeeRepositoryImpl;
import repository.custom.impl.ProductRepositoryImpl;
import util.RepositoryType;

public class DAOFactory {
    private static DAOFactory instance;

    private DAOFactory(){

    }

    public static DAOFactory getInstance() {
        return instance == null ? instance = new DAOFactory() : instance;
    }

    public <T extends SuperRepository>T getRepositoryType(RepositoryType type){
        switch (type){
            case EMPLOYEE: return (T) new EmployeeRepositoryImpl(); //type casting
            case PRODUCT: return (T) new ProductRepositoryImpl();

        }
        return null;
    }
}
