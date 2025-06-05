package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import db.DBConnection;
import dto.Employee;
import dto.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import util.CrudUtil;

import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ClothifyController implements Initializable {

    public JFXTextField txtEmployeeId;
    public TableColumn colEmployeeId;

    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadProductTable();
        loadEmployeeTable();

    }
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
    private TableColumn colProductId;

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


    public boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        return email.matches(emailRegex);
    }



    @FXML
    void btnAddEmployeeOnClick(ActionEvent event) {
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

        // add duplicate id validation later


        try {
            Boolean isAdded= CrudUtil.execute("INSERT INTO employee(name,email,employee_password) VALUES(?,?,?)",employee_name,email,password);
            if(isAdded){
                new Alert(Alert.AlertType.INFORMATION,"New employee added successfully!").show();
            }else {
                new Alert(Alert.AlertType.ERROR, "Error adding new employee!").show();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        loadEmployeeTable();
    }

    @FXML
    void btnAddProductOnClick(ActionEvent event) {
        String product_name = txtProductName.getText();
        String size = txtSize.getText();
        String price_text = txtPrice.getText();
        String qty_text = txtQty.getText();
        String img_url = txtProductImageUrl.getText();

        if(product_name.isEmpty() || size.isEmpty()|| price_text.isEmpty() ||qty_text.isEmpty()|| img_url.isEmpty()){
            new Alert(Alert.AlertType.ERROR,"Please fill all the fields first!").show();
            return;
        }
        if(!size.equals("XS") && !size.equals("S")&& !size.equals("M")&& !size.equals("L")&& !size.equals("XL")&& !size.equals("2XL")&& !size.equals("3XL")&& !size.equals("4XL") && !size.equals("5XL")){
            new Alert(Alert.AlertType.ERROR,"Enter a valid size between XS and 5XL").show();
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
            new Alert(Alert.AlertType.ERROR, "Invalid price format. Please enter a number.").show();
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
            Boolean isAdded = CrudUtil.execute("INSERT INTO products(product_name,size,price,qty,img_url) VALUES(?,?,?,?,?)", product_name, size, price, qty, img_url);
            if(isAdded){
                new Alert(Alert.AlertType.INFORMATION,"New product added successfully!").show();
            }else {
                new Alert(Alert.AlertType.ERROR, "Error adding new product!").show();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        loadProductTable();
    }
    @FXML
    void btnEditEmployeeOnClick(ActionEvent event) {
        String employee_name = txtEmployeeName.getText();
        String email = txtEmployeeEmail.getText();
        String password = txtEmployeePassword.getText();
        int id = Integer.parseInt(txtEmployeeId.getText());
        try {
            Boolean isUpdated = CrudUtil.execute("UPDATE employee SET name=?, email=?, employee_password=? WHERE id=?", employee_name, email, password, id);


            if (isUpdated) {
                new Alert(Alert.AlertType.INFORMATION, "Employee Updated!").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Employee not updated!").show();
            }

            loadEmployeeTable();

        } catch (SQLException e) {
            e.printStackTrace(); // Show full error in console for debugging
            throw new RuntimeException(e);
        }

    }

    @FXML
    void btnEditProductOnClick(ActionEvent event) {
        String id= txtProductId.getText();
        String product_name = txtProductName.getText();
        String size = txtSize.getText();
        String price_text = txtPrice.getText();
        String qty_text = txtQty.getText();
        String img_url = txtProductImageUrl.getText();

        if(product_name.isEmpty() || size.isEmpty()|| price_text.isEmpty() ||qty_text.isEmpty()|| img_url.isEmpty()){
            new Alert(Alert.AlertType.ERROR,"Please fill all the fields first!").show();
            return;
        }

        if(!size.equals("XS") && !size.equals("S")&& !size.equals("M")&& !size.equals("L")&& !size.equals("XL")&& !size.equals("2XL")&& !size.equals("3XL")&& !size.equals("4XL") && !size.equals("5XL")){
            new Alert(Alert.AlertType.ERROR,"Enter a valid size between XS and 5XL").show();
            return;
        }
        try {
            Boolean isUpdated = CrudUtil.execute("UPDATE products SET product_name=?, size=?, price=?, qty=?,img_url=? WHERE id=?", product_name, size, price_text, qty_text ,img_url,id);


            if (isUpdated) {
                new Alert(Alert.AlertType.INFORMATION, "Product Updated!").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Product not updated!").show();
            }

            loadProductTable();

        } catch (SQLException e) {
            e.printStackTrace(); // Show full error in console for debugging
            throw new RuntimeException(e);
        }


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
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    void btnSearchEmployeeOnClick(ActionEvent event) {
        int id = Integer.parseInt(txtEmployeeId.getText());

        try {
            ResultSet resultSet = CrudUtil.execute("SELECT * FROM employee WHERE id = ?",id);

            if(resultSet.next()){
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");
                String password = resultSet.getString("employee_password");
                txtEmployeeName.setText(name);
                txtEmployeeEmail.setText(email);
                txtEmployeePassword.setText(String.valueOf(password));
            }else {
                new Alert(Alert.AlertType.ERROR,"Employee not found!").show();
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    void btnSearchProductOnClick(ActionEvent event) {
        int id = Integer.parseInt(txtProductId.getText());

        try {
            ResultSet resultSet = CrudUtil.execute("SELECT * FROM products WHERE id = ?",id);

            if(resultSet.next()){
                String name = resultSet.getString("product_name");
                String size = resultSet.getString("size");
                String price = String.valueOf(resultSet.getDouble("price"));
                String  qty = String.valueOf(resultSet.getInt("qty"));
                String url = resultSet.getString("img_url");

                txtProductName.setText(name);
                txtSize.setText(size);
                txtPrice.setText(price);
                txtQty.setText(qty);
                txtProductImageUrl.setText(url);
            }else {
                new Alert(Alert.AlertType.ERROR,"Product not found!").show();
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
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
        colImgUrl.setCellValueFactory(new PropertyValueFactory<>("image_url"));

        try {

            ResultSet resultSet = CrudUtil.execute("SELECT * FROM products");
            while (resultSet.next()) {
               productList.add(new Product(resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getDouble(4),
                        resultSet.getInt(5),
                        resultSet.getString(6)));

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



}
