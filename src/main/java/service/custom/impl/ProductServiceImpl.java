package service.custom.impl;

import dto.Employee;
import dto.OrderDetails;
import dto.Product;
import entity.EmployeeEntity;
import entity.ProductEntity;
import org.modelmapper.ModelMapper;
import repository.DAOFactory;
import repository.custom.ProductRepository;
import repository.custom.impl.ProductRepositoryImpl;
import service.custom.ProductService;
import util.CrudUtil;
import util.RepositoryType;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductServiceImpl implements ProductService {

    ProductRepository repository = DAOFactory.getInstance().getRepositoryType(RepositoryType.PRODUCT);
    ModelMapper mapper = new ModelMapper();

    @Override
    public Boolean addProduct(Product product) {
        ProductEntity entity = mapper.map(product, ProductEntity.class);
        return repository.add(entity);
    }

    @Override
    public Boolean updateProduct(Product product) {
        ProductEntity entity = mapper.map(product, ProductEntity.class);
        return repository.update(entity);
    }

    @Override
    public Boolean deleteProduct(int id) {
        return repository.delete(id);
    }

    @Override
    public Product searchProductById(int id)  {
        ProductEntity entity = repository.searchById(id);
        return (entity != null) ? mapper.map(entity, Product.class) : null;
    }

    @Override
    public List<Product> getAll() {
        List<ProductEntity> productEntities = repository.getAll();
        List<Product> productList = new ArrayList<>();
        for(ProductEntity entity: productEntities){
            productList.add(mapper.map(entity, Product.class));
        }
        return productList;
    }
    private final ProductRepository productRepository = new ProductRepositoryImpl();

    @Override
    public boolean updateProductQuantity(int productId, int newQty) throws SQLException {
        return productRepository.updateQuantity(productId, newQty);
    }

    public Boolean updateStock(List<OrderDetails> orderDetails) throws SQLException {
        for (OrderDetails details : orderDetails) {
            Boolean isUpdate = updateStock(details);
            if (!isUpdate) {
                return false;
            }
        }
        return true;
    }

    public Boolean updateStock(OrderDetails orderDetails) throws SQLException {
        String sql = "UPDATE products SET available_stock = available_stock - ? WHERE id =?";
        return CrudUtil.execute(sql, orderDetails.getQty(), orderDetails.getItemCode());
    }

    @Override
    public List<Integer> getProductIds(){
        List<Product> all = getAll();
        ArrayList<Integer> productIdList = new ArrayList<>();
        all.forEach(product->{
            productIdList.add(product.getId());
        });
        return productIdList;
    }
}
