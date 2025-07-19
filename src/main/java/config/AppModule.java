package config;

import com.google.inject.AbstractModule;
import repository.custom.SupplierRepository;
import repository.custom.impl.SupplierRepositoryImpl;
import service.custom.SupplierService;
import service.custom.impl.SupplierServiceImpl;

public class AppModule extends AbstractModule {

    @Override
    protected void configure(){
        bind(SupplierService.class).to(SupplierServiceImpl.class);
        bind(SupplierRepository.class).to(SupplierRepositoryImpl.class);
    }
}
