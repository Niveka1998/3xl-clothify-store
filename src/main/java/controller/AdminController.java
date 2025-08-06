package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import db.DBConnection;
import dto.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;
import service.BoFactory;
import service.custom.*;
import service.custom.impl.EmployeeServiceImpl;
import service.custom.impl.ProductServiceImpl;
import util.CrudUtil;
import util.ServiceType;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class AdminController implements Initializable {

    @FXML
    private JFXTextField txtEmployeePassword;
    @FXML
    private JFXButton btnAddEmployee;

    @FXML
    private JFXButton btnEditEmployee;

    @FXML
    private JFXButton btnRemoveEmployee;

    @FXML
    private JFXButton btnSearchEmployee;

    @FXML
    private TableColumn<?, ?> colEmployeeEmail;

    @FXML
    private TableColumn<?, ?> colEmployeeId;

    @FXML
    private TableColumn<?, ?> colEmployeeName;

    @FXML
    private TableColumn<?, ?> colEmployeePassword;

    @FXML
    private TableView<Employee> tblEmployee;


    @FXML
    private JFXTextField txtEmployeeId;

    @FXML
    private JFXTextField txtEmployeeName;


    @FXML
    private AnchorPane root2;
    @FXML
    private JFXButton btnCreateEmployeeAccount;

    @FXML
    private JFXButton btnEmployeeLogin;

    @FXML
    private Hyperlink linkForgotPwdEmpl;

    @FXML
    private JFXTextField txtEmployeeEmail;

    @FXML
    private AnchorPane root;
    @FXML
    private JFXButton btnAddToCart;

    @FXML
    private JFXButton btnPlaceOrder;

    @FXML
    private JFXButton btnReturnOrder;

    @FXML
    private JFXComboBox cmbSelectItem;

    @FXML
    private JFXComboBox cmbUserId;

    @FXML private TableColumn<CartTM, String> colItemCode;
    @FXML private TableColumn<CartTM, Integer> colItemQty;
    @FXML private TableColumn<CartTM, Double> colUnitPrice;
    @FXML private TableColumn<CartTM, Double> colTotalPrice;

    @FXML
    private TableColumn<CartTM, String> colUserName;

    @FXML
    private Label lblNetTotal;

    @FXML
    private TableView tblOrder;

    @FXML
    private JFXTextField txtOrderId;

    @FXML
    private JFXTextField txtQuantity;

    @FXML
    private JFXTextField txtStock;

    @FXML
    private JFXTextField txtUnitPrice;

    @FXML
    private JFXTextField txtUserName;
    @FXML
    private JFXButton btnAddNewStock;

    @FXML
    private JFXButton btnAddProduct;

    @FXML
    private JFXButton btnReduceStock;

    @FXML
    private JFXButton btnRemoveProduct;

    @FXML
    private JFXButton btnSearchProduct;

    @FXML
    private TableColumn<Product, String> colCategory;

    @FXML
    private TableColumn<Product, Double> colPrice;

    @FXML
    private TableColumn<Product, Integer> colProductId;

    @FXML
    private TableColumn<Product, String> colProductName;

    @FXML
    private TableColumn<Product, Integer> colQty;

    @FXML
    private TableColumn<Product, String> colSize;

    @FXML
    private TableColumn<Product, String> colSupplier;

    @FXML
    private TableView<Product> tblProduct;

    @FXML
    private JFXButton txtEditProduct;

    @FXML
    private JFXTextField txtPrice;

    @FXML
    private JFXTextField txtProductCategory;

    @FXML
    private JFXTextField txtProductId;

    @FXML
    private JFXTextField txtProductName;

    @FXML
    private JFXTextField txtQty;

    @FXML
    private JFXTextField txtSize;

    @FXML
    private JFXTextField txtSupplier;
    @FXML
    private JFXButton btnAddSupplier;

    @FXML
    private JFXButton btnEditSupplier;

    @FXML
    private JFXButton btnRemoveSupplier;

    @FXML
    private JFXButton btnSearchSupplier;

    @FXML
    private TableColumn<Supplier, String> colSupplierCompany;

    @FXML
    private TableColumn<Supplier, String> colSupplierEmail;

    @FXML
    private TableColumn<Supplier, Integer> colSupplierId;

    @FXML
    private TableColumn<Supplier, String> colSupplierItem;

    @FXML
    private TableColumn<Supplier, String> colSupplierName;

    @FXML
    private TableView<Supplier> tblSupplier;

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
    @FXML
    private TextField txtEmail;

    @FXML
    private PasswordField txtNewPassword;

    EmployeeService employeeService = BoFactory.getInstance().getServiceType(ServiceType.EMPLOYEE);
    ProductService productService = BoFactory.getInstance().getServiceType(ServiceType.PRODUCT);
    SupplierService supplierService = BoFactory.getInstance().getServiceType(ServiceType.SUPPLIER);
    UserService userService = BoFactory.getInstance().getServiceType(ServiceType.USER);
    OrderService orderService= BoFactory.getInstance().getServiceType(ServiceType.ORDER);


    public boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        return email.matches(emailRegex);
    }
    //clear fields
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

    //populate fields
    public void populateEmployeeFields(Employee employee) {
        if (employee == null) {
            new Alert(Alert.AlertType.WARNING, "Employee not found").show();
            return;
        }
        txtEmployeeId.setText(String.valueOf(employee.getId()));
        txtEmployeeName.setText(employee.getName());
        txtEmployeeEmail.setText(employee.getEmail());
        txtEmployeePassword.setText(String.valueOf(employee.getPassword()));
//        txtEmployeeId.setEditable(false);
    }

    public void populateProductFields(Product product){
        if (product == null) {
            new Alert(Alert.AlertType.WARNING, "Product not found").show();
            return;
        }
        txtProductId.setText(String.valueOf(product.getId()));
        txtProductName.setText(product.getName());
        txtSize.setText(product.getSize());
        txtPrice.setText(String.valueOf(product.getPrice()));
        txtQty.setText(String.valueOf(product.getQuantity()));
        txtProductCategory.setText(product.getCategory());
        txtSupplier.setText(product.getSupplier());
        System.out.println(product);
//        txtProductId.setEditable(false);
    }

    public void populateSupplierFields(Supplier supplier){
        if (supplier == null) {
            new Alert(Alert.AlertType.WARNING, "Employee not found").show();
            return;
        }
        txtSupplierId.setText(String.valueOf(supplier.getId()));
        txtSupplierName.setText(supplier.getName());
        txtSupplierCompany.setText(supplier.getCompany());
        txtSupplierEmail.setText(supplier.getEmail());
        txtSupplierItem.setText(supplier.getItem());
        System.out.println(supplier);
    }

    //crud operations for employee, product, supplier

