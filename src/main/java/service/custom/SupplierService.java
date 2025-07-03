package service.custom;

import dto.Product;
import dto.Supplier;
import service.SuperService;

import java.sql.SQLException;
import java.util.List;

public interface SupplierService extends SuperService {
    Boolean addSupplier(Supplier supplier);
    Boolean updateSupplier(Supplier supplier);
    Boolean deleteSupplier(int id);
    Supplier searchSupplierId(int id) throws SQLException;
    List<Supplier> getAll();
}
