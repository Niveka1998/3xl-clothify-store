package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import dto.Product;
import dto.Supplier;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import service.BoFactory;
import service.custom.ProductService;
import service.custom.SupplierService;
import service.custom.impl.ProductServiceImpl;
import service.custom.impl.SupplierServiceImpl;
import util.CrudUtil;
import util.ServiceType;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class EmployeeController implements Initializable {

    public TableColumn colSupplier;
    public TableColumn colCategory;
    public JFXTextField txtProductCategory;
    public JFXTextField txtSupplier;
    @FXML
    private JFXButton btnAddProduct;

    @FXML
    private JFXButton btnRemoveProduct;

    @FXML
    private JFXButton btnSearchProduct;

    @FXML
    private TableColumn colImgUrl;

    @FXML
    private TableColumn colPrice;

    @FXML
    private TableColumn colProductId;

    @FXML
    private TableColumn colProductName;

    @FXML
    private TableColumn colQty;

    @FXML
    private TableColumn colSize;

    @FXML
    private TableView tblProduct;

    @FXML
    private JFXButton txtEditProduct;

    @FXML
    private JFXTextField txtPrice;

    @FXML
    private JFXTextField txtProductId;

    @FXML
    private JFXTextField txtProductImageUrl;

    @FXML
    private JFXTextField txtProductName;

    @FXML
    private JFXTextField txtQty;

    @FXML
    private JFXTextField txtSize;

    @FXML
    private JFXButton btnAddSupplier;

    @FXML
    private JFXButton btnEditSupplier;

    @FXML
    private JFXButton btnRemoveSupplier;

    @FXML
    private JFXButton btnSearchSupplier;

    @FXML
    private TableColumn colSupplierCompany;

    @FXML
    private TableColumn colSupplierEmail;

    @FXML
    private TableColumn colSupplierId;

    @FXML
    private TableColumn colSupplierItem;

    @FXML
    private TableColumn colSupplierName;

    @FXML
    private TableView tblSupplier;

    @FXML
    private JFXTextField txtSupplierCompany;

    @FXML
    private JFXTextField txtSupplierEmail;

    @FXML
    private JFXTextField txtSupplierId;

    @FXML
    private JFXTextField txtSupplierItem;

    @FXML
    private JFXTextField txtSupplierName;

    ProductService productService = BoFactory.getInstance().getServiceType(ServiceType.PRODUCT);
    SupplierService supplierService = BoFactory.getInstance().getServiceType(ServiceType.SUPPLIER);

    private void clearProductFields(){
        txtProductId.clear();
        txtProductName.clear();
        txtPrice.clear();
        txtSize.clear();
        txtSupplier.clear();
        txtProductCategory.clear();
        txtQty.clear();
        tblProduct.getSelectionModel().clearSelection();
    }

    private void clearSupplierFields(){
        txtSupplierId.clear();
        txtSupplierCompany.clear();
        txtSupplierEmail.clear();
        txtSupplierItem.clear();
        txtSupplierName.clear();
    }

    public void populateProductFields(Product product){
        txtProductId.setText(String.valueOf(product.getId()));
        txtProductName.setText(product.getName());
        txtSize.setText(product.getSize());
        txtPrice.setText(String.valueOf(product.getPrice()));
        txtQty.setText(String.valueOf(product.getQuantity()));
        txtProductCategory.setText(product.getCategory());
        txtSupplier.setText(product.getSupplier());
//        txtProductId.setEditable(false);
    }

    public void populateSupplierFields(Supplier supplier){
        txtSupplierId.setText(String.valueOf(supplier.getId()));
        txtSupplierName.setText(supplier.getName());
        txtSupplierItem.setText(supplier.getItem());
        txtSupplierCompany.setText(supplier.getCompany());
        txtSupplierEmail.setText(supplier.getEmail());
    }

    public boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        return email.matches(emailRegex);
    }
    @FXML
    void btnAddProductOnClick(ActionEvent event) {
        String id_text= txtProductId.getText();
        String product_name = txtProductName.getText();
        String size = txtSize.getText();
        String price_text = txtPrice.getText();
        String qty_text = txtQty.getText();
        String category = txtProductCategory.getText();
        String supplier = txtSupplier.getText();


        if(product_name.isEmpty() || size.isEmpty()|| price_text.isEmpty() ||qty_text.isEmpty()|| category.isEmpty() || supplier.isEmpty()){
            new Alert(Alert.AlertType.ERROR,"Please fill all the fields first!").show();
            return;
        }
        if(!size.equals("XS") && !size.equals("S")&& !size.equals("M")&& !size.equals("L")&& !size.equals("XL")&& !size.equals("2XL")&& !size.equals("3XL")&& !size.equals("4XL") && !size.equals("5XL")){
            new Alert(Alert.AlertType.ERROR,"Enter a valid size between XS and 5XL").show();
            return;
        }
        try {
            int id =Integer.parseInt(txtProductId.getText());
            if(id<=0){
                new Alert(Alert.AlertType.ERROR,"ID must be a positive number!").show();
                return;
            }
        }catch (NumberFormatException e){
            new Alert(Alert.AlertType.ERROR,"Invalid ID. Please enter a positive number.").show();
            return;
        }
        double price;
        try {
            price = Double.parseDouble(price_text);
            if(price<=0){
                new Alert(Alert.AlertType.ERROR,"Price must be a positive number!").show();
                return;
            }
        } catch (NumberFormatException e) {
            new Alert(Alert.AlertType.ERROR, "Invalid price format. Please enter a positive number.").show();
            return;
        }

        int qty;
        try {
            qty = Integer.parseInt(qty_text);
            if(qty<=0){
                new Alert(Alert.AlertType.ERROR,"Quantity must be a positive number!").show();
                return;
            }
        } catch (NumberFormatException e) {
            new Alert(Alert.AlertType.ERROR, "Invalid quantity format. Please enter a number.").show();
            return;
        }

        try {
            int id =Integer.parseInt(txtProductId.getText());
            Product product= productService.searchProductById(id);
            if (product!=null) {
                new Alert(Alert.AlertType.ERROR, "Product with ID " + id + " already exists.").show();
                return;
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Error checking for existing product: " + e.getMessage()).show();
            e.printStackTrace(); // Log the error
            return;
        }
        int id =Integer.parseInt(txtProductId.getText());
        Product product = new Product(id,product_name,size,price,qty,category,supplier);

        Boolean b = productService.addProduct(product);
//            Boolean isAdded = CrudUtil.execute("INSERT INTO products(product_name,size,price,qty,img_url) VALUES(?,?,?,?,?)", product_name, size, price, qty, img_url);
        if(b){
            new Alert(Alert.AlertType.INFORMATION,"New product added successfully!").show();
            loadProductTable();
            clearProductFields();
        }else {
            new Alert(Alert.AlertType.ERROR, "Error adding new product!").show();
        }
    }

    @FXML
    void btnEditProductOnClick(ActionEvent event) {
        String id_text= txtProductId.getText();
        String product_name = txtProductName.getText();
        String size = txtSize.getText();
        String price_text = txtPrice.getText();
        String qty_text = txtQty.getText();
        String category = txtProductCategory.getText();
        String supplier = txtSupplier.getText();

        if(product_name.isEmpty() || size.isEmpty()|| price_text.isEmpty() ||qty_text.isEmpty()|| category.isEmpty() ||supplier.isEmpty()){
            new Alert(Alert.AlertType.ERROR,"Please fill all the fields first!").show();
            return;
        }

        if(!size.equals("XS") && !size.equals("S")&& !size.equals("M")&& !size.equals("L")&& !size.equals("XL")&& !size.equals("2XL")&& !size.equals("3XL")&& !size.equals("4XL") && !size.equals("5XL")){
            new Alert(Alert.AlertType.ERROR,"Enter a valid size between XS and 5XL").show();
            return;
        }
        try {
            int id =Integer.parseInt(txtProductId.getText());
            if(id<=0){
                new Alert(Alert.AlertType.ERROR,"ID must be a positive number!").show();
                return;
            }
        }catch (NumberFormatException e){
            new Alert(Alert.AlertType.ERROR,"Invalid ID. Please enter a positive number.").show();
            return;
        }
        double price;
        try {
            price = Double.parseDouble(price_text);
            if(price<=0){
                new Alert(Alert.AlertType.ERROR,"Price must be a positive number!").show();
                return;
            }
        } catch (NumberFormatException e) {
            new Alert(Alert.AlertType.ERROR, "Invalid price format. Please enter a positive number.").show();
            return;
        }

        int qty;
        try {
            qty = Integer.parseInt(qty_text);
            if(qty<=0){
                new Alert(Alert.AlertType.ERROR,"Quantity must be a positive number!").show();
                return;
            }
        } catch (NumberFormatException e) {
            new Alert(Alert.AlertType.ERROR, "Invalid quantity format. Please enter a number.").show();
            return;
        }
        int id =Integer.parseInt(txtProductId.getText());
        Product product = new Product(id,product_name,size,price,qty,category,supplier);

        Boolean b = productService.updateProduct(product);
        if(b){
            new Alert(Alert.AlertType.INFORMATION,"Product updated successfully!").show();
            loadProductTable();
            clearProductFields();
        }else {
            new Alert(Alert.AlertType.ERROR, "Error updating product!").show();
        }

        clearProductFields();
        loadProductTable();


    }

    @FXML
    void btnRemoveProductOnClick(ActionEvent event) {
        String id = txtProductId.getText();
        try {
            Boolean isDeleted = CrudUtil.execute("DELETE FROM products WHERE id =? ",id);

            if(isDeleted){
                new Alert(Alert.AlertType.INFORMATION,"Product deleted from the system!").show();
            }else{
                new Alert(Alert.AlertType.ERROR, "Error deleting product!").show();
            }
            loadProductTable();
            clearProductFields();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    void btnSearchProductOnClick(ActionEvent event) {

        try {
            int id = Integer.parseInt(txtProductId.getText());
            if (id <= 0) {
                new Alert(Alert.AlertType.ERROR, "ID must be a positive number!").show();
                return;
            }

            ProductService productService = new ProductServiceImpl();
            Product product = productService.searchProductById(id);

            if (product != null) {
                populateProductFields(product);
            } else {
                new Alert(Alert.AlertType.WARNING, "Product not found!").show();
            }

        } catch (NumberFormatException e) {
            new Alert(Alert.AlertType.ERROR, "Invalid ID. Please enter a positive number.").show();
        } catch (SQLException e) {
            e.printStackTrace(); // Optional: Log the exception for debugging
            new Alert(Alert.AlertType.ERROR, "An error occurred while searching for the product.").show();
        }

    }

    List<Product> productList=  new ArrayList<>(); // arraylist to store products
    private void loadProductTable() {
        productList.clear();
        colProductId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colProductName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colSize.setCellValueFactory(new PropertyValueFactory<>("size"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        colSupplier.setCellValueFactory(new PropertyValueFactory<>("supplier"));
        colCategory.setCellValueFactory(new PropertyValueFactory<>("category"));

        try {

            ResultSet resultSet = CrudUtil.execute("SELECT * FROM products");
            while (resultSet.next()) {
                productList.add(new Product(resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getDouble(4),
                        resultSet.getInt(5),
                        resultSet.getString(6),
                        resultSet.getString(7)));

            }
            ObservableList<Product> productObservableList = FXCollections.observableArrayList();
            productList.forEach(product -> productObservableList.add(product));

            tblProduct.getItems().clear();
            tblProduct.setItems(productObservableList);


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        loadProductTable();
        loadSupplierTable();

    }

    List<Supplier> supplierList = new ArrayList<>();
    private void loadSupplierTable(){
        supplierList.clear();
        colSupplierId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colSupplierName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colSupplierCompany.setCellValueFactory(new PropertyValueFactory<>("company"));
        colSupplierEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colSupplierItem.setCellValueFactory(new PropertyValueFactory<>("item"));

        try {
            ResultSet resultSet = CrudUtil.execute("SELECT * FROM supplier");
            while (resultSet.next()){
                supplierList.add(new Supplier(resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5)));

            }
            ObservableList<Supplier> supplierObservableList = FXCollections.observableArrayList();
            supplierList.forEach(supplier->supplierObservableList.add(supplier));

            tblSupplier.getItems().clear();
            tblSupplier.setItems(supplierObservableList);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public void btnAddSupplierOnClick(ActionEvent actionEvent) {
        String id_text = txtSupplierId.getText();
        String supplier_name = txtSupplierName.getText();
        String email = txtSupplierEmail.getText();
        String company = txtSupplierCompany.getText();
        String item = txtSupplierItem.getText();

        if(supplier_name.isEmpty() || email.isEmpty() || company.isEmpty() || item.isEmpty()){
            new Alert(Alert.AlertType.ERROR,"Please fill all the fields first!").show();
            return;
        }

        if (!isValidEmail(email)) {
            new Alert(Alert.AlertType.ERROR, "Enter a valid email address").show();
            return;
        }

        try {
            int id = Integer.parseInt(txtSupplierId.getText());
            if(id<=0){
                new Alert(Alert.AlertType.ERROR,"ID must be a positive number!").show();
                return;
            }
        }catch (NumberFormatException e){
            new Alert(Alert.AlertType.ERROR,"Invalid ID. Please enter a positive number.").show();
            return;
        }
        // add duplicate id validation later
        try {
            int id = Integer.parseInt(txtSupplierId.getText());
            Supplier supplier = supplierService.searchSupplierId(id);
            if (supplier!=null) {
                new Alert(Alert.AlertType.ERROR, "Supplier with ID " + id + " already exists.").show();
                return;
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Error checking for existing supplier: " + e.getMessage()).show();
            e.printStackTrace(); // Log the error
            return;
        }

        int id = Integer.parseInt(txtSupplierId.getText());
        Supplier supplier = new Supplier(id,supplier_name,email,company,item);
        Boolean b = supplierService.addSupplier(supplier);
        //Boolean isAdded= CrudUtil.execute("INSERT INTO employee(name,email,employee_password) VALUES(?,?,?)",employee_name,email,password);
        if(b != null && b){
            new Alert(Alert.AlertType.INFORMATION,"New supplier added successfully!").show();
            clearSupplierFields();
            loadSupplierTable();

        }else {
            new Alert(Alert.AlertType.ERROR, "Error adding new supplier!").show();
        }
    }

    public void btnRemoveSupplierOnClick(ActionEvent actionEvent) {
        String id = txtSupplierId.getText();
        try {
            Boolean isDeleted = CrudUtil.execute("DELETE FROM supplier WHERE id =? ",id);

            if(isDeleted){
                new Alert(Alert.AlertType.INFORMATION,"Supplier deleted from the system!").show();
            }else{
                new Alert(Alert.AlertType.ERROR, "Error deleting supplier!").show();
            }
            loadSupplierTable();
            clearSupplierFields();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void btnEditSupplierOnClick(ActionEvent actionEvent) {
        String id_text = txtSupplierId.getText();
        String supplier_name = txtSupplierName.getText();
        String email = txtSupplierEmail.getText();
        String company = txtSupplierCompany.getText();
        String item = txtSupplierItem.getText();

        if(supplier_name.isEmpty() || email.isEmpty()|| company.isEmpty() ||item.isEmpty()){
            new Alert(Alert.AlertType.ERROR,"Please fill all the fields first!").show();
            return;
        }

        try {
            int id =Integer.parseInt(txtSupplierId.getText());
            if(id<=0){
                new Alert(Alert.AlertType.ERROR,"ID must be a positive number!").show();
                return;
            }
        }catch (NumberFormatException e){
            new Alert(Alert.AlertType.ERROR,"Invalid ID. Please enter a positive number.").show();
            return;
        }

        int id =Integer.parseInt(txtSupplierId.getText());
        Supplier supplier = new Supplier(id,supplier_name,company,email,item);

        Boolean b = supplierService.updateSupplier(supplier);
        if(b){
            new Alert(Alert.AlertType.INFORMATION,"Supplier updated successfully!").show();
            loadSupplierTable();
            clearProductFields();
        }else {
            new Alert(Alert.AlertType.ERROR, "Error updating supplier!").show();
        }

        clearSupplierFields();
        loadSupplierTable();

    }

    public void btnSearchSupplierOnClick(ActionEvent actionEvent) {
        try {
            int id = Integer.parseInt(txtSupplierId.getText());
            if (id <= 0) {
                new Alert(Alert.AlertType.ERROR, "ID must be a positive number!").show();
                return;
            }

            SupplierService supplierService1 = new SupplierServiceImpl();
            Supplier supplier= supplierService1.searchSupplierId(id);

            if (supplier != null) {
                populateSupplierFields(supplier);
            } else {
                new Alert(Alert.AlertType.WARNING, "Supplier not found!").show();
            }

        } catch (NumberFormatException e) {
            new Alert(Alert.AlertType.ERROR, "Invalid ID. Please enter a positive number.").show();
        } catch (SQLException e) {
            e.printStackTrace(); // Optional: Log the exception for debugging
            new Alert(Alert.AlertType.ERROR, "An error occurred while searching for the supplier.").show();
        }
    }


    public void btnAddNewStockOnClick(ActionEvent actionEvent) {
        Product selectedProduct = (Product) tblProduct.getSelectionModel().getSelectedItem();

        if (selectedProduct == null) {
            new Alert(Alert.AlertType.WARNING, "Please select a product to add stock.").show();
            return;
        }

        if (txtQty.getText().isEmpty() || !txtQty.getText().matches("\\d+")) {
            new Alert(Alert.AlertType.ERROR, "Please enter a valid quantity.").show();
            return;
        }

        int addedQty = Integer.parseInt(txtQty.getText());
        int currentQty = selectedProduct.getQuantity();  // from table column
        int newQty = currentQty + addedQty;

        try {
            // Update in DB
            boolean isUpdated = productService.updateProductQuantity(selectedProduct.getId(), newQty);

            if (isUpdated) {
                selectedProduct.setQuantity(newQty);
                tblProduct.refresh();

                new Alert(Alert.AlertType.INFORMATION, "Stock updated successfully!").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Failed to update stock.").show();
            }

        } catch (SQLException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Error: " + e.getMessage()).show();
        }
    }

    @FXML
    void btnReduceStockOnClick(ActionEvent event) {
        Product selectedProduct = (Product) tblProduct.getSelectionModel().getSelectedItem();

        if (selectedProduct == null) {
            new Alert(Alert.AlertType.WARNING, "Please select a product to reduce stock.").show();
            return;
        }

        if (txtQty.getText().isEmpty() || !txtQty.getText().matches("\\d+")) {
            new Alert(Alert.AlertType.ERROR, "Please enter a valid quantity to reduce.").show();
            return;
        }

        int reduceQty = Integer.parseInt(txtQty.getText());
        int currentQty = selectedProduct.getQuantity();

        if (reduceQty > currentQty) {
            new Alert(Alert.AlertType.ERROR, "Cannot reduce more than available stock.").show();
            return;
        }

        int newQty = currentQty - reduceQty;

        try {
            boolean isUpdated = productService.updateProductQuantity(selectedProduct.getId(), newQty);

            if (isUpdated) {
                selectedProduct.setQuantity(newQty);
                tblProduct.refresh(); // Update the UI
                new Alert(Alert.AlertType.INFORMATION, "Stock reduced successfully!").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Failed to update stock.").show();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Error: " + e.getMessage()).show();
        }
    }

}
