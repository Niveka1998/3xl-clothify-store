package service.custom.impl;


import dto.Supplier;
import entity.SupplierEntity;
import jakarta.inject.Inject;
import org.modelmapper.ModelMapper;
import repository.DAOFactory;
import repository.custom.SupplierRepository;
import service.custom.SupplierService;
import util.RepositoryType;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SupplierServiceImpl implements SupplierService {

//    @Inject
//    private SupplierRepository supplierRepository;
    SupplierRepository supplierRepository = DAOFactory.getInstance().getRepositoryType(RepositoryType.SUPPLIER);
    ModelMapper mapper = new ModelMapper();

    @Override
    public Boolean addSupplier(Supplier supplier) {
        SupplierEntity entity = mapper.map(supplier, SupplierEntity.class);
        return supplierRepository.add(entity);
    }

    @Override
    public Boolean updateSupplier(Supplier supplier) {
        SupplierEntity entity = mapper.map(supplier, SupplierEntity.class);
        return supplierRepository.update(entity);
    }

    @Override
    public Boolean deleteSupplier(int id) {
        return supplierRepository.delete(id);
    }

    @Override
    public Supplier searchSupplierId(int id) throws SQLException {
        SupplierEntity entity = supplierRepository.searchById(id);
        return (entity != null) ? mapper.map(entity, Supplier.class) : null;
    }

    @Override
    public List<Supplier> getAll() {
        List<SupplierEntity> supplierEntities = supplierRepository.getAll();
        List<Supplier> supplierList = new ArrayList<>();
        for(SupplierEntity entity: supplierEntities){
            supplierList.add(mapper.map(entity, Supplier.class));
        }
        return supplierList;
    }
}