//  add
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
        if (!password.matches("^(?=.*[A-Za-z])(?=.*\\d)(?=.*[^A-Za-z\\d])[A-Za-z\\d\\W_]{8,}$")) {
            new Alert(Alert.AlertType.ERROR, "Password must be at least 8 characters, include a letter, a number, and a symbol!").show();
            return;
        }

        if (!isValidEmail(email)) {
            new Alert(Alert.AlertType.ERROR, "Enter a valid email address").show();
            return;
        }

        int id;
        try {
             id =Integer.parseInt(txtEmployeeId.getText());
            if(id<=0){
                new Alert(Alert.AlertType.ERROR,"ID must be a positive number!").show();
                return;
            }
        }catch (NumberFormatException e){
            new Alert(Alert.AlertType.ERROR,"Invalid ID. Please enter a positive number.").show();
            return;
        }

        try {
            id = Integer.parseInt(txtEmployeeId.getText());
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

        id = Integer.parseInt(txtEmployeeId.getText());
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

    public void btnAddSupplierOnClick(ActionEvent actionEvent) {
        String id_text = txtSupplierId.getText();
        String supplier_name = txtSupplierName.getText();
        String company = txtSupplierCompany.getText();
        String email = txtSupplierEmail.getText();
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

//    edit
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
        if (!password.matches("^(?=.*[A-Za-z])(?=.*\\\\d)(?=.*[^A-Za-z\\\\d])[A-Za-z\\\\d\\W_]{8,}$")) {
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

//    remove
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

//    search
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

            if (employee != null) {
                populateEmployeeFields(employee);
            } else {
                new Alert(Alert.AlertType.WARNING, "Employee not found!").show();
                clearEmployeeFields();
            }


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
                clearSupplierFields();
            }

        } catch (NumberFormatException e) {
            new Alert(Alert.AlertType.ERROR, "Invalid ID. Please enter a positive number.").show();
        } catch (SQLException e) {
            e.printStackTrace(); // Optional: Log the exception for debugging
            new Alert(Alert.AlertType.ERROR, "An error occurred while searching for the supplier.").show();
        }
    }

    //load tables
    List<Product> productList=  new ArrayList<>();
    private void loadProductTable() {
        // Add null checks for all table columns
        if (colProductId == null || colProductName == null || colSize == null ||
                colPrice == null || colQty == null || colSupplier == null || colCategory == null) {
            System.err.println("One or more table columns are null. Check FXML fx:id values.");
            System.err.println("colProductId: " + colProductId);
            System.err.println("colProductName: " + colProductName);
            System.err.println("colSize: " + colSize);
            System.err.println("colPrice: " + colPrice);
            System.err.println("colQty: " + colQty);
            System.err.println("colSupplier: " + colSupplier);
            System.err.println("colCategory: " + colCategory);
            return;
        }

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


    // add and reduce stock
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


    // load interfaces into admin interface

    public void btnEmployeeTblOnClick(ActionEvent actionEvent) throws IOException {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/employee-table.fxml"));
            Parent load = loader.load();

            Object controller = loader.getController();
            if (controller instanceof AdminController) {
                AdminController adminController = (AdminController) controller;
                adminController.loadEmployeeTable();
            }

            this.root2.getChildren().clear();
            this.root2.getChildren().add(load);
        } catch (IOException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Error loading employee table: " + e.getMessage()).show();
        }
    }

    public void btnProductTblOnClick(ActionEvent actionEvent) throws IOException {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/product-table.fxml"));
            Parent load = loader.load();

            Object controller = loader.getController();
            if (controller instanceof AdminController) {
                AdminController adminController = (AdminController) controller;
                adminController.loadProductTable();
            }

            this.root2.getChildren().clear();
            this.root2.getChildren().add(load);
        } catch (IOException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Error loading product table: " + e.getMessage()).show();
        }
    }

    public void btnSupplierTblOnClick(ActionEvent actionEvent) throws IOException {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/supplier-table.fxml"));
            Parent load = loader.load();

            Object controller = loader.getController();
            if (controller instanceof AdminController) {
                AdminController adminController = (AdminController) controller;
                adminController.loadSupplierTable();
            }

            this.root2.getChildren().clear();
            this.root2.getChildren().add(load);
        } catch (IOException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Error loading supplier table: " + e.getMessage()).show();
        }
    }


    //load order table
    List<CartTM> cartTMS = new ArrayList<>();
    public void btnOrderTblOnClick(ActionEvent actionEvent) throws IOException {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/order-table.fxml"));
            Parent load = loader.load();

            // Get the controller and load combo data
            Object controller = loader.getController();
            if (controller instanceof AdminController) {
                AdminController adminController = (AdminController) controller;

                // Now load the combo box data for this specific instance
                try {
                    adminController.loadUserIDs();
                    adminController.loadItemCodes();

                    // Set default selections if available
                    if (adminController.cmbUserId != null && !adminController.cmbUserId.getItems().isEmpty()) {
                        adminController.cmbUserId.getSelectionModel().selectFirst();
                    }
                    if (adminController.cmbSelectItem != null && !adminController.cmbSelectItem.getItems().isEmpty()) {
                        adminController.cmbSelectItem.getSelectionModel().selectFirst();
                    }

                } catch (SQLException e) {
                    new Alert(Alert.AlertType.ERROR, "Error loading data: " + e.getMessage()).show();
                }
            }

            this.root2.getChildren().clear();
            this.root2.getChildren().add(load);

        } catch (IOException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Error loading order table: " + e.getMessage()).show();
        }
    }

    private void loadUserIDs() throws SQLException {

        System.out.println("Attempting to load user IDs...");
        List<Integer> employeeIds = employeeService.getEmployeeIds();
        System.out.println("Retrieved IDs: " + employeeIds);

        if (employeeIds.isEmpty()) {
            System.out.println("No employee IDs found in database!");
        }

        ObservableList<Integer> idList = FXCollections.observableArrayList(employeeIds);
        cmbUserId.setItems(idList);
        System.out.println("Combo box items set: " + cmbUserId.getItems()); // Debug log
        }

    private void loadItemCodes() throws SQLException {
        List<Integer> allItemCodes = productService.getProductIds();
        cmbSelectItem.setItems(FXCollections.observableArrayList(allItemCodes));
    }

    public void btnAddToCartOnClick(ActionEvent actionEvent) {
        String itemCode = cmbSelectItem.getValue().toString();
        Integer qtyOnHand = Integer.parseInt(txtQuantity.getText());
        Double unitPrice = Double.parseDouble(txtUnitPrice.getText());
        Double total = qtyOnHand * unitPrice;
        String userName = txtUserName.getText();

        cartTMS.add(new CartTM(itemCode,qtyOnHand,unitPrice,total));
        System.out.println(cartTMS);
        tblOrder.setItems(FXCollections.observableArrayList(cartTMS));

        calNetTotal();
    }

    private void calNetTotal(){
        Double netTotal = 0.0;
        for(CartTM item : cartTMS){
            netTotal += item.getTotal();
        }

        lblNetTotal.setText(netTotal.toString());
    }

    public void setupComboBoxListeners() {
        if (cmbUserId != null) {
            cmbUserId.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
                if (newValue != null) {
                    setValuesToUserText(newValue.toString());
                }
            });
        }

        if (cmbSelectItem != null) {
            cmbSelectItem.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
                if (newValue != null) {
                    setValuesToItemText(newValue.toString());
                }
            });
        }
    }

    private void setValuesToUserText(String employeeId) {
        try {
            int id = Integer.parseInt(employeeId);
            Employee employee = employeeService.searchEmployeeById(id);

            if (employee != null) {
                txtUserName.setText(employee.getName());
            } else {
                txtUserName.setText("Employee not found");
            }

        } catch (NumberFormatException e) {
            new Alert(Alert.AlertType.ERROR, "Invalid employee ID format").show();
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Error loading employee: " + e.getMessage()).show();
            e.printStackTrace();
        }
    }

    private void setValuesToItemText(String id) {
        try {
            int productId = Integer.parseInt(id);
            Product product = productService.searchProductById(productId);

            if (product != null) {
                txtStock.setText(String.valueOf(product.getQuantity()));
                txtUnitPrice.setText(String.valueOf(product.getPrice()));
            } else {
                txtStock.setText("0");
                txtUnitPrice.setText("0.0");
                new Alert(Alert.AlertType.WARNING, "Product not found").show();
            }

        } catch (NumberFormatException e) {
            new Alert(Alert.AlertType.ERROR, "Invalid product ID format").show();
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Error loading product: " + e.getMessage()).show();
            e.printStackTrace();
        }
    }

    public void btnPlaceOrder(ActionEvent event) throws  SQLException{
        int userId = Integer.parseInt(cmbUserId.getValue().toString());
        int orderId = Integer.parseInt(txtOrderId.getText());
        ArrayList<OrderDetails> orderDetailsArrayList = new ArrayList<>();
        cartTMS.forEach(cart -> orderDetailsArrayList.add(
                new OrderDetails(
                        cart.getOrderId(),
                        cart.getItemCode(),
                        cart.getQtyOnHand(),
                        cart.getUnitPrice())
        ));

        Order o1 = new Order(orderId,userId,orderDetailsArrayList);
        System.out.println(o1);

        orderService.placeOrder(o1);
    }

    //report generation
    public void btnGetProductReportOnClick(ActionEvent actionEvent) {
        try {
            JasperDesign design = JRXmlLoader.load("src/main/resources/report/inventory-report.jrxml");
            JasperReport report = JasperCompileManager.compileReport(design);
            JasperPrint jasperPrint = JasperFillManager.fillReport(report, null, DBConnection.getInstance().getConnection());
            JasperExportManager.exportReportToPdfFile(jasperPrint, "inventory-report.pdf");
            JasperViewer.viewReport(jasperPrint, false);
        } catch (JRException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void btnGenerateSuppReport(ActionEvent actionEvent) {
        try {
            JasperDesign design = JRXmlLoader.load("src/main/resources/report/supplier-report.jrxml");
            JasperReport report = JasperCompileManager.compileReport(design);
            JasperPrint jasperPrint = JasperFillManager.fillReport(report, null, DBConnection.getInstance().getConnection());
            JasperExportManager.exportReportToPdfFile(jasperPrint, "supplier-report.pdf");
            JasperViewer.viewReport(jasperPrint, false);
        } catch (JRException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void btnGenerateEmplReport(ActionEvent actionEvent) {
        try {
            JasperDesign design = JRXmlLoader.load("src/main/resources/report/employee-report.jrxml");
            JasperReport report = JasperCompileManager.compileReport(design);
            JasperPrint jasperPrint = JasperFillManager.fillReport(report, null, DBConnection.getInstance().getConnection());
            JasperExportManager.exportReportToPdfFile(jasperPrint, "employee-report.pdf");
            JasperViewer.viewReport(jasperPrint, false);
        } catch (JRException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


//initialize
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("AdminController initialized");
        //System.out.println("colProductId is: " + colProductId);
        if (colProductId != null && tblProduct != null) {
            loadProductTable();
        }

        if (colEmployeeId != null && tblEmployee != null) {
            loadEmployeeTable();
        }

        if (colSupplierId != null && tblSupplier != null) {
            loadSupplierTable();
        }

        if (cmbUserId != null && cmbSelectItem != null) {
            System.out.println("Setting up combo box listeners...");
            setupComboBoxListeners();

            try {
                loadUserIDs();
                loadItemCodes();

                // Set default selections if available
                if (!cmbUserId.getItems().isEmpty()) {
                    cmbUserId.getSelectionModel().selectFirst();
                }
                if (!cmbSelectItem.getItems().isEmpty()) {
                    cmbSelectItem.getSelectionModel().selectFirst();
                }

            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, "Error loading data: " + e.getMessage()).show();
            }
        } else {
            System.out.println("ComboBoxes not injected - this is normal for main admin interface");
        }

        colItemCode.setCellValueFactory(new PropertyValueFactory<>("itemCode"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qtyOnHand"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        colTotalPrice.setCellValueFactory(new PropertyValueFactory<>("total"));


    }
}
