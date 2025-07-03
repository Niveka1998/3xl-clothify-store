package service;

import service.custom.impl.EmployeeServiceImpl;
import service.custom.impl.ProductServiceImpl;
import service.custom.impl.SupplierServiceImpl;
import util.ServiceType;

public class BoFactory {
    private static BoFactory instance;
    private BoFactory(){}

    public static BoFactory getInstance() {
        return instance ==  null ? instance= new BoFactory(): instance;
    }
    public <T extends SuperService>T getServiceType(ServiceType type){
        switch (type){
            case PRODUCT : return (T) new ProductServiceImpl();
            case EMPLOYEE: return (T) new EmployeeServiceImpl();
            case SUPPLIER: return (T) new SupplierServiceImpl();
        }
        return null;
    }
}
