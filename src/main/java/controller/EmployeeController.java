package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
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
import service.BoFactory;
import service.custom.ProductService;
import service.custom.impl.ProductServiceImpl;
import util.CrudUtil;
import util.ServiceType;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class EmployeeController implements Initializable {

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


    ProductService productService = BoFactory.getInstance().getServiceType(ServiceType.PRODUCT);
    private void clearProductFields(){
        txtProductId.clear();
        txtProductName.clear();
        txtPrice.clear();
        txtSize.clear();
        txtProductImageUrl.clear();
        txtQty.clear();
        tblProduct.getSelectionModel().clearSelection();
    }

    public void populateProductFields(Product product){
        txtProductId.setText(String.valueOf(product.getId()));
        txtProductName.setText(product.getName());
        txtSize.setText(product.getSize());
        txtPrice.setText(String.valueOf(product.getPrice()));
        txtQty.setText(String.valueOf(product.getQuantity()));
        txtProductImageUrl.setText(product.getImage_url());
//        txtProductId.setEditable(false);
    }

    @FXML
    void btnAddProductOnClick(ActionEvent event) {
        String id_text= txtProductId.getText();
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
        Product product = new Product(id,product_name,size,price,qty,img_url);

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
        Product product = new Product(id,product_name,size,price,qty,img_url);

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


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadProductTable();
    }
}
