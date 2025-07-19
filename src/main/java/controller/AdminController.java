package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import dto.Employee;
import dto.Order;
import dto.Product;
import dto.Supplier;
import jakarta.inject.Inject;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import repository.DAOFactory;
import service.BoFactory;
import service.custom.EmployeeService;
import service.custom.ProductService;
import service.custom.SupplierService;
import service.custom.impl.EmployeeServiceImpl;
import service.custom.impl.ProductServiceImpl;
import service.custom.impl.SupplierServiceImpl;
import util.CrudUtil;
import util.ServiceType;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class AdminController implements Initializable {

    public JFXTextField txtEmployeeId;
    @FXML
    public TableColumn colEmployeeId;
    public JFXTextField txtSupplierItem;
    public JFXTextField txtSupplierId;
    public JFXButton btnSearchSupplier;
    public JFXTextField txtSupplierEmail;
    public JFXTextField txtSupplierCompany;
    public JFXTextField txtSupplierName;
    public JFXButton btnEditSupplier;
    public JFXButton btnRemoveSupplier;
    public JFXButton btnAddSupplier;
    public TableColumn colSupplierItem;
    public TableColumn colSupplierCompany;
    public TableColumn colSupplierEmail;
    public TableColumn colSupplierName;
    public TableColumn colSupplierId;
    public TableView tblSupplier;
    public JFXTextField txtProductCategory;
    public JFXButton btnAddNewStock;
    public TableColumn colCategory;
    public TableColumn colSupplier;
    public JFXTextField txtSupplier;
    public JFXComboBox cmbPaymentType;
    public JFXComboBox cmbSelectItem;
    public JFXTextField txtOrderId;
    public JFXTextField txtQuantity;
    public JFXButton btnReturnOrder;
    public JFXButton btnPlaceOrder;
    public TableView tblOrder;
    public TableColumn colOrderId;
    public TableColumn colTotalCost;
    public TableColumn colEmpId;
    public TableColumn colEmpName;
    public TableColumn colItemList;
    public AnchorPane root2;

//    @Inject
//    SupplierService supplierService;

//    public void initialize(URL url, ResourceBundle resourceBundle) {
//        loadProductTable();
//        System.out.println("colProductId = " + colProductId);
//
//        loadEmployeeTable();
//        loadSupplierTable();
//        //loadProductsToComboBox();
//
//    }
    @FXML
    private JFXButton btnAddEmployee;

    @FXML
    private JFXButton btnAddProduct;

    @FXML
    private JFXButton btnEditEmployee;

    @FXML
    private JFXButton btnRemoveEmployee;

    @FXML
    private JFXButton btnRemoveProduct;

    @FXML
    private JFXButton btnSearchEmployee;

    @FXML
    private JFXButton btnSearchProduct;

    @FXML
    private TableColumn colEmployeeEmail;

    @FXML
    private TableColumn colEmployeeName;

    @FXML
    private TableColumn colEmployeePassword;

    @FXML
    private TableColumn colImgUrl;

    @FXML
    private TableColumn colPrice;

    @FXML
    public TableColumn colProductId;

    @FXML
    private TableColumn colProductName;

    @FXML
    private TableColumn colQty;

    @FXML
    private TableColumn colSize;

    @FXML
    private TableView tblEmployee;

    @FXML
    private TableView tblProduct;

    @FXML
    private JFXButton txtEditProduct;

    @FXML
    private JFXTextField txtEmployeeEmail;

    @FXML
    private JFXTextField txtEmployeeName;

    @FXML
    private JFXTextField txtEmployeePassword;

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

    EmployeeService employeeService = BoFactory.getInstance().getServiceType(ServiceType.EMPLOYEE);
    ProductService productService = BoFactory.getInstance().getServiceType(ServiceType.PRODUCT);
    SupplierService supplierService = BoFactory.getInstance().getServiceType(ServiceType.SUPPLIER);



    public boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        return email.matches(emailRegex);
    }
    private void clearEmployeeFields() {
        txtEmployeeId.clear();
        txtEmployeeName.clear();
        txtEmployeePassword.clear();
        txtEmployeeEmail.clear();
        tblEmployee.getSelectionModel().clearSelection();
    }

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

    public void populateEmployeeFields(Employee employee) {
        txtEmployeeId.setText(String.valueOf(employee.getId()));
        txtEmployeeName.setText(employee.getName());
        txtEmployeeEmail.setText(employee.getEmail());
        txtEmployeePassword.setText(String.valueOf(employee.getPassword()));
//        txtEmployeeId.setEditable(false);
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

    @FXML
    void btnAddEmployeeOnClick(ActionEvent event) {
        String id_text = txtEmployeeId.getText();
        String employee_name = txtEmployeeName.getText();
        String email = txtEmployeeEmail.getText();
        String password = txtEmployeePassword.getText();

        if(employee_name.isEmpty() || email.isEmpty() || password.isEmpty()){
            new Alert(Alert.AlertType.ERROR,"Please fill all the fields first!").show();
            return;
        }
        if (!password.matches("^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$")) {
            new Alert(Alert.AlertType.ERROR, "Password must be at least 8 characters, include a letter, a number, and a symbol!").show();
            return;
        }

        if (!isValidEmail(email)) {
            new Alert(Alert.AlertType.ERROR, "Enter a valid email address").show();
            return;
        }

        try {
            int id = Integer.parseInt(txtEmployeeId.getText());
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
            int id = Integer.parseInt(txtEmployeeId.getText());
            Employee employee = employeeService.searchEmployeeById(id);
            if (employee!=null) {
                new Alert(Alert.AlertType.ERROR, "Employee with ID " + id + " already exists.").show();
                return;
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Error checking for existing employee: " + e.getMessage()).show();
            e.printStackTrace(); // Log the error
            return;
        }

        int id = Integer.parseInt(txtEmployeeId.getText());
        Employee employee = new Employee(id,employee_name,email,password);
        Boolean b = employeeService.addEmployee(employee);
        //Boolean isAdded= CrudUtil.execute("INSERT INTO employee(name,email,employee_password) VALUES(?,?,?)",employee_name,email,password);
        if(b != null && b){
            new Alert(Alert.AlertType.INFORMATION,"New employee added successfully!").show();
            loadEmployeeTable();
            clearEmployeeFields();

        }else {
            new Alert(Alert.AlertType.ERROR, "Error adding new employee!").show();
        }
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
    void btnEditEmployeeOnClick(ActionEvent event) {
        String employee_name = txtEmployeeName.getText();
        String email = txtEmployeeEmail.getText();
        String password = txtEmployeePassword.getText();
        String id_text = txtEmployeeId.getText();

        if(employee_name.isEmpty() || email.isEmpty() || password.isEmpty()){
            new Alert(Alert.AlertType.ERROR,"Please fill all the fields first!").show();
            return;
        }
        if (!password.matches("^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$")) {
            new Alert(Alert.AlertType.ERROR, "Password must be at least 8 characters, include a letter, a number, and a symbol!").show();
            return;
        }

        if (!isValidEmail(email)) {
            new Alert(Alert.AlertType.ERROR, "Enter a valid email address").show();
            return;
        }

        try {
            int id= Integer.parseInt(txtEmployeeId.getText());
            if(id<=0){
                new Alert(Alert.AlertType.ERROR,"ID must be a positive number!").show();
                return;
            }
        }catch (NumberFormatException e){
            new Alert(Alert.AlertType.ERROR,"Invalid ID. Please enter a positive number.").show();
            return;
        }
        int id = Integer.parseInt(txtEmployeeId.getText());
        Employee employee = new Employee(id,employee_name,email,password);
        Boolean b = employeeService.updateEmployee(employee);
        if(b != null && b){
            new Alert(Alert.AlertType.INFORMATION,"Employee updated successfully!").show();
            loadEmployeeTable();
            clearEmployeeFields();

        }else {
            new Alert(Alert.AlertType.ERROR, "Error updating employee!").show();
        }

        loadEmployeeTable();
        clearEmployeeFields();

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
    void btnRemoveEmployeeOnClick(ActionEvent event) {
        String id= txtEmployeeId.getText();
        try {
            Boolean isDeleted = CrudUtil.execute("DELETE FROM employee WHERE id =? ",id);

            if(isDeleted){
                new Alert(Alert.AlertType.INFORMATION,"Employee removed from the system!").show();
            }else{
                new Alert(Alert.AlertType.ERROR, "Error deleting employee!").show();
            }
            loadEmployeeTable();
            clearEmployeeFields();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

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
    void btnSearchEmployeeOnClick(ActionEvent event) {
        try {
            int id =Integer.parseInt(txtEmployeeId.getText());
            if(id<=0){
                new Alert(Alert.AlertType.ERROR,"ID must be a positive number!").show();
                return;
            }
        }catch (NumberFormatException e){
            new Alert(Alert.AlertType.ERROR,"Invalid ID. Please enter a positive number.").show();
            return;
        }

        try {
            int id = Integer.parseInt(txtEmployeeId.getText());
            EmployeeService employeeService = new EmployeeServiceImpl();

            Employee employee = employeeService.searchEmployeeById(id);

            populateEmployeeFields(employee);


        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR,"Employee not found !");
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

    List<Product> productList=  new ArrayList<>();
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

    List<Employee> employeeList = new ArrayList<>();
    private void loadEmployeeTable(){
        employeeList.clear();
        colEmployeeId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colEmployeeName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colEmployeeEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colEmployeePassword.setCellValueFactory(new PropertyValueFactory<>("password"));

        try {
            ResultSet resultSet = CrudUtil.execute("SELECT * FROM employee");
            while (resultSet.next()){
                employeeList.add(new Employee(resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4)));

            }
            ObservableList<Employee> employeeObservableList = FXCollections.observableArrayList();
                employeeList.forEach(employee -> employeeObservableList.add(employee));

                tblEmployee.getItems().clear();
                tblEmployee.setItems(employeeObservableList);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


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

//            SupplierService supplierService1 = new SupplierServiceImpl();
//            Supplier supplier= supplierService1.searchSupplierId(id);

            Supplier supplier = supplierService.searchSupplierId(id);


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

    private final List<Product> currentOrderItems = new ArrayList<>();
    @FXML
    public void btnAddItemToOrderOnClick(ActionEvent actionEvent) {

    }



    public void btnPlaceOrderOnClick(ActionEvent actionEvent) {
    }

    public void btnReturnOrderOnClick(ActionEvent actionEvent) {
    }

    private void loadProductsToComboBox() {

    }


    public void btnEmployeeTblOnClick(ActionEvent actionEvent) throws IOException {
        URL resource = this.getClass().getResource("/view/employee-table.fxml");
        assert resource != null;

        Parent load = FXMLLoader.load(resource);
        this.root2.getChildren().clear();
        this.root2.getChildren().add(load);
    }

    public void btnProductTblOnClick(ActionEvent actionEvent) throws IOException {
        URL resource = this.getClass().getResource("/view/product-table.fxml");
        assert resource != null;

        Parent load = FXMLLoader.load(resource);
        this.root2.getChildren().clear();
        this.root2.getChildren().add(load);
    }

    public void btnSupplierTblOnClick(ActionEvent actionEvent) throws IOException {
        URL resource = this.getClass().getResource("/view/supplier-table.fxml");
        assert resource != null;

        Parent load = FXMLLoader.load(resource);
        this.root2.getChildren().clear();
        this.root2.getChildren().add(load);
    }

    public void btnOrderTblOnClick(ActionEvent actionEvent) throws IOException {
        URL resource = this.getClass().getResource("/view/order-table.fxml");
        assert resource != null;

        Parent load = FXMLLoader.load(resource);
        this.root2.getChildren().clear();
        this.root2.getChildren().add(load);
    }

    public void btnAdminReportsOnClick(ActionEvent actionEvent) {

    }

    public void initialize(URL url, ResourceBundle resourceBundle) {
        //loadProductTable();
        System.out.println("colProductId = " + colProductId);

        //loadEmployeeTable();
        //loadSupplierTable();
        //loadProductsToComboBox();

    }
}
